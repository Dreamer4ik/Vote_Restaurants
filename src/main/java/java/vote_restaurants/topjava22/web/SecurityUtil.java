package java.vote_restaurants.topjava22.web;

import java.vote_restaurants.topjava22.model.AbstractBaseEntity;

//refactoring
public class SecurityUtil {

   private static int id = AbstractBaseEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}
