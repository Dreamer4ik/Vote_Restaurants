package ru.vote_restaurants.topjava22.repository.proxyRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import ru.vote_restaurants.topjava22.model.Dish;

@Transactional(readOnly = true)
public interface ProxyDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurant_id")
    @Modifying
    int delete(@Param("id") int id, @Param("restaurant_id") int restaurant_id);

    @Query("SELECT d FROM Dish d WHERE  d.restaurant.id =: restaurant_id")
    List<Dish> getAll(@Param("restaurant_id") int restaurant_id);

}
