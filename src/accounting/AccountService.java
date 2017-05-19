package accounting;

import java.util.HashMap;

/**
 * Created by SERGEY on 16.05.2017.
 */
public interface AccountService {
    void addUser(UserProfile user) throws UserExistsException;
    void  deleteUserById(long id);
    UserProfile getUserByLogin(String login);
    UserProfile getUserById(long id);
    boolean isUserExists(String login);
    void addSession(String session, UserProfile user);
    UserProfile getUserBySession(String session);
    void deleteSession(String session);
    HashMap<String, Object> getUsersObjects();

}
