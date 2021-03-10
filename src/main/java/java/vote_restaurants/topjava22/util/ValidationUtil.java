package java.vote_restaurants.topjava22.util;

public class ValidationUtil {
    public static <T> T checkNotFoundWithId (T object, int id) {
        checkNotFoundWithId(object != null, id);

        return object;

    }
}
