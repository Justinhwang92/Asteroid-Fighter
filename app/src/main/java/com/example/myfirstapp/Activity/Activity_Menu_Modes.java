package com.example.myfirstapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myfirstapp.Audio.Audio_Activity_Menu_Modes;
import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.R;

/**
 * shows difficulty levels and endless
 */
public class Activity_Menu_Modes extends AppCompatActivity {
    private Audio_Activity_Menu_Modes myAudio;

    public static String mode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myAudio = new Audio_Activity_Menu_Modes(this);
        if(Audio_Master_Control.myMuted)
        {
            Audio_Master_Control.muteAllPlayers(this);
        }
        else
        {
            Audio_Master_Control.unmuteAllPlayers(this);
        }

        myAudio.startMedia(Audio_Activity_Menu_Modes.MEDIA_PLAYERS.BGM_MODES);

        //below: different onclick listeners for each difficulty/endless

        findViewById(R.id.novice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Modes.MEDIA_PLAYERS.SFX_MENU_CLICK);
                mode = "novice";
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Backstory1.class));
                releasePlayers();
            }
        });

        findViewById(R.id.intermediate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Modes.MEDIA_PLAYERS.SFX_MENU_CLICK);
                mode = "intermediate";
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Backstory1.class));
                releasePlayers();
            }
        });

        findViewById(R.id.advanced).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Modes.MEDIA_PLAYERS.SFX_MENU_CLICK);
                mode = "advanced";
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Backstory1.class));
                releasePlayers();
            }
        });

        findViewById(R.id.endless).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Modes.MEDIA_PLAYERS.SFX_MENU_CLICK);
                mode = "endless";
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Backstory1.class));
                releasePlayers();
            }
        });

        findViewById(R.id.backToMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Modes.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Modes.this, Activity_Menu_Main.class));
                releasePlayers();
            }
        });
    }

    private void releasePlayers()
    {
        Audio_Activity_Menu_Modes.releasePlayers(this);
    }
}

