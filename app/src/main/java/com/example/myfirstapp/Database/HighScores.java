package com.example.myfirstapp.Database;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myfirstapp.R;
import com.example.myfirstapp.Activity.Activity_Game;
import com.example.myfirstapp.Database.UserContract.UserEntry;

public class HighScores extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int USER_LOADER = 0;

    UserCursorAdapter mUserCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        ListView HighScoresList = findViewById(R.id.highScoreList);

        // get database
        mUserCursorAdapter = new UserCursorAdapter(this, null);
        HighScoresList.setAdapter(mUserCursorAdapter);

        // go back to main menu
        final Button MainMenuButton = findViewById(R.id.MainMenuButton);
        MainMenuButton.setOnClickListener(this);

        // loader
        getLoaderManager().initLoader(USER_LOADER, null, this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        i = new Intent(this, Activity_Game.class);
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
