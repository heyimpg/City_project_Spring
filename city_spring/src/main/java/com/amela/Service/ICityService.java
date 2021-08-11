package com.amela.Service;

import com.amela.Models.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICityService extends IGeneralService<City>{
    Page<City> findByName(String name, Pageable pageable);
}
