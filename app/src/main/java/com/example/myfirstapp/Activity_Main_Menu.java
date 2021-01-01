package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * main activity starts when the game starts - the menu
 */
public class Activity_Main_Menu extends AppCompatActivity {

    public Audio_Activity_Main_Menu myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);


        myAudio = new Audio_Activity_Main_Menu(this);

        findViewById(R.id.instruction).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                myAudio.playMedia(Audio_Activity_Main_Menu.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showInstructions();
            }
        });

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.playMedia(Audio_Activity_Main_Menu.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showGame();
            }
        });

        findViewById(R.id.creditsid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.playMedia(Audio_Activity_Main_Menu.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showCredits();
            }
        });

        myAudio.playMedia(Audio_Activity_Main_Menu.MEDIA_PLAYERS.BGM_MENU);
    }
    public void showInstructions()
    {
        Intent intent = new Intent(this, Activity_Menu_Instructions.class);
        startActivity(intent);
        myAudio.stopMedia(Audio_Activity_Main_Menu.MEDIA_PLAYERS.BGM_MENU);

    }

    public void showCredits()
    {
        Intent intent = new Intent(this, Activity_Menu_Credits.class);
        startActivity(intent);
        myAudio.stopMedia(Audio_Activity_Main_Menu.MEDIA_PLAYERS.BGM_MENU);

    }

    public void showGame()
    {
        startActivity(new Intent(Activity_Main_Menu.this, Activity_Game.class));
    }


}