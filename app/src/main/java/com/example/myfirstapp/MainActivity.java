package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * main activity starts when the game starts - the menu
 */
public class MainActivity extends AppCompatActivity {

    private MainActivityAudio myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        myAudio = new MainActivityAudio(this);
        myAudio.run();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        myAudio.playMedia(MainActivityAudio.MEDIA_PLAYERS.BGM_MENU);

        findViewById(R.id.instruction).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                myAudio.playMedia(MainActivityAudio.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showInstructions();
            }
        });

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.playMedia(MainActivityAudio.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(MainActivity.this, Activity.class));
            }
        });

        findViewById(R.id.creditsid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.playMedia(MainActivityAudio.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showCredits();
            }
        });



    }
    public void showInstructions()
    {
        Intent intent = new Intent(this, Instructions.class);
        startActivity(intent);
        myAudio.stopMedia(MainActivityAudio.MEDIA_PLAYERS.BGM_MENU);
    }

    public void showCredits()
    {
        Intent intent = new Intent(this,Credits.class);
        startActivity(intent);
        myAudio.stopMedia(MainActivityAudio.MEDIA_PLAYERS.BGM_MENU);
    }


}