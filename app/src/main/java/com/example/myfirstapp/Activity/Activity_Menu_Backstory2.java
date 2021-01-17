/*
 ***************************************************
 * see Activity_Menu_Backstory1 for details
 ***************************************************
 */
package com.example.myfirstapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.myfirstapp.Audio.Audio_Activity_Menu_Backstory2;
import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.R;

public class Activity_Menu_Backstory2 extends AppCompatActivity {
    private Audio_Activity_Menu_Backstory2 myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backstory2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myAudio = new Audio_Activity_Menu_Backstory2(this);
        Audio_Master_Control.checkMuteStatus(this);
        myAudio.getMediaPlayer(Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.BGM_MODES).setVolume((float).5, (float).5);
        myAudio.startMedia(Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.BGM_MODES);
        myAudio.startMedia((Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.BACKSTORY2));

        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Game.class));
                releasePlayers();
            }
        });

        findViewById(R.id.previous2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Menu_Backstory1.class));
                releasePlayers();
            }
        });

        findViewById(R.id.continue2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Backstory2.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Backstory2.this, Activity_Menu_Backstory3.class));
                releasePlayers();
            }
        });
    }

    private void releasePlayers()
    {
        Audio_Activity_Menu_Backstory2.releasePlayers(this);
    }


}

