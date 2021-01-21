package ASTEROID_FIGHTER_FREE.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ASTEROID_FIGHTER_FREE.Database.UserContract.UserEntry;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "HighScores.db";

    public static final String CREATE_DATABASE = "CREATE TABLE "
            + UserEntry.TABLE_NAME + " ("
            + UserEntry._ID + " INTEGER PRIMARY KEY, "
            + UserEntry.COLUMN_USER_NAME + " TEXT, "
            + UserEntry.COLUMN_LAST_GAME_SCORE + " INTEGER, "
            + UserEntry.COLUMN_HIGH_SCORE + " INTEGER)";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
