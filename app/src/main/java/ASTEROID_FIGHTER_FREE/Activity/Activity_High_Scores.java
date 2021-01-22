package ASTEROID_FIGHTER_FREE.Activity;

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
import ASTEROID_FIGHTER_FREE.Audio.Audio_Activity_Game_Over;
import ASTEROID_FIGHTER_FREE.Audio.Audio_Activity_High_Scores;
import ASTEROID_FIGHTER_FREE.Audio.Audio_Master_Control;
import ASTEROID_FIGHTER_FREE.Database.UserCursorAdapter;
import ASTEROID_FIGHTER_FREE.R;
import ASTEROID_FIGHTER_FREE.Database.UserContract.UserEntry;

public class Activity_High_Scores extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int USER_LOADER = 0;

    UserCursorAdapter mUserCursorAdapter;

    Audio_Activity_High_Scores myAudio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAudio = new Audio_Activity_High_Scores(this);
        Audio_Master_Control.checkMuteStatus(myAudio);
        myAudio.startMedia(Audio_Activity_High_Scores.MEDIA_PLAYERS.BGM_CREDITS_LOOP);
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
        myAudio.releasePlayers();
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

    //called when application stops
    @Override
    protected void onPause() {
        super.onPause();
        myAudio.pauseLoopers();
    }
    //called when application starts/resumes
    @Override
    protected void onResume() {
        super.onResume();
        myAudio.resumeLoopers();
    }

    //called when application stops
    @Override
    protected void onStop(){
        super.onStop();
    }

    //i think this is called when display turns back on.
    //if you press the power button and turn the emulator off and press again to start
    //it calls this method. not sure if power button on emulator just turns off screen or turns
    //off emulator
    @Override
    protected void onRestart(){
        super.onRestart();
        myAudio.releasePlayers();
        myAudio = new Audio_Activity_High_Scores(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();

    }
}
