package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
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
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);


       // scorelabel.setText

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        playAgain = (Button) findViewById(R.id.playAgainButton);
        gameOverText = (TextView)findViewById(R.id.gameOverMessage);

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

        scoreLabel = (TextView)findViewById(R.id.scoreLabel);

        scoreLabel.setText("SCORE: "+ score);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}