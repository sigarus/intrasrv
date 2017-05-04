package accounting;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        } catch (UserExistsException e) {
           e.printStackTrace();
        }
    }


    public void addUser(UserProfile user) throws UserExistsException {
          if (isUserExists(user.getLogin())) {
            throw new UserExistsException(user.getLogin());
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

    public void deleteSession(HttpSession session){
        sessions.remove(session);

    }

    public Map getHashOfUsers(){
        HashMap<String,Object> root = new HashMap<String,Object>();
        HashMap<String,Object> users = new HashMap<String,Object>();
        Iterator<Map.Entry<String,UserProfile>> iter = usersDB.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, UserProfile> pair = iter.next();
            users.put(pair.getValue().getLogin(),(Object)pair.getValue());
        }
        root.put("users",users);
        return root;
    }


}
