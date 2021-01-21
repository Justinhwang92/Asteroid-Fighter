package ASTEROID_FIGHTER_FREE.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import ASTEROID_FIGHTER_FREE.R;

/**
 * class that shows game over screen at end of play
 */
public class Activity_Game_Over extends Activity implements View.OnClickListener {

    TextView gameOverTitle;
    TextView gameOverText;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //puts game over screen on the screen
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

        playAgainButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
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



}