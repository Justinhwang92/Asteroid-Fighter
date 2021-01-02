package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Activity_Menu_Backstory1 extends AppCompatActivity {
    private MediaPlayer myBGM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backstory1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myBGM = MediaPlayer.create(this, R.raw.bgm_credits_loop);
        myBGM.setLooping(true);
        myBGM.start();

        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Game.class));
            }
        });

        findViewById(R.id.continue1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Menu_Backstory2.class));
            }
        });

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

