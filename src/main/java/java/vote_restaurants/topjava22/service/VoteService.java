package java.vote_restaurants.topjava22.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.vote_restaurants.topjava22.model.Vote;
import java.vote_restaurants.topjava22.repository.VoteRepository;

import static java.vote_restaurants.topjava22.util.ValidationUtil.assureIdConsistent;
import static java.vote_restaurants.topjava22.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Vote save(Vote vote, int userId, int restaurantId){
        Assert.notNull(vote , " vote must not be null");
        return checkNotFoundWithId(voteRepository.save(vote, userId, restaurantId), vote.id());

    }

    public void update(Vote vote, int userId, int restaurantId, int id){
        Assert.notNull(vote, "user must be not null");
        assureIdConsistent(vote, id);
        checkNotFoundWithId(voteRepository.save(vote, userId, restaurantId), id);
    }

    public Vote getVote(int id, int userId, LocalDate localDate){
        return checkNotFoundWithId(voteRepository.getVote(id, userId, localDate), id);
    }

    public List<Vote>  getAllVotesOfAdmin (int restaurantId){
        return voteRepository.getAllVotesOfRestaurant(restaurantId);
    }

    public List<Vote> getAll(){
        return voteRepository.getAll();
    }


}
