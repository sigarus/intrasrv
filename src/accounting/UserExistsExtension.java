package accounting;

/**
 * Created by SERGEY on 27.04.2017.
 */
public class UserExistsExtension extends Exception {
    public UserExistsExtension(String login){
        super("User " + login + "already exists.");
    }
}
