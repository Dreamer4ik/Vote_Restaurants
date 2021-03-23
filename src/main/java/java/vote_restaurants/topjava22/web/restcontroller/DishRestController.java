package java.vote_restaurants.topjava22.web.restcontroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.vote_restaurants.topjava22.View;
import java.vote_restaurants.topjava22.model.Dish;
import java.vote_restaurants.topjava22.service.DishService;
import java.vote_restaurants.topjava22.web.SecurityUtil;

import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNew;


@RestController
@RequestMapping(value = DishRestController.REST_URL_DINNERS, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    private static final Logger LOG = LoggerFactory.getLogger(DishRestController.class);

    public static final String REST_URL_DINNERS = "/restaurants/dishes";

    @Autowired
    private final DishService dishService;

    public DishRestController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping(value = "/{restaurantId}/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> save(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        checkNew(dish);
        dish.setLocalDate(LocalDate.now());
        LOG.info("save {} for admin {}", dish, userId);
        Dish createdDis = dishService.create(dish, userId, restaurantId);
        URI ofNewResourse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL_DINNERS + "/{id}")
                .buildAndExpand(createdDis.getId()).toUri();

        return ResponseEntity.created(ofNewResourse).body(createdDis);
    }

    @PutMapping(value = "/{restaurantId}update{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Validated(View.Web.class) @RequestBody Dish dish, @PathVariable int id, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("update vote id {} for user id {}", id, userId);
        dishService.update(dish, userId, restaurantId, id);
    }

    @DeleteMapping("/{restaurantId}/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        LOG.info("delete dish {} for restaurant {}", id, restaurantId);
        dishService.delete(id, restaurantId);
    }

    @GetMapping("/{restaurantId}")
    public List<Dish> getAllForRestaurant(@PathVariable int restaurantId) {
        LOG.info("getAll for restaurant {}", restaurantId);
        return dishService.getAllForRestaurant(restaurantId);
    }


}
