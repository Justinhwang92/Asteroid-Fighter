package com.example.myfirstapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.Audio.Audio_Activity_Game;
import com.example.myfirstapp.Audio.Audio_Activity_Menu_Main;
import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.Database.HighScores;
import com.example.myfirstapp.R;

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


        //Movement of the logo with name
        final TextView gamename = (TextView) findViewById(R.id.Name1);
        gamename.setSelected(true);

        //To get the height and width of the running screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        //moves only once
        ObjectAnimator animator = ObjectAnimator.ofFloat(gamename, "translationX",width/3 - width/24);
        animator.setDuration(3000);
        animator.start();

        //instructions button
        findViewById(R.id.instruction).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                myAudio.startMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showInstructions();
            }
        });

        //play button
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showModes();
            }
        });

        //credits button
        findViewById(R.id.creditsid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showCredits();
            }
        });

        //mute/unmute
        findViewById(R.id.mainaudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView audio = findViewById(R.id.mainaudio);
                if(Audio_Master_Control.myMuted)
                {
                    audio.setImageResource(R.drawable.unmuted_audio);
                    unmuteAudio();
                }
                else
                {
                    audio.setImageResource(R.drawable.muted_audio);
                    muteAudio();
                }
            }
        });

        // high score
        findViewById(R.id.HighScoresButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.SFX_MENU_CLICK);
                showHighScores();
            }
        });



        if(Audio_Master_Control.myMuted)
        {
            muteAudio();
            ImageView audio = findViewById(R.id.mainaudio);
            audio.setImageResource(R.drawable.muted_audio);
        }
        //plays menu music
        myAudio.startMedia(Audio_Activity_Menu_Main.MEDIA_PLAYERS.BGM_MENU);

    }

    //self-explanatory methods to show different screens

    public void showInstructions()
    {
        startActivity(new Intent(this, Activity_Menu_Instructions.class));
        releasePlayers();
    }

    public void showCredits()
    {
        startActivity(new Intent(this, Activity_Menu_Credits.class));
        releasePlayers();
    }

    public void showModes() {
        startActivity(new Intent(this, Activity_Menu_Modes.class));
        releasePlayers();
    }

    public void showHighScores() {
        startActivity(new Intent(this, HighScores.class));
        releasePlayers();
    }

    public void muteAudio()
    {
        Audio_Master_Control.muteAllPlayers(this);
    }

    public void unmuteAudio()
    {
        Audio_Master_Control.unmuteAllPlayers(this);
    }

    private void releasePlayers()
    {
        Audio_Activity_Menu_Main.releasePlayers(this);
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
        myAudio = new Audio_Activity_Menu_Main(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();

    }
}