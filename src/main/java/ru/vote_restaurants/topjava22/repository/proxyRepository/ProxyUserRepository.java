package ru.vote_restaurants.topjava22.repository.proxyRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vote_restaurants.topjava22.model.User;

@Transactional(readOnly = true)
public interface ProxyUserRepository extends JpaRepository <User, Integer> {

    @Transactional
    @Query("DELETE FROM User  u WHERE u.id =: id")
    @Modifying
    int delete(@Param("id") int id);

    User getByEmail(String email);


}
