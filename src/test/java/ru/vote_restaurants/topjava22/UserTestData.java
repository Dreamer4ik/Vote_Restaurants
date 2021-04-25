package ru.vote_restaurants.topjava22;

import ru.vote_restaurants.topjava22.model.Role;
import ru.vote_restaurants.topjava22.model.User;

public class UserTestData {
    public static TestMatcher<User> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class, "password");


    public static final int START_SEQ = 100002;
    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final String EMAIL = "user0@yandex.ru";
    public static final String EMAIL_NOT_FOUND = "notfound@mail.ru";

    public static final User USER = new User(USER_ID, "User0", "user0@yandex.ru", "password0", Role.USER);

    public static User getNewUser() {
        return new User(null, "NewUser", "newauser@mail.ru", "newpass", Role.USER);

    }

    public static User getUpdatedUser() {
        User updatedUser = new User(USER);
        updatedUser.setName("Updated");
        updatedUser.setEmail("updated@mail.ru");
        return updatedUser;

    }
}
