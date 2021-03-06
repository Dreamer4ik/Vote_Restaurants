package ru.vote_restaurants.topjava22.repository;

import ru.vote_restaurants.topjava22.model.User;

public interface UserRepository {

    User save(User user);

    boolean delete (int id);

    User getUser(int id);

    User getByEmail(String email);

}
