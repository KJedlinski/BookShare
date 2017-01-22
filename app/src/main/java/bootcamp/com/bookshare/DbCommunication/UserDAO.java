package bootcamp.com.bookshare.DbCommunication;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by krystian on 22.01.17.
 */

class UserDAO {
    private static UserDAO instance;

    public UserDAO getUserDAOInstance(){
        if (instance == null){
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
}
