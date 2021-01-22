package ASTEROID_FIGHTER_FREE.Database;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import ASTEROID_FIGHTER_FREE.R;
import ASTEROID_FIGHTER_FREE.Database.UserContract.UserEntry;


public class UserCursorAdapter extends CursorAdapter {

    public UserCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.score_list, parent, false);
    }

    @Override
    public void bindView(View listItemView, Context context, Cursor cursor) {
        // Field of the listItemView
        TextView nameText = (TextView) listItemView.findViewById(R.id.name_text);
        TextView scoreText = (TextView)listItemView.findViewById(R.id.score_text);

        // Get pet properties from the database
        String playerName = cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_USER_NAME));
        int highScore = cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_HIGH_SCORE));
        int lastScore = cursor.getInt(cursor.getColumnIndex(UserEntry.COLUMN_LAST_GAME_SCORE));

        // Fill the listItemView with the values
        nameText.setText(playerName);
        scoreText.setText(String.valueOf(highScore));
    }
}
