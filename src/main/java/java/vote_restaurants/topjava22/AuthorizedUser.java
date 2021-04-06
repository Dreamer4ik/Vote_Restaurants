package java.vote_restaurants.topjava22;


import java.vote_restaurants.topjava22.to.UserTo;
import java.vote_restaurants.topjava22.model.User;
import java.vote_restaurants.topjava22.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;

    //REFACTORING
    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
    }


    public int getId(){
        return userTo.getId();
    }

    public void updateUser(UserTo newTo){
        userTo = newTo;
    }

    public UserTo getUserTo(){
        return  userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}
