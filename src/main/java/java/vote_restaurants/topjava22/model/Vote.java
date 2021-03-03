package java.vote_restaurants.topjava22.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @Column(name = "date")
    @NotNull
    private LocalDate localDate;

    @Column(name = "time")
    @NotNull
    private LocalTime localTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    public Vote(LocalDate localDate, LocalTime localTime, int userId) {
        this(null, localDate, localTime, userId);
    }

    public Vote(Integer id, LocalDate localDate, LocalTime localTime, int userId) {
        super(id);
        this.localDate = localDate;
        this.localTime = localTime;
        this.userId = userId;
    }

    public Vote() {
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return super.toString() +
                "localDate=" + localDate +
                ", localTime=" + localTime +
                ", restaurant=" + restaurant +
                ", user_id=" + userId +
                '}';
    }
}