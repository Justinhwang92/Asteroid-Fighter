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


public class Activity_Game_Over extends Activity {

    Button playAgain;
    TextView gameOverText;
    TextView scoreLabel;
    TextView score;
    TextView highScore;
    TextView highScore2;
    TextView highScore3;
    int lastScore;
    int best;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

       // scorelabel.setText

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        playAgain = (Button) findViewById(R.id.playAgainButton);
        gameOverText = (TextView)findViewById(R.id.gameOverMessage);


        score = (TextView)findViewById(R.id.scoreLabel);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String text = bundle.getString("Score");
        score.setText(text);

        highScore = (TextView)findViewById(R.id.highScoreLabel);
        highScore2 = (TextView)findViewById(R.id.highScoreLabel);
        highScore3 = (TextView)findViewById(R.id.highScoreLabel);

        int scoreNum = Integer.parseInt(text);
        boolean changed = false;

        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highestScore = mypref.getInt("highscore",0);
        int highestScore2 = mypref.getInt("highscore2",0);
        int highestScore3 = mypref.getInt("highscore3",0);
        //highscore1
        if(highestScore > scoreNum)
        {
            highScore.setText("1st: "+highestScore);
        }
        else if(changed==false){
            highScore3.setText("3rd: "+ highestScore2);
            highScore2.setText("2nd: "+ highestScore);
            highScore.setText("1st: "+ scoreNum);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore",scoreNum);
            editor.commit();
            changed=true;
        }
        //highscore2
        SharedPreferences mypref2 = getPreferences(MODE_PRIVATE);

        if(highestScore2 > scoreNum )
        {
            highScore2.setText("2nd: "+highestScore2);
        }
        else if(changed==false){
            highScore3.setText("3rd:" + highestScore2);
            highScore2.setText("2nd:"+ scoreNum);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore2",scoreNum);
            editor.commit();
            changed=true;
        }
        //highscore3;

        SharedPreferences mypref3 = getPreferences(MODE_PRIVATE);

        if(highestScore3>scoreNum)
        {
            highScore3.setText("3rd: "+highestScore3);
        }
        else if(changed==false){
            highScore.setText("3rd: "+ scoreNum);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore3",scoreNum);
            editor.commit();
            changed=true;
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