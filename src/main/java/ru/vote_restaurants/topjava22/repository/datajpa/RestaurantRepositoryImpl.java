package ru.vote_restaurants.topjava22.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.vote_restaurants.topjava22.model.Restaurant;
import ru.vote_restaurants.topjava22.repository.proxyRepository.ProxyRestaurantRepository;

import java.util.List;

import ru.vote_restaurants.topjava22.repository.RestaurantRepository;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private static final Sort SORT_NAME_ID = Sort.by(Sort.Direction.ASC, "name", "id");


    @Autowired
    private ProxyRestaurantRepository proxyRestaurantRepository;

    @Override
    public Restaurant get(int id) {
        return proxyRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return proxyRestaurantRepository.findAll(SORT_NAME_ID);
    }

    @Override
    public Restaurant getByName(String name) {
        return proxyRestaurantRepository.findByName(name);
    }
}
