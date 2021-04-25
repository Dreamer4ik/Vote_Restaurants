package ru.vote_restaurants.topjava22.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vote_restaurants.topjava22.model.Vote;
import ru.vote_restaurants.topjava22.util.exception.NotFoundException;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static ru.vote_restaurants.topjava22.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {
    @Autowired
    private VoteService voteService;

    @Test
    public void save() {
        Vote created = voteService.save(getNewVoteToday(), USER_ID, RESTAURANT_ID_1);
        int newId = created.getId();
        Vote newVote = getNewVoteToday();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteService.getVote(newId, USER_ID, LocalDate.now()), newVote);
    }

    @Test
    public void updateVoteAfterEleven() {
        Vote creatNew = voteService.save(getNewVoteToday(), USER_ID, RESTAURANT_ID_1);
        assertThrows(NotFoundException.class, () -> voteService.update(getUpdatedVoteAfterEleven(), USER_ID, RESTAURANT_ID_1, NEW_VOTE_ID));
    }

    @Test
    public void updateVoteBeforeEleven() {
        Vote creatNew = voteService.save(getNewVoteToday(), USER_ID, RESTAURANT_ID_1);
        voteService.update(getUpdatedVoteBeforeEleven(), USER_ID, RESTAURANT_ID_1, NEW_VOTE_ID);
        VOTE_MATCHER.assertMatch(voteService.getVote(creatNew.getId(), USER_ID, LocalDate.now()), getUpdatedVoteBeforeEleven());
    }

    @Test
    public void getVote() {
        Vote vote = voteService.getVote(VOTE_ID, USER_ID, LocalDate.of(2020, 8, 25));
        VOTE_MATCHER.assertMatch(vote, VOTE1);

    }


    @Test
    public void getAllVotesOfRestaurant() {
        VOTE_MATCHER.assertMatch(voteService.getAllVotesOfAdmin(RESTAURANT_ID_1), VOTES_FOR_ADMIN);
    }

    @Test
    public void getAll() {
        VOTE_MATCHER.assertMatch(voteService.getAll(), ALL_VOTES);
    }
}