package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/*
 *
 */
public class MainActivity extends AppCompatActivity {


    static MediaPlayer myMenuPlayer;
    private Button buttonInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        myMenuPlayer = MediaPlayer.create(this, R.raw.bgm_menu);
        myMenuPlayer.setLooping(true);
        myMenuPlayer.start();

        findViewById(R.id.instruction).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Instruction();
            }
        });


        final MediaPlayer menuClickPlayer = MediaPlayer.create(this, R.raw.sfx_menu_option_click);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuClickPlayer.start();
                startActivity(new Intent(MainActivity.this, Activity.class));
            }
        });

    }
    public void Instruction ()
    {
        Intent intent = new Intent(this, Instruction.class);
        startActivity(intent);
    }


}