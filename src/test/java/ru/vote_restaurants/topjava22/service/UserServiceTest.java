package ru.vote_restaurants.topjava22.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vote_restaurants.topjava22.model.User;
import ru.vote_restaurants.topjava22.util.exception.NotFoundException;

import static org.junit.Assert.*;
import static ru.vote_restaurants.topjava22.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    public void create() {
        User created = userService.create(getNewUser());
        int newId = created.getId();
        User newUser = getNewUser();
        newUser.setId(newId);
        VOTE_MATCHER.assertMatch(created, newUser);
        VOTE_MATCHER.assertMatch(userService.getUser(newId), newUser);


    }

    @Test
    public void update() {
        User updatedUser = getUpdatedUser();
        userService.update(updatedUser);
        VOTE_MATCHER.assertMatch(userService.getUser(USER_ID), getUpdatedUser());
    }


    @Test
    public void delete() {
        userService.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> userService.getUser(USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> userService.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        VOTE_MATCHER.assertMatch(userService.getUser(USER_ID), USER);
    }

    @Test
    public void getByEmail() {
        VOTE_MATCHER.assertMatch(userService.getByEmail(EMAIL), USER);
    }

    @Test
    public void getByEmailNotFound() {
        assertThrows(NotFoundException.class, () -> userService.getByEmail(EMAIL_NOT_FOUND));
    }
}