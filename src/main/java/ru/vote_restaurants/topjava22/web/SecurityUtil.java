package ru.vote_restaurants.topjava22.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.vote_restaurants.topjava22.AuthorizedUser;
import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    private SecurityUtil() {
    }


    public static AuthorizedUser safeGetUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser getUser(){
        return requireNonNull(safeGetUser(), "No authorized user found");
    }

    public static int authUserId() {
        return getUser().getUserTo().id();
    }


}
