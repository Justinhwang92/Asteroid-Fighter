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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activity_Game extends AppCompatActivity {

    private Game_Display gameDisplay;
    private MediaPlayer myConstantSong;
    private Thread thread;

    //Question part
    private Game_MathProblems myMP;
    private String myQuestion;
    private int myAns;
    private List<Integer> myChoices;
    private boolean isBossStage = false;
    private int score = 0;

    //Buttons
    Button mybutton1;
    Button mybutton2;
    Button mybutton3;
    Button mybutton4;


    public static Audio_Activity_Game myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_flight_button);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        myAudio = new Audio_Activity_Game(this);

        gameDisplay = new Game_Display(this, point.x +5000, point.y, myAudio);
        setContentView(gameDisplay);

        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);

        //Layout on top of surface view
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View secondLayerView = LayoutInflater.from(this).inflate(R.layout.activity_game, null, false);
        addContentView(secondLayerView, lp);

        //For math problem class
        //starts with easy questions
        setQuestionAnswerOnDisplay();

        //int score = display.theScore;
        //Toast.makeText(Activity.this,"this is a score"+score, Toast.LENGTH_LONG).show();

        Audio_Activity_Main_Menu.releasePlayers();
    }

    public void setQuestionAnswerOnDisplay(){
        //Initial question to be displayed
        myMP = new Game_MathProblems(isBossStage);

        if(!gameDisplay.isBossStage){
            myQuestion = myMP.getEasyQuestions();
            myChoices = new ArrayList<>(myMP.getEasyAnswers());
        }
        //else means its boss stage
        else{
            myQuestion = myMP.getHardQuestions();
            myChoices = new ArrayList<>(myMP.getHardAnswers());
        }

        myAns = myMP.getAnswer();
        initializeQuestionAnswer();
    }

    public void initializeQuestionAnswer(){
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

    public Audio_Activity_Game getMyAudio()
    {
        return myAudio;
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameDisplay.donePlaying();
        myAudio.stopMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameDisplay.resume();
        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }

    @Override
    protected void onStop(){
        super.onStop();
        gameDisplay.donePlaying();
        myAudio.stopMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);

    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onStart(){
        super.onStart();
        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }


    public void gameDonePlayAgain() {
        myAudio.stopMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_BOSS);
//        ActivityAudio.myActivityPlayers[ActivityAudio.MEDIA_PLAYERS.BGM_BOSS.ordinal()].release();
        /*SharedPreferences preferences = getSharedPreferences("PREFS",0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lastScore",display.theScore);
        editor.commit();*/

        Intent intent = new Intent(this, Activity_Game_Over.class);
        Bundle bundle = new Bundle();
        String points;
        points = Integer.toString(gameDisplay.theScore);
        bundle.putString("Score",points);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


    public void onClick(View view) {

            switch (view.getId()){

                case R.id.ans_button1:
                    if(mybutton1.getText().equals(myAns+"")){
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_CORRECT);
                        gameDisplay.getFlightObj().hasShot = true;
                        setQuestionAnswerOnDisplay();
                    }else{
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT);
                        break;
                    }

                    break;

                case R.id.ans_button2:
                    if(mybutton2.getText().equals(myAns+"")){
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_CORRECT);
                        gameDisplay.getFlightObj().hasShot = true;
                        setQuestionAnswerOnDisplay();
                    }else{
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT);
                        break;
                    }
                    break;

                case R.id.ans_button3:
                    if(mybutton3.getText().equals(myAns+"")){
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_CORRECT);
                        gameDisplay.getFlightObj().hasShot = true;
                        setQuestionAnswerOnDisplay();
                    }else{
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT);
                        break;
                    }
                    break;

                case R.id.ans_button4:
                    if(mybutton4.getText().equals(myAns+"")){
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_CORRECT);
                        gameDisplay.getFlightObj().hasShot = true;
                        setQuestionAnswerOnDisplay();
                    }else{
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT);
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