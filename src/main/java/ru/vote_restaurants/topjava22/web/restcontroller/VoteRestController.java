package ru.vote_restaurants.topjava22.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vote_restaurants.topjava22.View;
import ru.vote_restaurants.topjava22.model.Vote;
import ru.vote_restaurants.topjava22.service.VoteService;
import ru.vote_restaurants.topjava22.util.ValidationUtil;
import ru.vote_restaurants.topjava22.web.SecurityUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL_VOTES, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    private static final Logger LOG = LoggerFactory.getLogger(VoteRestController.class);

    public static final String REST_URL_VOTES = "/restaurants/votes";

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/{restaurantId}/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int restaurantId){

        int userId = SecurityUtil.authUserId();
        ValidationUtil.checkNew(vote);
        LOG.info("create vote {} for user id {}", vote, userId);
        Vote createdVote = voteService.save(vote, userId, restaurantId);
        URI ofNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL_VOTES + "/{id}")
                .buildAndExpand(createdVote.getId()).toUri();

        return ResponseEntity.created(ofNewResource).body(createdVote);
    }

    @PutMapping(value = "/{restaurantId}/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@Validated(View.Web.class) @RequestBody Vote vote,@PathVariable int id,  @PathVariable int restaurantId){
        int userId = SecurityUtil.authUserId();
        LOG.info("update vote id {} for user id {}", id, userId);
        voteService.update(vote, userId, restaurantId, id);
    }

    @GetMapping("/{restaurantId}")
    List<Vote> getAllVotesOfRestaurnat(@PathVariable int restaurantId ){
        LOG.info("getAllVotesOfRestaurant restaurant id {}", restaurantId);
        return voteService.getAllVotesOfAdmin(restaurantId);
    }

    @GetMapping
    public List<Vote> getAll(){
        LOG.info("getAllVote");
        return voteService.getAll();
    }
}
