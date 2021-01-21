package ASTEROID_FIGHTER_FREE.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import ASTEROID_FIGHTER_FREE.Audio.Audio_Activity_Menu_Instructions;
import ASTEROID_FIGHTER_FREE.Audio.Audio_Master_Control;
import ASTEROID_FIGHTER_FREE.R;

public class Activity_Menu_Instructions extends AppCompatActivity {
    private Audio_Activity_Menu_Instructions myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_instruction);

        myAudio = new Audio_Activity_Menu_Instructions(this);
        Audio_Master_Control.checkMuteStatus(this);

        myAudio.startMedia(Audio_Activity_Menu_Instructions.MEDIA_PLAYERS.BGM_INSTRUCTIONS);

        findViewById(R.id.backToMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Instructions.MEDIA_PLAYERS.SFX_MENU_CLICK);
                //release the resources associated with this audio player
                releasePlayers();
                startActivity(new Intent(Activity_Menu_Instructions.this, Activity_Menu_Main.class));

            }
        });
    }

    private void releasePlayers()
    {
        Audio_Activity_Menu_Instructions.releasePlayers(this);
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
        myAudio = new Audio_Activity_Menu_Instructions(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();

    }
}