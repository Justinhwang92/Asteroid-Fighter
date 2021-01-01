package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Activity_Menu_Instructions extends AppCompatActivity {
    private MediaPlayer myBGM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instruction);

        myBGM = MediaPlayer.create(this, R.raw.bgm_instructions_loop);
        myBGM.setLooping(true);
        myBGM.start();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myBGM!=null) {
                    if(myBGM.isPlaying())
                        myBGM.stop();
                    myBGM.reset();
                    myBGM.release();
                    myBGM=null;
                }
                Activity_Main_Menu.myAudio.playMedia(Audio_Activity_Main_Menu.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Instructions.this, Activity_Main_Menu.class));
            }
        });


    }
}