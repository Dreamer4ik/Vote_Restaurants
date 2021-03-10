package java.vote_restaurants.topjava22.repository;

import java.time.LocalDate;
import java.util.List;
import java.vote_restaurants.topjava22.model.Vote;

public interface VoteRepository {

    Vote save (Vote vote , int userId, int restaurantId);

    Vote getVote(int id , int userId, LocalDate localDate);

    List<Vote> getAllVotesOfRestaurant (int restaurantId);

    List<Vote> getAll();
}
