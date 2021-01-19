package com.example.myfirstapp.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.Database.HighScores;
import com.example.myfirstapp.Database.UserContract.UserEntry;
import com.example.myfirstapp.Audio.Audio_Activity_Game;
import com.example.myfirstapp.R;

/**
 * class that shows game victory screen at end of play
 */
public class Activity_Game_Victory extends Activity implements View.OnClickListener {

    String userName;
    int userHighScore = Activity_Game.HIGH_SCORE;

    TextView gameVictoryTitle;
    TextView gameVictoryText;
    TextView score;
    TextView askUserName;
    EditText userNameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //puts game over screen on the screen
        setContentView(R.layout.activity_game_victory);

        //gets rid of notification bar on top of phone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize text based on xml
        gameVictoryTitle = findViewById(R.id.gameVictoryTitile);
        gameVictoryText = findViewById(R.id.gameVictoryMessage);
        score = findViewById(R.id.scoreLabel);
        askUserName = findViewById(R.id.askUserName);
        userNameEdit = findViewById(R.id.userName);

        final Button playAgainButton = findViewById(R.id.playAgainButton);
        final Button okButton = findViewById(R.id.okButton);

        //Extract the data…
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("Score");
        score.setText(text);

        playAgainButton.setOnClickListener(this);
        okButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        Intent j;

        i = new Intent(this, Activity_Menu_Main.class);
        j = new Intent(this, HighScores.class);

        switch (view.getId()) {
            case R.id.playAgainButton:
                releasePlayers();
                finish();
                startActivity(i);
                break;

            case R.id.okButton:
                try {
                    releasePlayers();
                    saveScore();
                    finish();
                    startActivity(j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.myWebSearch_bt:
                Uri uri = Uri.parse("https://www.tutordudes.com/intern"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }

    private void saveScore(){
        userName = userNameEdit.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_USER_NAME, userName);
        values.put(UserEntry.COLUMN_HIGH_SCORE, userHighScore);

        // for the future use
        values.put(UserEntry.COLUMN_LAST_GAME_SCORE, userHighScore);

        // user details
        Uri newUri = getContentResolver().insert(UserEntry.CONTENT_URI, values);

        // validation
        if (newUri != null) {
            // success
            Toast.makeText(getApplicationContext(), "Thanks, " + userNameEdit.getText().toString() + "!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "INVALID", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void releasePlayers()
    {
        Audio_Activity_Game.releasePlayers(this);
    }

}