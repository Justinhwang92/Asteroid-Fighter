/*
 * this class represents the activities regarding the game.
 */
package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

public class Activity extends AppCompatActivity {

    private Display display;
    private ActivityAudio myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        myAudio = new ActivityAudio(this);
        Thread audioThread = new Thread(myAudio);
        audioThread.start();

        display = new Display(this, point.x +5000, point.y, myAudio);
        setContentView(display);

        myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.BGM_GAME_LOOP);

        //stop and release the menu mediaplayer to recoup resources
        for(MediaPlayer player : MainActivityAudio.myMainActivityPlayers)
        {
            if(player!=null) {
                if(player.isPlaying())
                    player.stop();
                player.reset();
                player.release();
                player=null;
            }
        }


        //int score = display.theScore;
        //Toast.makeText(Activity.this,"this is a score"+score, Toast.LENGTH_LONG).show();
    }

    public ActivityAudio getMyAudio()
    {
        return myAudio;
    }

    @Override
    protected void onPause() {
        super.onPause();
        display.donePlaying();
        myAudio.stopMedia(ActivityAudio.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }

    @Override
    protected void onResume() {
        super.onResume();
        display.resume();
        myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }

    @Override
    protected void onStop(){
        super.onStop();
        display.donePlaying();
        myAudio.stopMedia(ActivityAudio.MEDIA_PLAYERS.BGM_GAME_LOOP);

    }

    @Override
    protected void onRestart(){
        super.onRestart();

    }

    @Override
    protected void onStart(){
        super.onStart();
        myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }


    public void gameDonePlayAgain() {
        myAudio.stopMedia(ActivityAudio.MEDIA_PLAYERS.BGM_BOSS);
//        ActivityAudio.myActivityPlayers[ActivityAudio.MEDIA_PLAYERS.BGM_BOSS.ordinal()].release();
        Intent intent = new Intent(this, PlayAgain.class);
        startActivity(intent);
        finish();
    }

}