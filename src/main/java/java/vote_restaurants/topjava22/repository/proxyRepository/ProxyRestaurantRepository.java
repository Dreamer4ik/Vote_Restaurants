package java.vote_restaurants.topjava22.repository.proxyRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.vote_restaurants.topjava22.model.Restaurant;

@Transactional(readOnly = true)
public interface ProxyRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Restaurant findByName(String name);
}
