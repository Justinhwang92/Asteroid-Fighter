package com.example.myfirstapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myfirstapp.Audio.Audio_Activity_Menu_Main;
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
        Audio_Master_Control.checkMuteStatus(this);

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

    //called when application stops
    @Override
    protected void onPause() {
        super.onPause();
        myAudio.pauseLoopers();
    }
    //called when application starts/resumes
    @Override
    protected void onResume() {
        super.onResume();
        myAudio.resumeLoopers();
    }

    //called when application stops
    @Override
    protected void onStop(){
        super.onStop();
    }

    //i think this is called when display turns back on.
    //if you press the power button and turn the emulator off and press again to start
    //it calls this method. not sure if power button on emulator just turns off screen or turns
    //off emulator
    @Override
    protected void onRestart(){
        super.onRestart();
        myAudio.releasePlayers(this);
        myAudio = new Audio_Activity_Menu_Modes(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();

    }
}

