package ru.vote_restaurants.topjava22;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vote_restaurants.topjava22.model.Dish;
import ru.vote_restaurants.topjava22.model.Role;
import ru.vote_restaurants.topjava22.model.User;
import ru.vote_restaurants.topjava22.model.Vote;
import ru.vote_restaurants.topjava22.service.DishService;
import ru.vote_restaurants.topjava22.service.UserService;
import ru.vote_restaurants.topjava22.service.VoteService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class MainApplicationContext {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml",
                "spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            DishService dishService = appCtx.getBean(DishService.class);
            UserService userService = appCtx.getBean(UserService.class);
            VoteService voteService = appCtx.getBean(VoteService.class);


            dishService.create(new Dish(null, "NewDish", new BigDecimal("200.45"), 100000), 100000, 100007);
            userService.create(new User(null, "NewUser", "newauser@mail.ru", "newpass", Role.USER));
            voteService.save(new Vote(LocalDate.now(),
                    LocalTime.of(10, 30, 45), 100002), 100002, 100007);


        }

    }
}
