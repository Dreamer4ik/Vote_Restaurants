package ru.vote_restaurants.topjava22.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vote_restaurants.topjava22.AuthorizedUser;
import ru.vote_restaurants.topjava22.View;
import ru.vote_restaurants.topjava22.model.User;
import ru.vote_restaurants.topjava22.service.UserService;
import ru.vote_restaurants.topjava22.to.UserTo;
import ru.vote_restaurants.topjava22.util.ValidationUtil;

import java.net.URI;

import ru.vote_restaurants.topjava22.util.UserUtil;

@RestController
@RequestMapping(value = UserRestController.REST_URL_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    public static final String REST_URL_USERS = "/users";
    @Autowired
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus (value = HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Validated(View.Web.class) @RequestBody UserTo userTo) {
        User createdUser =  create(userTo);
        URI ofNewResourse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL_USERS).build().toUri();
        return ResponseEntity.created(ofNewResourse).body(createdUser);
    }

    public User create (UserTo userTo){
        LOG.info("create from to {}", userTo);
        ValidationUtil.checkNew(userTo);
        return userService.create(UserUtil.createNewFromTo(userTo));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update (@RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authUser){
        LOG.info("update {} with id={}", userTo , authUser.getId());
        ValidationUtil.assureIdConsistent(userTo, authUser.getId());
        userService.update(userTo);
    }

    @DeleteMapping("/profile/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        LOG.info("delete {}",authUser.getId());
        userService.delete(authUser.getId());
    }


}
