package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class PlayAgain extends Activity {

    Button playAgain;
    TextView gameOverText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        playAgain = (Button) findViewById(R.id.playAgainButton);
        gameOverText = (TextView)findViewById(R.id.gameOverMessage);

        //play again button onclick listener
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgain.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Typeface typeface =  Typeface.DEFAULT;
        playAgain.setTypeface(typeface);
        gameOverText.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}