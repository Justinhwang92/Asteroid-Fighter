package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class PlayAgain extends Activity {

    Button playAgain;
    TextView gameOverText;
    TextView scoreLabel;
    TextView score;
    TextView highScore;
    int lastScore;
    int best;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);

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
                for(MediaPlayer player : ActivityAudio.myActivityPlayers)
                {
                    if(player!=null) {
                        if(player.isPlaying())
                            player.stop();
                        player.reset();
                        player.release();
                        player=null;
                    }
                }

                Intent intent = new Intent(PlayAgain.this, MainActivity.class);
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