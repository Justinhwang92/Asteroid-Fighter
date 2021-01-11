package com.example.myfirstapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myfirstapp.Audio.Audio_Activity_Menu_Main;
import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.R;

public class Activity_Menu_Instructions extends AppCompatActivity {
    private MediaPlayer myBGM;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instruction);

        myBGM = MediaPlayer.create(this, R.raw.bgm_instructions_loop);
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
                startActivity(new Intent(Activity_Menu_Instructions.this, Activity_Menu_Main.class));
                Audio_Activity_Menu_Main.releasePlayers();
            }
        });
    }
}