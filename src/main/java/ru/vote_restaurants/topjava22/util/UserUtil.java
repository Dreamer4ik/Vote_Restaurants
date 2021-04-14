package ru.vote_restaurants.topjava22.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.vote_restaurants.topjava22.model.Role;
import ru.vote_restaurants.topjava22.model.User;
import ru.vote_restaurants.topjava22.to.UserTo;

public class UserUtil {
    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return user;
    }


    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static UserTo asTo(User user){
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
