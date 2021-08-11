package com.amela.Repository;

import com.amela.Models.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityRepository extends IGeneralRepository<City>{
    Page<City> findByName(String name, Pageable pageable);

}
