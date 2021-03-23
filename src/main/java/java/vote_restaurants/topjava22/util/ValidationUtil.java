package java.vote_restaurants.topjava22.util;

import java.vote_restaurants.topjava22.HasId;
import java.vote_restaurants.topjava22.util.exception.IllegalRequestDataException;

public class ValidationUtil {
    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);

        return object;

    }

    public static void assureIdConsistent(HasId bean, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);

        return object;
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }

}
