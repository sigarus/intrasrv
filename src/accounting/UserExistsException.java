package accounting;

/**
 * Created by SERGEY on 27.04.2017.
 */
public class UserExistsException extends Exception {
    public UserExistsException(String login){
        super("User " + login + "already exists.");
    }
}
