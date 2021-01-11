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
import com.example.myfirstapp.Audio.Audio_Activity_Menu_Backstory3;
import com.example.myfirstapp.Audio.Audio_Master_Control;
import com.example.myfirstapp.R;

public class Activity_Menu_Backstory3 extends AppCompatActivity {
    private Audio_Activity_Menu_Backstory3 myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backstory3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myAudio = new Audio_Activity_Menu_Backstory3(this);

        if(Audio_Master_Control.myMuted)
        {
            Audio_Master_Control.muteAllPlayers(this);
        }
        else
        {
            Audio_Master_Control.unmuteAllPlayers(this);
            myAudio.getMediaPlayer(Audio_Activity_Menu_Backstory3.MEDIA_PLAYERS.BGM_MODES).setVolume((float).5, (float).5);
        }

        myAudio.startMedia(Audio_Activity_Menu_Backstory3.MEDIA_PLAYERS.BGM_MODES);
        myAudio.startMedia((Audio_Activity_Menu_Backstory3.MEDIA_PLAYERS.BACKSTORY3));

        findViewById(R.id.previous3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Backstory3.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Backstory3.this, Activity_Menu_Backstory2.class));
                releasePlayers();
            }
        });

        findViewById(R.id.playNow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudio.startMedia(Audio_Activity_Menu_Backstory3.MEDIA_PLAYERS.SFX_MENU_CLICK);
                startActivity(new Intent(Activity_Menu_Backstory3.this, Activity_Game.class));
                releasePlayers();
            }
        });
    }

    private void releasePlayers()
    {
        Audio_Activity_Menu_Backstory3.releasePlayers(this);
    }
}

