package ASTEROID_FIGHTER_FREE.Database;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import ASTEROID_FIGHTER_FREE.Activity.Activity_Menu_Main;
import ASTEROID_FIGHTER_FREE.R;
import ASTEROID_FIGHTER_FREE.Database.UserContract.UserEntry;

public class HighScores extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int USER_LOADER = 0;

    UserCursorAdapter mUserCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ListView HighScoresList = findViewById(R.id.highScoreList);

        // get database
        mUserCursorAdapter = new UserCursorAdapter(this, null);
        HighScoresList.setAdapter(mUserCursorAdapter);

        // go back to main menu
        findViewById(R.id.backToMain).setOnClickListener(this);

        // loader
        getLoaderManager().initLoader(USER_LOADER, null, this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        i = new Intent(this, Activity_Menu_Main.class);
        finish();
        startActivity(i);
    }
    @Override
    public void onBackPressed(){
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // displaying sections
        String[] projection = {
                UserEntry._ID,
                UserEntry.COLUMN_USER_NAME,
                UserEntry.COLUMN_HIGH_SCORE,
                UserEntry.COLUMN_LAST_GAME_SCORE
        };

        // sorting order
        String sortOrder = UserEntry.COLUMN_HIGH_SCORE + " DESC";

        // loader
        return new CursorLoader(this,
                UserEntry.CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
        );

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mUserCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mUserCursorAdapter.swapCursor(null);
    }
}
