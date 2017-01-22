package bootcamp.com.bookshare.DbCommunication;

/**
 * Created by krystian on 17.01.17.
 */

class User {

    private String id;
    private String login;
    private String password;
    private String name;
    private String surname;

    public User(String login, String name, String password, String surname) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.surname = surname;
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
