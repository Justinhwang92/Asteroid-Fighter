package ASTEROID_FIGHTER_FREE.Database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class UserContract {
    private static final String LOG_TAG = UserContract.class.getSimpleName();
    public static final String CONTENT_AUTHORITY = "ASTEROID_FIGHTER_FREE";

    // The base of all uris using the content Authority
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // The possible paths
    public static final String PATH_USER_TABLE = "users";

    private UserContract(){}

    // Inner class that defines the table contents of the password table
    public static final class UserEntry implements BaseColumns{
        // Append URI parts together
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USER_TABLE);

        // for single user
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER_TABLE;

        // for a list of users
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USER_TABLE;

        // table name
        public static final String TABLE_NAME = "users";

        // user's ID
        public static final String _ID = BaseColumns._ID;

        // user's name
        public static final String COLUMN_USER_NAME = "name";

        // last game's score
        public static final String COLUMN_LAST_GAME_SCORE = "last_game_score";

        // user's highest score.
        public static final String COLUMN_HIGH_SCORE = "high_score";

        // name validation
        public static boolean nameIsValid(String name) {
            if (name != null && !name.isEmpty()) {
                return true;
            }
            return false;
        }
        // score validation
        public static boolean scoreIsValid(int score) {
            if (score >= 0) {
                return true;
            }
            return false;
        }
    }
}
