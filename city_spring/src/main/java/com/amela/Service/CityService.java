package com.amela.Service;

import com.amela.Models.City;
import com.amela.Repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CityService implements ICityService{

    @Autowired
    private ICityRepository cityRepository;

    @Override
    public Page<City> findAll(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(int id) {
        return cityRepository.findById(id);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void delete(int id) {
        cityRepository.delete(id);
    }

    @Override
    public Page<City> findByName(String name, Pageable pageable) {
        return cityRepository.findByName(name, pageable);
    }
}
