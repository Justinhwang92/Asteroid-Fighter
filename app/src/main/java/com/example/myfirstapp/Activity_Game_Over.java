package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

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

    /**
     * 2nd top highscore
     */
    TextView highScore2;

    /**
     * 3rd top highscore
     */
    TextView highScore3;

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
        highScore = findViewById(R.id.highScoreLabel);
        highScore2 = findViewById(R.id.highScoreLabel2);
        highScore3 = findViewById(R.id.highScoreLabel3);

        int scoreNum = Integer.parseInt(text);
        boolean changed = false;

        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        SharedPreferences mypref2 = getPreferences(MODE_PRIVATE);
        SharedPreferences mypref3 = getPreferences(MODE_PRIVATE);
        int highestScore = mypref.getInt("highscore",0);
        int highestScore2 = mypref.getInt("highscore2",0);
        int highestScore3 = mypref.getInt("highscore3",0);

        //highscore1
        if(highestScore > scoreNum)
        {
            highScore.setText("1st: "+highestScore);
        }
        else if(!changed){
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore",scoreNum);
            editor.commit();
            changed=true;
            highScore3.setText("3rd: "+ highestScore2);
            highScore2.setText("2nd: "+ highestScore);
            highScore.setText("1st: "+ scoreNum);

        }
        //highscore2

        if(highestScore2 > scoreNum )
        {
            highScore2.setText("2nd: "+highestScore2);
        }
        else if(!changed){
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore2",scoreNum);
            editor.commit();
            changed=true;
            highScore3.setText("3rd:" + highestScore2);
            highScore2.setText("2nd:"+ scoreNum);

        }
        //highscore3;
        if(highestScore3>scoreNum)
        {
            highScore3.setText("3rd: "+highestScore3);
        }
        else if(changed==false){
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore3",scoreNum);
            editor.commit();
            changed=true;
            highScore.setText("3rd: "+ scoreNum);

        }
        changed=false;

        /*
        SharedPreferences preferences= getSharedPreferences("PREFS",0);
        lastScore = preferences.getInt("lastScore",0);
        best=preferences.getInt("lastScore",0);

        if(lastScore > best)
        {
            best = lastScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("lastScore",best);
            editor.apply();
        }
        highScore.setText("High Score: "+best);
*/
        //play again button onclick listener
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //stop and release all mediaplayers to recoup resources
                //go back to main menu (start main menu activity)
                Audio_Activity_Game.releasePlayers();
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
}