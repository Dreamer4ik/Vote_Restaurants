package java.vote_restaurants.topjava22.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.vote_restaurants.topjava22.model.Restaurant;
import java.vote_restaurants.topjava22.repository.RestaurantRepository;
import java.vote_restaurants.topjava22.repository.proxyRepository.ProxyRestaurantRepository;

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
