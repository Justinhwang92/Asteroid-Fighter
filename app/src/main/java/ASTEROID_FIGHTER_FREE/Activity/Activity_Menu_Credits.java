package ASTEROID_FIGHTER_FREE.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import ASTEROID_FIGHTER_FREE.Audio.Audio_Activity_Menu_Credits;
import ASTEROID_FIGHTER_FREE.Audio.Audio_Master_Control;
import ASTEROID_FIGHTER_FREE.R;
/**
 * activity that shows when credits is clicked on main menu
 */
public class Activity_Menu_Credits extends AppCompatActivity implements View.OnClickListener {
    /**
     * for audio
     */
    private Audio_Activity_Menu_Credits myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Audio
        myAudio = new Audio_Activity_Menu_Credits(this);
        Audio_Master_Control.checkMuteStatus(myAudio);
        myAudio.startMedia(Audio_Activity_Menu_Credits.MEDIA_PLAYERS.BGM_CREDITS);

        //ok button
        final Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(this);
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
        myAudio.releasePlayers();
        myAudio = new Audio_Activity_Menu_Credits(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Activity_Game_Victory.class);
        myAudio.releasePlayers();
        finish();
        startActivity(i);
    }
}