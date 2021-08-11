package com.amela.Repository;

import com.amela.Models.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CityRepository implements ICityRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<City> findAll(Pageable pageable) {

        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c", City.class);
        List<City> noteList = query.getResultList();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), noteList.size());
        Page<City> pageList = new PageImpl<>(noteList.subList(start, end), pageable, noteList.size());

        return pageList;
    }

    @Override
    public List<City> findAll() {
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c", City.class);
        return query.getResultList();
    }

    @Override
    public City findById(int id) {
        return entityManager.find(City.class, id);
    }

    @Override
    public void save(City city) {
        if (city.getID()!=0)
            entityManager.merge(city);
        else
            entityManager.persist(city);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(findById(id));
    }

    @Override
    public Page<City> findByName(String name, Pageable pageable) {
        TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c WHERE c.name like :name", City.class);
        query.setParameter("name", "%"+name+"%");
        List<City> noteTypeList = query.getResultList();

        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), noteTypeList.size());
        Page<City> pageList = new PageImpl<>(noteTypeList.subList(start, end), pageable, noteTypeList.size());
        return pageList;
    }
}
