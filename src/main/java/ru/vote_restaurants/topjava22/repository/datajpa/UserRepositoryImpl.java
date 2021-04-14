package ru.vote_restaurants.topjava22.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vote_restaurants.topjava22.model.User;
import ru.vote_restaurants.topjava22.repository.UserRepository;

import ru.vote_restaurants.topjava22.repository.proxyRepository.ProxyUserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private ProxyUserRepository proxyUserRepository;

    @Override
    public User save(User user) {
        return proxyUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return proxyUserRepository.delete(id) != 0;
    }

    @Override
    public User getUser(int id) {
        return proxyUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return proxyUserRepository.getByEmail(email);
    }
}
