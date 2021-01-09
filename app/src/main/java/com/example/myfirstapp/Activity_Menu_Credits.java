package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * activity that shows when credits is clicked on main menu
 */
public class Activity_Menu_Credits extends AppCompatActivity {
    /**
     * for audio
     */
    private MediaPlayer myBGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
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
                Activity_Menu_Main.myAudio.playMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Credits.this, Activity_Menu_Main.class));
                Audio_Activity_Menu_Main.releasePlayers();
            }
        });

    }
}