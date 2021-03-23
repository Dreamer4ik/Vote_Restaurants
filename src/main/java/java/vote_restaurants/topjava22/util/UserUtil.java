package java.vote_restaurants.topjava22.util;

import org.springframework.util.StringUtils;

import java.vote_restaurants.topjava22.model.Role;
import java.vote_restaurants.topjava22.model.User;
import java.vote_restaurants.topjava22.to.UserTo;

public class UserUtil {
    public static Object updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return user;
    }

    //REFACTOR
    public static User prepareToSave(User user, String passwordEncoder) {
        String password = user.getPassword();
        //  user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }
}
