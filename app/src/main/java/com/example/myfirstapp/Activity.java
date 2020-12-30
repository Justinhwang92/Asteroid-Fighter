/*
 * this class represents the activities regarding the game.
 */
package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Activity extends AppCompatActivity {

    private Display display;
    private MediaPlayer myConstantSong;
    private Thread thread;

    //Question part
    private MathProblems myMP;
    private String myQuestion;
    private int myAns;
    private List<Integer> myChoices;
    private boolean isBossStage = false;

    //Buttons
    Button mybutton1;
    Button mybutton2;
    Button mybutton3;
    Button mybutton4;


    private ActivityAudio myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_flight_button);

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

        //Layout on top of surface view
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View secondLayerView = LayoutInflater.from(this).inflate(R.layout.activity_flight_button, null, false);
        addContentView(secondLayerView, lp);

        //For math problem class
        //starts with easy questions
        setQuestionAnswerOnDisplay();

        //int score = display.theScore;
        //Toast.makeText(Activity.this,"this is a score"+score, Toast.LENGTH_LONG).show();
    }

    public void setQuestionAnswerOnDisplay(){
        //Initial question to be displayed
        myMP = new MathProblems(isBossStage);

        if(!display.isBossStage){
            myQuestion = myMP.getEasyQuestions();
            myChoices = new ArrayList<>(myMP.getEasyAnswers());
        }
        //else means its boss stage
        else{
            myQuestion = myMP.getHardQuestions();
            myChoices = new ArrayList<>(myMP.getHardAnswers());
        }

        myAns = myMP.getAnswer();
        intitializeQuestionAnswer();
    }

    public void intitializeQuestionAnswer(){
        TextView queText = (TextView) findViewById(R.id.id_question);
        queText.setText(myQuestion);

        Collections.shuffle(myChoices);

        mybutton1 = (Button) findViewById(R.id.ans_button1);
        mybutton1.setText(myChoices.get(0)+"");

        mybutton2 = (Button) findViewById(R.id.ans_button2);
        mybutton2.setText(myChoices.get(1)+"");

        mybutton3 = (Button) findViewById(R.id.ans_button3);
        mybutton3.setText(myChoices.get(2)+"");

        mybutton4 = (Button) findViewById(R.id.ans_button4);
        mybutton4.setText(myChoices.get(3)+"");
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


    public void onClick(View view) {
            switch (view.getId()){

                case R.id.ans_button1:
                    if(mybutton1.getText().equals(myAns+"")){
                        display.getFlightObj().hasShot = true;
                        setQuestionAnswerOnDisplay();
                    }else{
                        break;
                    }

                    break;

                case R.id.ans_button2:
                    if(mybutton2.getText().equals(myAns+"")){
                        display.getFlightObj().hasShot = true;
                        setQuestionAnswerOnDisplay();
                    }else{
                        break;
                    }
                    break;

                case R.id.ans_button3:
                    if(mybutton3.getText().equals(myAns+"")){
                        display.getFlightObj().hasShot = true;
                        setQuestionAnswerOnDisplay();
                    }else{
                        break;
                    }
                    break;

                case R.id.ans_button4:
                    if(mybutton4.getText().equals(myAns+"")){
                        display.getFlightObj().hasShot = true;
                        setQuestionAnswerOnDisplay();
                    }else{
                        break;
                    }
                    break;

                default:
                    break;
            }
    }
    /**
     *
     */

}