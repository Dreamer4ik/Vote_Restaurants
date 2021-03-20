package java.vote_restaurants.topjava22.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.vote_restaurants.topjava22.model.User;
import java.vote_restaurants.topjava22.repository.UserRepository;
import java.vote_restaurants.topjava22.to.UserTo;
import java.vote_restaurants.topjava22.util.UserUtil;

import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNotFound;
import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNotFoundWithId;
import static java.vote_restaurants.topjava22.util.UserUtil.prepareToSave;


// Add spring security later
//Refactor method prepareAndSave later
@Service
public class UserService {

    private final UserRepository userRepository;
    private String passwordEncoder;

    public String getPasswordEncoder() {
        return passwordEncoder;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(User user) {
        Assert.notNull(user, "user must be not null");

        return prepareAndSave(user, getPasswordEncoder());
    }

    public void update(UserTo userTo) {
        Assert.notNull(userTo, "user must not be null");
        User user = getUser(userTo.id());
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        prepareAndSave(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }



    public User getUser(int id) {
        return checkNotFoundWithId(userRepository.getUser(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email" + email);

    }

    // + public UserDetails loadUserByUsername

    // REFACTOR
    private User prepareAndSave(User user, String passwordEncoder) {

        return userRepository.save(prepareToSave(user, getPasswordEncoder()));
    }

    //REF
    private void prepareAndSave(Object updateFromTo) {
    }

}
