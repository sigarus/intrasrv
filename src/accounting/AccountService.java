package accounting;


import org.eclipse.jetty.server.session.Session;

import java.util.HashMap;

/**
 * Created by SERGEY on 26.04.2017.
 */
public class AccountService {

    private HashMap<String,UserProfile> usersDB;
    private HashMap<String,UserProfile> sessions;

    public AccountService(){
         usersDB = new HashMap<>();
         sessions = new HashMap<>();

         UserProfile adm = new UserProfile("Admin","Adminovich","Adminov","adm","adm", true);

        try {
            addUser(adm);
        } catch (UserExistsExtension e) {
           e.printStackTrace();
        }
    }


    public void addUser(UserProfile user) throws UserExistsExtension{
          if (isUserExists(user.getLogin())) {
            throw new UserExistsExtension(user.getLogin());
        }
          usersDB.put(user.getLogin(),user);
    }

    public UserProfile getUserByLogin(String login){
        return usersDB.get(login);
    }

    public boolean isUserExists(String login){
        return (getUserByLogin(login) != null);
    }

    public void addSession(String session, UserProfile user){
        sessions.put(session,user);
    }

    public UserProfile getUserBySession(String session){
        return  sessions.get(session);
    }

    public void deleteSession(String session){
        sessions.remove(session);

    }


}
