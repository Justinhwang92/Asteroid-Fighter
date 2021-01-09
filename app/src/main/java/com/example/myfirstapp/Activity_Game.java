package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;

/**
 * THIS CLASS CONTAINS THE MAIN GAME ACTIVITY
 * INCLUDING THE DISPLAY AND MATH PROBLEMS
 */
public class Activity_Game extends AppCompatActivity {

    /**
     * field for display of the game which updates and draws the game state
     */
    public static Game_Display gameDisplay;

    /**
     * field that stores and manages audio for game activity
     */
    public static Audio_Activity_Game myAudio;

    // FIELDS REGARDING MATH PROBLEMS IN THE GAME
    /**
     * field for MathProblems class - contains all the logic behind the math problems
     */
    private Game_MathProblems myMP;

    /**
     * field for the equation to be completed at the top of the screen
     */
    private String myQuestion;

    /**
     * the solution to the equation
     */
    private String myAns;

    /**
     * the list of all the solutions (including wrong) that can be selected for the math problem
     */
    private ArrayList<String> myChoices;

    /**
     * field that stores whether the boss has spawned or not
     */
    private boolean isBossStage = false;

    private Activity_Menu_Modes myMode;

    private String myTypeOfMode = "";

    //BUTTONS FOR MATH PROBLEMS
    private Button myButton1;
    private Button myButton2;
    private Button myButton3;
    private Button myButton4;

