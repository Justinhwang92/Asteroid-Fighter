package com.example.myfirstapp.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.myfirstapp.Database.UserContract.UserEntry;

public class UserProvider extends ContentProvider{

    public static final int USERS = 100;
    public static final int USER_ID = 101;
    private UserDbHelper mUserDbHelper;

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        // for whole table
        sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USER_TABLE, USERS);
        // for a single row
        sUriMatcher.addURI(UserContract.CONTENT_AUTHORITY, UserContract.PATH_USER_TABLE + "/#", USER_ID);
    }

    @Override
    public boolean onCreate() {
        mUserDbHelper = new UserDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mUserDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match){
            case USERS:
                cursor = database.query(UserEntry.TABLE_NAME, projection, null,
                        null, null, null, sortOrder);
                break;
            case USER_ID:
                selection = UserEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(UserEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch(match){
            case USER_ID:
                return UserEntry.CONTENT_ITEM_TYPE;
            case USERS:
                return UserEntry.CONTENT_LIST_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI ");
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case USERS:
                return insertPlayer(uri, values);
            default:
                throw new IllegalArgumentException("Can't save the high score");
        }
    }

    private Uri insertPlayer(Uri uri, ContentValues values) {
        String playerName = values.getAsString(UserEntry.COLUMN_USER_NAME);
        int lastScore = values.getAsInteger(UserEntry.COLUMN_LAST_GAME_SCORE);
        int highScore = values.getAsInteger(UserEntry.COLUMN_HIGH_SCORE);

        if (!UserEntry.nameIsValid(playerName)){
            throw new IllegalArgumentException("Validation error: name");
        }
        if (!UserEntry.scoreIsValid(lastScore)) {
            throw new IllegalArgumentException("Validation error: Last game's score'");
        }
        if (!UserEntry.scoreIsValid(highScore)) {
            throw new IllegalArgumentException("Validation error: high score");
        }

        SQLiteDatabase database = mUserDbHelper.getWritableDatabase();
        // new user
        long newRowID = database.insert(UserEntry.TABLE_NAME, null, values);

        if (newRowID == -1) {
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, newRowID);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
