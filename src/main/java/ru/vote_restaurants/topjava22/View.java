package ru.vote_restaurants.topjava22;

import javax.validation.groups.Default;

public class View {
    // Validate only form UI/REST
    public interface Web extends Default {
    }

    // Validate only when DB save/update
    public interface Persist extends Default {
    }
}
