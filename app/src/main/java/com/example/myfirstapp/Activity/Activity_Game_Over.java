package com.example.myfirstapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfirstapp.Audio.Audio_Activity_Game;
import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.R;

/**
 * class that shows game over screen at end of play
 */
public class Activity_Game_Over extends Activity {

    /**
     * button that brings you back to main menu
     */
    Button playAgain;

    /**
     * text: "Game Over"
     */
    TextView gameOverText;

    /**
     * last score
     */
    TextView score;

    /**
     * top highscore
     */
    TextView highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //puts game over screen on the screen
        setContentView(R.layout.activity_game_over);

        //gets rid of notification bar on top of phone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize text based on xml
        playAgain = findViewById(R.id.playAgainButton);
        gameOverText = findViewById(R.id.gameOverMessage);

        score = findViewById(R.id.scoreLabel);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String text = bundle.getString("Score");
        score.setText(text);

        //initialize high scores based on xml
        highScore = (TextView)findViewById(R.id.highScoreLabel);
        int scoreNum = Integer.parseInt(text);

        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highestScore = mypref.getInt("highscore",0);
        if(highestScore > scoreNum)
        {
            highScore.setText("High Score: "+highestScore);
        }
        else{
            highScore.setText("New High score :"+ scoreNum);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore",scoreNum);
            editor.commit();
        }

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stop and release all mediaplayers to recoup resources
                releasePlayers();
                //go back to main menu (start main menu activity)
                Intent intent = new Intent(Activity_Game_Over.this, Activity_Menu_Main.class);
                startActivity(intent);
                finish();
            }
        });

        int x = 1;
        Typeface typeface =  Typeface.DEFAULT;
        playAgain.setTypeface(typeface);
        gameOverText.setTypeface(typeface);


        //scoreLabel = (TextView)findViewById(R.id.scoreLabel);
        //scoreLabel.setText("SCORE: "+ score);
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