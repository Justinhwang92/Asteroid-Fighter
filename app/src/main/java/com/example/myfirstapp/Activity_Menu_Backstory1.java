package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Activity_Menu_Backstory1 extends AppCompatActivity {
    /**
     * to play audio
     */
    private MediaPlayer myBGM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set screen view to this
        setContentView(R.layout.activity_backstory1);
        //don't show notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize audio
        myBGM = MediaPlayer.create(this, R.raw.bgm_credits_loop);
        if(Audio_Master_Control.myMuted)
        {
            myBGM.setVolume(0, 0);
        }
        else
        {
            myBGM.setVolume(1, 1);
        }
        myBGM.setLooping(true);
        myBGM.start();

        //onclick listener for skip (go straight to play)
        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //release the resources associated with this audio player
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                //show main game activity
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Game.class));
            }
        });

        //next backstory
        findViewById(R.id.continue1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //release the resources associated with this audio player
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                //show main game activity
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Menu_Backstory2.class));
            }
        });

        //back to main
        findViewById(R.id.previous1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Menu_Modes.class));
            }
        });
    }
}

