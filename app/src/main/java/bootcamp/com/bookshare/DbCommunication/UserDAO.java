package bootcamp.com.bookshare.DbCommunication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class UserDAO {
    private static UserDAO instance;

    public UserDAO getUserDAOInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public void addUser(User user, Context context) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("surname", user.getSurname());
        contentValues.put("login", user.getLogin());
        contentValues.put("password", user.getPassword());
        SQLiteData.getDatabase(context).getWritableDatabase().insertOrThrow("users", null, contentValues);
    }

    public void removeUser(User user, Context context) {
        SQLiteData.getDatabase(context).getWritableDatabase().delete("users", "id = ?", new String[]{user.getId()});
    }

    public void updateUserData(User user, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("surname", user.getSurname());
        contentValues.put("login", user.getLogin());
        contentValues.put("password", user.getPassword());
        SQLiteData.getDatabase(context)
                .getWritableDatabase()
                .update("users", contentValues, "id = ?", new String[]{user.getId()});
    }

    public String showUser(User user) {
        return user.toString();
    }

    public static void authorizeUser(User user, Context context) {

        String id = "";
        String name = "";
        String passwd = "";
        String surname = "";
        String login = null;

        Cursor registeredUsers = SQLiteData.getDatabase(context)
                .getReadableDatabase()
                .query("users", new String[]{"id", "name", "surname", "login", "password"}, null, null, null, null, null);

        while (registeredUsers.moveToNext()) {
            if (registeredUsers.getString(1).equals(user.getLogin())) {
                id = registeredUsers.getString(0);
                name = registeredUsers.getString(1);
                surname = registeredUsers.getString(2);
                login = registeredUsers.getString(3);
                passwd = registeredUsers.getString(4);
                break;
            }
        }
        registeredUsers.close();

        if (login == null) {
            user.setAuthorizedLogin(false);
            return;
        } else if (!passwd.equals(user.getPassword())) {
            user.setAuthorizedPasswd(false);
            return;
        } else {
            user.setId(id);
            user.setName(name);
            user.setSurname(surname);
            user.setAuthorizedLogin(true);
            user.setAuthorizedPasswd(true);
        }
    }
}