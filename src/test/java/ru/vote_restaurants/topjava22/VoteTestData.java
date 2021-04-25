package ru.vote_restaurants.topjava22;

import ru.vote_restaurants.topjava22.model.Restaurant;
import ru.vote_restaurants.topjava22.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Vote.class, "restaurant");


    public static final int START_SEQ = 100000;
    public static final int VOTE_ID = START_SEQ + 15;
    public static final int USER_ID = START_SEQ + 2;
    public static final int RESTAURANT_ID_1 = START_SEQ + 7;
    public static final int RESTAURANT_ID_2 = START_SEQ + 8;
    public static final int NEW_VOTE_ID = START_SEQ + 20;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID_1, "Груша");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID_2, "Кампания");


    public static final Vote VOTE1 = new Vote(VOTE_ID, LocalDate.of(2020, 8, 25)
            , LocalTime.of(10, 00, 00), USER_ID);

    public static final Vote VOTE2 = new Vote(VOTE_ID + 1, LocalDate.of(2020, 8, 25)
            , LocalTime.of(13, 00, 00), USER_ID + 1);

    public static final Vote VOTE3 = new Vote(VOTE_ID + 2, LocalDate.of(2020, 8, 25)
            , LocalTime.of(10, 00, 00), USER_ID + 2);

    public static final Vote VOTE4 = new Vote(VOTE_ID + 3, LocalDate.of(2020, 8, 25)
            , LocalTime.of(10, 00, 00), USER_ID + 3);

    public static final Vote VOTE5 = new Vote(VOTE_ID + 4, LocalDate.of(2020, 8, 25)
            , LocalTime.of(10, 00, 00), USER_ID + 4);

    public static final List<Vote> VOTES_FOR_ADMIN = List.of(VOTE1, VOTE2, VOTE3);

    public static final List<Vote> ALL_VOTES = List.of(VOTE1, VOTE2, VOTE3, VOTE4, VOTE5);

    public static Vote getNewVoteToday() {
        Vote newVote = new Vote(null, LocalDate.now(),
                LocalTime.of(10, 30, 45), USER_ID);
        newVote.setRestaurant(RESTAURANT_1);
        return newVote;
    }

    public static Vote getUpdatedVoteAfterEleven() {
        Vote newVote = new Vote(NEW_VOTE_ID, LocalDate.now(),
                LocalTime.of(15, 30, 45), USER_ID);
        newVote.setRestaurant(RESTAURANT_1);
        return newVote;
    }

    public static Vote getUpdatedVoteBeforeEleven() {
        Vote newVote = new Vote(NEW_VOTE_ID, LocalDate.now(),
                LocalTime.of(10, 50, 45), USER_ID);
        newVote.setRestaurant(RESTAURANT_1);
        return newVote;
    }
}
