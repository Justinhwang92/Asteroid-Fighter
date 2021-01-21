package com.example.myfirstapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myfirstapp.Audio.Audio_Activity_Menu_Backstory1;
import com.example.myfirstapp.Audio.Audio_Activity_Menu_Main;
import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.R;

public class Activity_Menu_Backstory1 extends AppCompatActivity {
    /**
     * to play audio
     */
    private Audio_Activity_Menu_Backstory1 myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set screen view to this
        setContentView(R.layout.activity_backstory1);
        //don't show notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize audio
        myAudio = new Audio_Activity_Menu_Backstory1(this);

        Audio_Master_Control.checkMuteStatus(this);
        if(!(Audio_Master_Control.myMuted))
        {
            myAudio.getMediaPlayer(Audio_Activity_Menu_Backstory1.MEDIA_PLAYERS.BGM_MODES).setVolume((float).5, (float).5);
        }
        myAudio.startMedia(Audio_Activity_Menu_Backstory1.MEDIA_PLAYERS.BGM_MODES);
        myAudio.startMedia((Audio_Activity_Menu_Backstory1.MEDIA_PLAYERS.BACKSTORY1));

        //onclick listener for skip (go straight to play)
        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myAudio.startMedia(Audio_Activity_Menu_Backstory1.MEDIA_PLAYERS.SFX_MENU_CLICK);
                //release the resources associated with this audio player
                releasePlayers();
                //show main game activity
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Game.class));

            }
        });

        //next backstory
        findViewById(R.id.continue1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Backstory1.MEDIA_PLAYERS.SFX_MENU_CLICK);
                //release the resources associated with this audio player
                releasePlayers();
                //show main game activity
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Menu_Backstory2.class));

            }
        });

        //back to main
        findViewById(R.id.previous1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Backstory1.MEDIA_PLAYERS.SFX_MENU_CLICK);
                //release the resources associated with this audio player
                releasePlayers();
                startActivity(new Intent(Activity_Menu_Backstory1.this, Activity_Menu_Modes.class));

            }
        });
    }

    private void releasePlayers()
    {
        Audio_Activity_Menu_Backstory1.releasePlayers(this);
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
        myAudio = new Audio_Activity_Menu_Backstory1(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();

    }
}

