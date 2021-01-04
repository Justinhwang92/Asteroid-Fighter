package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * main activity starts when the game starts - the menu
 */
public class Activity_Menu_Main extends AppCompatActivity {

    public static Audio_Activity_Menu_Main myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);
        myAudio = new Audio_Activity_Menu_Main(this);

        //instructions button
        findViewById(R.id.instruction).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                myAudio.playMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showInstructions();
            }
        });

        //play button
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.playMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showModes();
            }
        });

        //credits button
        findViewById(R.id.creditsid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.playMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showCredits();
            }
        });

        //plays menu music
        myAudio.playMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.BGM_MENU);
    }

    //self-explanatory methods to show different screens

    public void showInstructions()
    {
        startActivity(new Intent(this, Activity_Menu_Instructions.class));
        myAudio.stopMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.BGM_MENU);

    }

    public void showCredits()
    {
        startActivity(new Intent(this, Activity_Menu_Credits.class));
        myAudio.stopMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.BGM_MENU);
    }

    public void showModes() {
        startActivity(new Intent(this, Activity_Menu_Modes.class));
        myAudio.stopMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.BGM_MENU);
    }
}