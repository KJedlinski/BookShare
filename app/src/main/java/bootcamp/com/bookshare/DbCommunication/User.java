package bootcamp.com.bookshare.DbCommunication;

/**
 * Created by krystian on 17.01.17.
 */

public class User {

    private String id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean authorizedLogin;
    private boolean authorizedPasswd;


    public User(String login, String name, String password, String surname) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.surname = surname;
    }

    public boolean isAuthorizedLogin() {
        return authorizedLogin;
    }

    public void setAuthorizedLogin(boolean authorizedLogin) {
        this.authorizedLogin = authorizedLogin;
    }

    public boolean isAuthorizedPasswd() {
        return authorizedPasswd;
    }

    public void setAuthorizedPasswd(boolean authorizedPasswd) {
        this.authorizedPasswd = authorizedPasswd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){ return id;}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString(){
        return "Twoje dane:\n" + "Imię i nazwisko: " +name + " " + surname
                + "\nID: " + id + "\nLogin: " + login;
    }
}
