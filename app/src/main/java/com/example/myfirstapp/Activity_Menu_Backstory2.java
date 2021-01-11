/*
 ***************************************************
 * see Activity_Menu_Backstory1 for details
 ***************************************************
 */
package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Activity_Menu_Backstory2 extends AppCompatActivity {
    private MediaPlayer myNarration;
    private MediaPlayer myBGM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backstory2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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


        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Game.class));
            }
        });

        findViewById(R.id.previous2).setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Menu_Backstory1.class));
            }
        });

        findViewById(R.id.continue2).setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Menu_Backstory3.class));
            }
        });
    }
}

