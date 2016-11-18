package cl.telematica.android.certamen3.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cl.telematica.android.certamen3.FavoriteDatabase;

/**
 * Created by Erlend on 18.11.2016.
 */

public class DBHelper {

    private static DBHelper instance;

    private FavoriteDatabase favoriteDatabase;

    public static DBHelper getInstance() {
        if(instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public void createDatabase(Context mContext){
        this.favoriteDatabase = new FavoriteDatabase(mContext);
    }

    public void addFavorite(String link){
        SQLiteDatabase db = favoriteDatabase.getWritableDatabase();

        if(db != null){
            db.beginTransaction();
            try {
                db.execSQL("INSERT INTO favorites (link) VALUES ('" + link + "')");
            } finally {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        }
    }

    public void removeFavorite(String link){
        SQLiteDatabase db = favoriteDatabase.getWritableDatabase();
        if(db != null){
            db.beginTransaction();
            try {
                db.execSQL("DELETE FROM favorites WHERE link = '" + link + "'");
            } finally {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        }
    }

    public boolean isFavorite(String link){
        SQLiteDatabase db = favoriteDatabase.getReadableDatabase();

        String query = "Select * from favorites where link = '" + link + "'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}
