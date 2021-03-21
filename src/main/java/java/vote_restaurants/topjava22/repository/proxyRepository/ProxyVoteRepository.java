package java.vote_restaurants.topjava22.repository.proxyRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.vote_restaurants.topjava22.model.Vote;

@Transactional(readOnly = true)
public interface ProxyVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT  v FROM  Vote  v WHERE v.restaurant.id =: restaurant_id")
    List<Vote> getAllVotesOfRestaurant(@Param("restaurant_id") int restaurant_id);

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.userId=:user_id AND v.localDate=:date")
    Vote getVote(@Param("id") int id, @Param("user_id") int user_id, @Param("date") LocalDate localdate);
}
