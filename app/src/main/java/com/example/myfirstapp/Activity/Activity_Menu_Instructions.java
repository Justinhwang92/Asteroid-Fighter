package com.example.myfirstapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myfirstapp.Audio.Audio_Activity_Menu_Instructions;
import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.R;

public class Activity_Menu_Instructions extends AppCompatActivity {
    private Audio_Activity_Menu_Instructions myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instruction);

        myAudio = new Audio_Activity_Menu_Instructions(this);
        if(Audio_Master_Control.myMuted)
        {
            Audio_Master_Control.muteAllPlayers(this);
        }
        else
        {
            Audio_Master_Control.unmuteAllPlayers(this);
        }

        myAudio.startMedia(Audio_Activity_Menu_Instructions.MEDIA_PLAYERS.BGM_INSTRUCTIONS);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Instructions.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Instructions.this, Activity_Menu_Main.class));
                releasePlayers();
            }
        });
    }

    private void releasePlayers()
    {
        Audio_Activity_Menu_Instructions.releasePlayers(this);
    }

}