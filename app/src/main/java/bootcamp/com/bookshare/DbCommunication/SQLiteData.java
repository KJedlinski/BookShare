package bootcamp.com.bookshare.DbCommunication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by krystian on 17.01.17.
 */

public class SQLiteData extends SQLiteOpenHelper {

    private static SQLiteData appDatabaseInstance;

    public SQLiteData(Context context) {
        super(context, "usersDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTO INCREMENT, name TEXT, surname TEXT" +
                "login TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static SQLiteData getDatabase(Context context){
        if (appDatabaseInstance  == null){
            appDatabaseInstance = new SQLiteData(context);
        }
        return  appDatabaseInstance;
    }
}