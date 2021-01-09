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

public class Activity_Menu_Backstory3 extends AppCompatActivity {
    private MediaPlayer myBGM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backstory3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        findViewById(R.id.previous3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Backstory3.this, Activity_Menu_Backstory2.class));
            }
        });

        findViewById(R.id.playNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Backstory3.this, Activity_Game.class));
            }
        });
    }
}

