package java.vote_restaurants.topjava22.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.vote_restaurants.topjava22.AuthorizedUser;
import java.vote_restaurants.topjava22.model.User;
import java.vote_restaurants.topjava22.repository.UserRepository;
import java.vote_restaurants.topjava22.to.UserTo;
import java.vote_restaurants.topjava22.util.UserUtil;

import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNotFound;
import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNotFoundWithId;
import static java.vote_restaurants.topjava22.util.UserUtil.prepareToSave;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        Assert.notNull(user, "user must be not null");

        return prepareAndSave(user);
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

    private User prepareAndSave(User user) {
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
