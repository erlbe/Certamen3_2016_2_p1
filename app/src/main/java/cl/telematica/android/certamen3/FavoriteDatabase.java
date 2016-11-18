package cl.telematica.android.certamen3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Erlend on 18.11.2016.
 */

public class FavoriteDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "favoritesDB";
    private static final int DB_version = 1;

    String sqlString = "CREATE TABLE IF NOT EXISTS 'favorites' ('link' TEXT PRIMARY KEY)";

    public FavoriteDatabase(Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS favorites");
        onCreate(db);
    }
}
