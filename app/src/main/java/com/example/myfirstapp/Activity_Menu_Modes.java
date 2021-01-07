package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * shows difficulty levels and endless
 */
public class Activity_Menu_Modes extends AppCompatActivity {
    private MediaPlayer myBGM;
    public static String mode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myBGM = MediaPlayer.create(this, R.raw.bgm_credits_loop);
        myBGM.setLooping(true);
        myBGM.start();

        //below: different onclick listeners for each difficulty/endless

        findViewById(R.id.novice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = "novice";
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Backstory1.class));
            }
        });

        findViewById(R.id.intermediate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = "intermediate";
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Backstory1.class));
            }
        });

        findViewById(R.id.advanced).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = "advanced";
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Backstory1.class));
            }
        });

        findViewById(R.id.endless).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = "endless";
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Backstory1.class));
            }
        });

        findViewById(R.id.backToMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Main.class));
            }
        });
    }
}

