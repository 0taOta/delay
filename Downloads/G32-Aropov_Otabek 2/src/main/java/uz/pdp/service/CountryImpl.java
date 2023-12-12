package uz.pdp.service;

import uz.pdp.entity.Country;
import uz.pdp.entity.Region;
import uz.pdp.payload.CountryDTO;

import java.io.*;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class CountryImpl implements CountryService{
    private static CountryService instance;
    static Lock lock= new ReentrantLock();
    private CountryImpl(){};
    static List<Country> countries = Collections.synchronizedList(new ArrayList<>());
    public static CountryService getInstance(){
        if(Objects.isNull(instance))
            lock.lock();
        if(Objects.isNull(instance))
            instance= new CountryImpl();
        lock.unlock();
        return instance;
    }
    @Override
    public List<Country>getRegionsById(Integer id) {
        List<Country> regions = countries
                .stream()
                .filter(country -> country.getRegion().
                        getRegionId().equals(id)).collect(Collectors.toList());
        return regions;
    }

    @Override
    public CountryDTO getCountryById(Integer id) {
        Country first = countries
                .stream()
                .filter(country -> country.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("id is not found"));
        return toDTO(first);
    }

    @Override
    public Boolean addCountry(CountryDTO countryDTO) {
        int i = countries.size() + 1;
        Region region = RegionsImpl.getInstance().regions
                .stream()
                .filter(region1 -> region1.getRegionId().equals(countryDTO.getId()))
                .findFirst()
                .orElseThrow(()->new RuntimeException());
        Country country = new Country(i, countryDTO.getName(), countryDTO.getFlagUrl(), countryDTO.getFilePath()
                , countryDTO.getEstablishment(), countryDTO.getSquare(), region);
      return true;
    }

    @Override
    public Boolean editCountry(Integer id, CountryDTO countryDTO) {
        CountryDTO index = getCountryById(id);
        index.setEstablishment(countryDTO.getEstablishment());
        index.setFilePath(countryDTO.getFilePath());
        index.setName(countryDTO.getName());
        index.setFlagUrl(countryDTO.getFlagUrl());
        index.setSquare(countryDTO.getSquare());

        Region reg = RegionsImpl.getInstance().regions
                .stream()
                .filter(region -> region.getRegionId().equals(region.getRegionId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
        index.setRegion(reg);
        return true;

    }

    @Override
    public Boolean deleteCountry(Integer id) {
        CountryDTO index = getCountryById(id);
        boolean remove = countries.remove(index);
        return remove;

    }

    @Override
    public String read(Integer id) {
        CountryDTO index = getCountryById(id);
        File file = new File(index.getFilePath());
        String str;
        try(InputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = inputStream.readAllBytes();
            str= new String(bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    @Override
    public Boolean serialize() {
        try(ObjectOutputStream outputStream= new ObjectOutputStream(new FileOutputStream("db/serialize.txt"))){
            outputStream.writeObject(countries);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean deserialize() {
        try(ObjectInputStream objectInputStream= new ObjectInputStream(new FileInputStream("db/serialize.txt"))) {
            countries = (List<Country>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean logger() throws IOException {
        Handler handler= new FileHandler("db/logger.txt");
        return true;
    }

    public CountryDTO toDTO(Country country){
        return new CountryDTO(country.getId(),country.getName(),
                country.getFlagUrl(),country.getFilePath(),
                country.getEstablishment(),country.getSquare(),country.getRegion());
    }

}
