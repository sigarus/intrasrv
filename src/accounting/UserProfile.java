package accounting;

/**
 * Created by SERGEY on 26.04.2017.
 */
public class UserProfile {

    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String psssword;
    private boolean adm;
    private  boolean banned;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPsssword() {
        return psssword;
    }


    public UserProfile(String firstName, String middleName, String lastName, String login, String psssword, boolean adm) {
        this.id =0;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.psssword = psssword;
        this.adm = adm;
        this.banned = false;
    }

    public  UserProfile(UserProfile user){
        this.id =user.getId();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.login = user.getLogin();
        this.psssword = user.getPsssword();
        this.adm = user.getAdm();
        this.banned = user.getBanned();
    }

    public void banUser(){
        this.setBanned(true);
    }

    public void unBanUser(){
        this.setBanned(false);
    }

    public boolean getAdm(){
        return adm;
    }

    public  boolean getBanned(){
        return banned;
    }




    @Override
    public String toString() {
        return "UserProfile{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", adm=" + adm +
                ", banned=" + banned +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPsssword(String psssword) {
        this.psssword = psssword;
    }

    public void setAdm(boolean adm) {
        this.adm = adm;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
