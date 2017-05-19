package accounting;

import dbservice.DBService;

import java.util.HashMap;

/**
 * Created by SERGEY on 16.05.2017.
 */
public class AccountServiceDB implements AccountService {

    private final DBService dbService;
    private HashMap<String,UserProfile> sessions;

    public AccountServiceDB(DBService dbService) {
      this.dbService = dbService;
      sessions = new HashMap<>();
    }

    @Override
    public void addUser(UserProfile user) throws UserExistsException {

    }

    @Override
    public void deleteUserById(long id) {

    }

    @Override
    public UserProfile getUserByLogin(String login) {
        return null;
    }

    @Override
    public UserProfile getUserById(long id) {
        return null;
    }

    @Override
    public boolean isUserExists(String login) {
        return false;
    }

    @Override
    public void addSession(String session, UserProfile user) {

    }

    @Override
    public UserProfile getUserBySession(String session) {
        return null;
    }

    @Override
    public void deleteSession(String session) {

    }

    @Override
    public HashMap<String, Object> getUsersObjects() {
        return null;
    }
}
