/*
 ***************************************************
 * see Activity_Menu_Backstory1 for details
 ***************************************************
 */
package ASTEROID_FIGHTER_FREE.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import ASTEROID_FIGHTER_FREE.Audio.Audio_Activity_Menu_Backstory2;
import ASTEROID_FIGHTER_FREE.Audio.Audio_Master_Control;
import ASTEROID_FIGHTER_FREE.R;

public class Activity_Menu_Backstory2 extends AppCompatActivity {
    private Audio_Activity_Menu_Backstory2 myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backstory2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myAudio = new Audio_Activity_Menu_Backstory2(this);
        Audio_Master_Control.checkMuteStatus(myAudio);
        if(!(Audio_Master_Control.myMuted))
        {
            myAudio.getMediaPlayer(Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.BGM_MODES).setVolume((float).5, (float).5);
        }
        myAudio.startMedia(Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.BGM_MODES);
        myAudio.startMedia((Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.BACKSTORY2));

        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //release the resources associated with this audio player
                releasePlayers();
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Game.class));

            }
        });

        findViewById(R.id.previous2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //release the resources associated with this audio player
                releasePlayers();
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Menu_Backstory1.class));

            }
        });

        findViewById(R.id.continue2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //release the resources associated with this audio player
                releasePlayers();
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Menu_Backstory3.class));

            }
        });
    }

    private void releasePlayers()
    {
        Audio_Activity_Menu_Backstory2.releasePlayers();
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
        myAudio = new Audio_Activity_Menu_Backstory2(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();

    }

}

