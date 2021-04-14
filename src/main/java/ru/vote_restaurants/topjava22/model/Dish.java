package ru.vote_restaurants.topjava22.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "date", nullable = false, columnDefinition = "DATE DEFAULT now()")
    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    public Dish() {
    }

    public Dish(Integer id, String name, BigDecimal price, int userId) {
        super(id, name);
        this.price = price;
        this.localDate = LocalDate.now();
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
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
                ", price=" + price +
                ", localDate=" + localDate +
                ", restaurant=" + restaurant +
                ", userId=" + userId +
                '}';
    }
}