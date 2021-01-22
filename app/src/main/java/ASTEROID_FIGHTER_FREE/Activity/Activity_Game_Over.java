package ASTEROID_FIGHTER_FREE.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import ASTEROID_FIGHTER_FREE.Audio.Audio_Activity_Game_Over;
import ASTEROID_FIGHTER_FREE.Audio.Audio_Activity_Menu_Credits;
import ASTEROID_FIGHTER_FREE.Audio.Audio_Master_Control;
import ASTEROID_FIGHTER_FREE.R;


/**
 * class that shows game over screen at end of play
 */
public class Activity_Game_Over extends Activity implements View.OnClickListener {

    TextView gameOverTitle;
    TextView gameOverText;
    TextView score;

    Audio_Activity_Game_Over myAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //puts game over screen on the screen

        myAudio = new Audio_Activity_Game_Over(this);
        Audio_Master_Control.checkMuteStatus(myAudio);
        myAudio.startMedia(Audio_Activity_Game_Over.MEDIA_PLAYERS.SFX_LOST_ALL_LIVES);
        setContentView(R.layout.activity_game_over);

        //gets rid of notification bar on top of phone
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //initialize text based on xml
        gameOverTitle = findViewById(R.id.gameOverTitile);
        gameOverText = findViewById(R.id.gameOverMessage);
        score = findViewById(R.id.scoreLabel);

        final Button playAgainButton = findViewById(R.id.playAgainButton);

        //Extract the data…
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("Score");
        score.setText(text);
    }

    @Override
    public void onClick(View view) {
        myAudio.releasePlayers();
        Intent i = new Intent(this, Activity_Menu_Main.class);

        switch (view.getId()) {
            case R.id.playAgainButton:
                finish();
                startActivity(i);
                break;

            case R.id.myWebSearch_bt:
                Uri uri = Uri.parse("https://www.tutordudes.com/intern"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
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
        myAudio = new Audio_Activity_Game_Over(this);
    }
    //called when application starts/resumes
    @Override
    protected void onStart(){
        super.onStart();

    }
}