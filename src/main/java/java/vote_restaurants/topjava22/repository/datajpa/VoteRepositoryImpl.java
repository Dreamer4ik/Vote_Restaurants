package java.vote_restaurants.topjava22.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.vote_restaurants.topjava22.model.Vote;
import java.vote_restaurants.topjava22.repository.VoteRepository;
import java.vote_restaurants.topjava22.repository.proxyRepository.ProxyRestaurantRepository;
import java.vote_restaurants.topjava22.repository.proxyRepository.ProxyUserRepository;
import java.vote_restaurants.topjava22.repository.proxyRepository.ProxyVoteRepository;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private ProxyVoteRepository proxyVoteRepository;
    @Autowired
    private ProxyUserRepository proxyUserRepository;
    @Autowired
    private ProxyRestaurantRepository proxyRestaurantRepository;


    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        LocalDate localDateToday = LocalDate.now();

        if (!vote.isNew() && (getVote(vote.getId(), userId, vote.getLocalDate()) == null)
            || !vote.getLocalDate().equals(localDateToday)) {

            return  null;
        }

        if (!vote.isNew() && vote.getLocalTime().isAfter(LocalTime.of(11, 00, 00))) {
            return null;
        }

        vote.setRestaurant(proxyRestaurantRepository.getOne(restaurantId));
        vote.setUserId(proxyUserRepository.getOne(userId).id());

        return proxyVoteRepository.save(vote);
    }

    @Override
    public Vote getVote(int id, int userId, LocalDate localDate) {
        return proxyVoteRepository.getVote(id, userId, localDate);
    }

    @Override
    public List<Vote> getAllVotesOfRestaurant(int restaurantId) {
        return proxyVoteRepository.getAllVotesOfRestaurant(restaurantId);
    }

    @Override
    public List<Vote> getAll() {
        return proxyVoteRepository.findAll();
    }
}