    /**
     * onCreate is called when this activity is started/made
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //releases the MediaPlayers from the main menu to free up resources
        Audio_Activity_Menu_Main.releasePlayers();
        //gets rid of notification bar for phone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initializes MediaPlayer devices for audio
        myAudio = new Audio_Activity_Game(this);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        new DecodeBitmapTask().execute(this);

        //start the game music

        //initializes the display for the game and starts running it
        gameDisplay = new Game_Display(this, point.x +5000, point.y, myAudio);
        setContentView(gameDisplay);
        //Layout on top of surface view
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View secondLayerView = LayoutInflater.from(this).inflate(R.layout.activity_game, null, false);
        addContentView(secondLayerView, lp);

        //To determine what mode is selected
        myMode = new Activity_Menu_Modes();
        myTypeOfMode = myMode.mode;

        //For math problem class
        //starts with easy questions
        //Initial question to be displayed


        myMP = new Game_MathProblems(isBossStage);
        questionBasedOnGameMode();


        findViewById(R.id.gameaudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView audio = findViewById(R.id.gameaudio);
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
    }
    //for loading screen
    private class DecodeBitmapTask extends AsyncTask<Object, Integer, Void>
    {
        private Activity_Game myActivity;
        private Point myPoint;
        private ProgressBar myProgress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onpreexecute");

        }

        @Override
        protected Void doInBackground(Object... theObjects) {
            System.out.println("DOINBACKGROUND");
            myActivity = (Activity_Game)theObjects[0];
            myProgress = new ProgressBar(myActivity);
            myProgress.setIndeterminate(true);
            setContentView(myProgress);
            return null;
        }


        @Override
        protected void onPostExecute(Void bitmap) {
            super.onPostExecute(bitmap);
            System.out.println("onpostexecute");
            myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
            //puts the display on the screen


        }
    }

    void questionBasedOnGameMode(){
        if(myTypeOfMode.equals("novice")){
            setQuestionAnswerOnDisplay();
        }
        else if(myTypeOfMode.equals("intermediate")){
            if(!gameDisplay.isBossStage){
                myQuestion = myMP.getIntermediateQuestion();
                myChoices = new ArrayList<>(myMP.getIntermediateQuestionAnswer());
            }
            //else means its boss stage
            else{
                myQuestion = myMP.getHardQuestions();
                myChoices = new ArrayList<>(myMP.getHardAnswers());
            }
            myAns = myMP.getAnswer();
            initializeQuestionAnswer();
        }
        else if(myTypeOfMode.equals("advanced")){
            if(!gameDisplay.isBossStage){
                myQuestion = myMP.getHardQuestions();
                myChoices = new ArrayList<>(myMP.getHardAnswers());
            }
            //else means its boss stage
            else{
                myQuestion = myMP.getExtrHardQuestions();
                myChoices = new ArrayList<>(myMP.getExtrHardAnswers());
            }
            myAns = myMP.getAnswer();
            initializeQuestionAnswer();
        }
        else if(myTypeOfMode.equals("endless")){
            if(gameDisplay.theScore <= 10){
                myQuestion = myMP.getEasyQuestions();
                myChoices = new ArrayList<>(myMP.getEasyAnswers());
            }
            else if(gameDisplay.theScore > 10 && gameDisplay.theScore <= 25){
                myQuestion = myMP.getIntermediateQuestion();
                myChoices = new ArrayList<>(myMP.getIntermediateQuestionAnswer());
            }
            else if(gameDisplay.theScore > 25 && gameDisplay.theScore <= 40){
                myQuestion = myMP.getHardQuestions();
                myChoices = new ArrayList<>(myMP.getHardAnswers());
            }
            else if(gameDisplay.theScore > 40){
                myQuestion = myMP.getExtrHardQuestions();
                myChoices = new ArrayList<>(myMP.getExtrHardAnswers());
            }
            myAns = myMP.getAnswer();
            initializeQuestionAnswer();
        }
    }


    /**
     * displays the questions and answers on the surface view
     */
    public void setQuestionAnswerOnDisplay(){

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

    /**
     * initializes the buttons and the question
     */
    public void initializeQuestionAnswer(){
        TextView queText = findViewById(R.id.id_question);
        queText.setText(myQuestion);

        Collections.shuffle(myChoices);

        myButton1 = findViewById(R.id.ans_button1);
        myButton1.setText(myChoices.get(0) + "");

        myButton2 = findViewById(R.id.ans_button2);
        myButton2.setText(myChoices.get(1) + "");

        myButton3 = findViewById(R.id.ans_button3);
        myButton3.setText(myChoices.get(2) + "");

        myButton4 = findViewById(R.id.ans_button4);
        myButton4.setText(myChoices.get(3) + "");
    }

    //called when application stops
    @Override
    protected void onPause() {
        super.onPause();
        gameDisplay.donePlaying();
        myAudio.stopMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }
    //called when application starts/resumes
    @Override
    protected void onResume() {
        super.onResume();
        gameDisplay.resume();
        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }

    //called when application stops
    @Override
    protected void onStop(){
        super.onStop();
        gameDisplay.donePlaying();
        myAudio.stopMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }

    //i think this is called when display turns back on.
    //if you press the power button and turn the emulator off and press again to start
    //it calls this method. not sure if power button on emulator just turns off screen or turns
    //off emulator
    @Override
    protected void onRestart(){
        super.onRestart();
        myAudio.releasePlayers();
        myAudio = new Audio_Activity_Game(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();
        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
    }

    /**
     * shows play again screen
     */
    public void gameDonePlayAgain() {
        myAudio.stopMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_BOSS);

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

            //determines if the question is correct and executes methods accordingly
            //i.e. if question is right, shoot and play correct sound and go to next question
            //if question is wrong, deduct point and play wrong answer sound
            switch (view.getId()){

                case R.id.ans_button1:
                    if(myButton1.getText().equals(myAns + "")){
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_CORRECT);
                        gameDisplay.getFlightObj().hasShot = true;
//                        setQuestionAnswerOnDisplay();
                        questionBasedOnGameMode();
                    }else{
                        gameDisplay.deductPoint();
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT);
//                        gameDisplay.deductPoint();
                        break;
                    }

                    break;

                case R.id.ans_button2:
                    if(myButton2.getText().equals(myAns+"")){
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_CORRECT);
                        gameDisplay.getFlightObj().hasShot = true;
//                        setQuestionAnswerOnDisplay();
                        questionBasedOnGameMode();
                    }else{
                        gameDisplay.deductPoint();
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT);
//                        gameDisplay.deductPoint();
                        break;
                    }
                    break;

                case R.id.ans_button3:
                    if(myButton3.getText().equals(myAns+"")){
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_CORRECT);
                        gameDisplay.getFlightObj().hasShot = true;
//                        setQuestionAnswerOnDisplay();
                        questionBasedOnGameMode();
                    }else{
                        gameDisplay.deductPoint();
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT);
//                        gameDisplay.deductPoint();
                        break;
                    }
                    break;

                case R.id.ans_button4:
                    if(myButton4.getText().equals(myAns+"")){
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_CORRECT);
                        gameDisplay.getFlightObj().hasShot = true;
//                        setQuestionAnswerOnDisplay();
                        questionBasedOnGameMode();
                    }else{
                        gameDisplay.deductPoint();
                        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT);
//                        gameDisplay.deductPoint();
                        break;
                    }
                    break;

                default:
                    try
                    {
                        throw new Exception();
                    }

                    catch(Exception e)
                    {
                        System.out.println(e.getStackTrace() + "\nInvalid button press");
                    }
                    break;
            }
    }
    public void muteAudio()
    {
        Audio_Master_Control.muteAllPlayers(this);
    }

    public void unmuteAudio()
    {
        Audio_Master_Control.unmuteAllPlayers(this);
    }
}
