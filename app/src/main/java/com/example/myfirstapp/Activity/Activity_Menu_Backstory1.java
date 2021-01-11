package com.example.myfirstapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.R;

public class Activity_Menu_Backstory1 extends AppCompatActivity {
    /**
     * to play audio
     */
    private MediaPlayer myNarration;
    private MediaPlayer myBGM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set screen view to this
        setContentView(R.layout.activity_backstory1);
        //don't show notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize audio
        myNarration = MediaPlayer.create(this, R.raw.backstory1);
        myBGM = MediaPlayer.create(this, R.raw.bgm_modes_loop);
        if(Audio_Master_Control.myMuted)
        {
            myNarration.setVolume(0, 0);
            myBGM.setVolume(0, 0);
        }
        else
        {
            myNarration.setVolume(1, 1);
            myBGM.setVolume((float)0.5, (float)0.5);
        }
        myNarration.start();
        myBGM.start();
        //onclick listener for skip (go straight to play)
        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //release the resources associated with this audio player
                if(myNarration !=null) {
                    if(myNarration.isPlaying())
                        myNarration.stop();
                    myNarration.reset();
                    myNarration.release();
                    myNarration =null;
                }
                if(myBGM !=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM =null;
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
                if(myNarration !=null) {
                    if(myNarration.isPlaying())
                        myNarration.stop();
                    myNarration.reset();
                    myNarration.release();
                    myNarration =null;
                }
                if(myBGM !=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM =null;
                }
                //show main game activity
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Menu_Backstory2.class));
            }
        });

        //back to main
        findViewById(R.id.previous1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myNarration !=null) {
                    if(myNarration.isPlaying())
                        myNarration.stop();
                    myNarration.reset();
                    myNarration.release();
                    myNarration =null;
                }
                if(myBGM !=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM =null;
                }
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Menu_Modes.class));
            }
        });
    }
}

