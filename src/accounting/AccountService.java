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
        long i=0;
        for (Map.Entry<String,UserProfile> pair : usersDB.entrySet()) {
            if (i < pair.getValue().getId()) { i = pair.getValue().getId();}
        }
          user.setId(i+1);
          usersDB.put(user.getLogin(),user);
    }

    public void  deleteUserById(long id){
        String key="";
        for (Map.Entry<String,UserProfile> pair : usersDB.entrySet()) {
            if (pair.getValue().getId()==id) { key = pair.getKey();}
        }
        if (!key.isEmpty()){usersDB.remove(key);};
    }

    public UserProfile getUserByLogin(String login){
        return usersDB.get(login);
    }

    public UserProfile getUserById(long id){
        UserProfile user = null;
        for (Map.Entry<String,UserProfile> pair : usersDB.entrySet()) {
            if (pair.getValue().getId()==id) { user = pair.getValue();}
        }
        return user;
    }


    public boolean isUserExists(String login){
        return (getUserByLogin(login) != null);
    }

    public void addSession(String session, UserProfile user){
        sessions.put(session,user);
    }

    public void deleteSessionById(long id){
        String session="";
        for (Map.Entry<String,UserProfile> pair : sessions.entrySet()) {
            if (pair.getValue().getId()==id) { session = pair.getKey();}
        }
        if (!session.isEmpty()){deleteSession(session);};
    }

    public UserProfile getUserBySession(String session){
        return  sessions.get(session);
    }

    public void deleteSession(String session){
        sessions.remove(session);

    }

    public HashMap<String, Object> getUsersObjects(){

        HashMap<String,Object> users = new HashMap<String,Object>();
        Iterator<Map.Entry<String,UserProfile>> iter = usersDB.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, UserProfile> pair = iter.next();
            users.put(pair.getValue().getLogin(),(Object)pair.getValue());
        }

        return users;
    }


    /*public Map getHashOfUsers(){
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
    */

}
