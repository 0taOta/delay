package uz.pdp.service;

import uz.pdp.entity.Region;
import uz.pdp.payload.RegionDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class RegionsImpl implements RegionService{
        private static RegionsImpl instance;
        static Lock lock= new ReentrantLock();
    private RegionsImpl(){};
        public List<Region> regions = Collections.synchronizedList(new ArrayList<>());
        public static RegionsImpl getInstance(){
            if(Objects.isNull(instance))
                lock.lock();
            if(Objects.isNull(instance))
                instance= new RegionsImpl();
            lock.unlock();
            return instance;
        }
    @Override
    public List<RegionDTO> all() {
        List<RegionDTO> collect = regions
                .stream()
                .map(region -> new RegionDTO(region.getRegionId()
                        , region.getName()
                        , region.getCountry()))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public RegionDTO add(RegionDTO genreDTO) {
        return genreDTO;
    }

    @Override
    public RegionDTO edit(RegionDTO regionDTO, Integer id) {
        Region index = getByIdOrElseThrow(id);
        index.setCountry(regionDTO.getCountry());
        index.setName(regionDTO.getName());
    return toDTO(index);
    }


    @Override
    public boolean delete(Integer id) {
        Region index = getByIdOrElseThrow(id);
        boolean remove = regions.remove(index);
        return remove;
    }

    @Override
    public Region getByIdOrElseThrow(Integer id) {
        Region orElseThrow = regions.stream()
                .filter(region -> region.getRegionId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("id is not found...."));
        return orElseThrow;
    }
    public RegionDTO toDTO(Region region){
        return new RegionDTO(region.getRegionId(), region.getName(), region.getCountry());
    }
}
