package uz.pdp.service;

import uz.pdp.entity.Country;
import uz.pdp.payload.CountryDTO;

import java.io.IOException;
import java.util.List;

public interface CountryService {

    List<Country> getRegionsById(Integer id);

    CountryDTO getCountryById(Integer id);
    Boolean addCountry(CountryDTO countryDTO );
    Boolean editCountry(Integer id, CountryDTO countryDTO);
    Boolean deleteCountry(Integer id);
    String read(Integer id);
    Boolean serialize();
    Boolean deserialize();
    Boolean logger() throws IOException;

}
