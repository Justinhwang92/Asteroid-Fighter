/*
 * This class represents the display of the game
 */
package ASTEROID_FIGHTER_FREE.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.SurfaceView;
import android.view.View;

import ASTEROID_FIGHTER_FREE.Activity.Activity_Game;
import ASTEROID_FIGHTER_FREE.Activity.Activity_Menu_Modes;
import ASTEROID_FIGHTER_FREE.Audio.Audio_Activity_Game;

import java.util.LinkedList;
import java.util.Random;

/*
 * main visual display class used for updating the main gameplay screen
 */
public class Game_Display extends SurfaceView implements Runnable {

    private Thread displayThread;
    public boolean isPlaying, isGameOver, isBossMusic = false;
    int myScreenX, myScreenY;
    public static float screenRatioX, screenRatioY;
    public boolean isBossStage = false;
    // allows for display of pictures
    private Paint paint;
    private Game_Enemy gameAsteroid;
    private Game_Spaceship gameSpaceship;
    private Activity_Game activityGame;
    private Game_Background gameBackground1, gameBackground2;
    private Game_Heart gameHeart;
    private Game_Laser theGameBullet;
    public int theScore;
    private static final int SCORE_TILL_BOSS = 50;
    private static final int SCORE_TILL_SPEED_UP = 20;
    private static final int NUMBER_OF_MINIONS = 20;
    private Audio_Activity_Game myAudio;
    private final LinkedList<Game_Enemy> allMinions = new LinkedList<>();
    private String mode;

    private boolean doneLoading1;
    private boolean doneLoading2;
    private boolean doneLoading3;
    private boolean doneLoading4;
    private boolean doneLoading5;
    private boolean doneLoading6;
    public boolean doneLoadingAll;
    View myLoadingScreen;

    public Game_Display(Activity_Game activityGame, int screenX, int screenY, Audio_Activity_Game theAudio, View theLoadingScreen) {
        super(activityGame);
        this.activityGame = activityGame;
        doneLoadingAll = false;

        this.myScreenX = screenX;
        this.myScreenY = screenY;
        screenRatioX = 1920f / myScreenX;
        screenRatioY = 1080f / myScreenY;

        myLoadingScreen = theLoadingScreen;

        doneLoading1 = false;
        doneLoading2 = false;
        doneLoading3 = false;
        doneLoading4 = false;
        doneLoading5 = false;
        doneLoading6 = false;

        new Loading1().execute();
        new Loading2().execute();
        new Loading3().execute(this, screenX, screenY);
        new Loading4().execute();
        new Loading5().execute();
        new Loading6().execute();

        myAudio = theAudio;


        paint = new Paint();
        paint.setTextSize(64);
        paint.setColor(Color.WHITE);

        mode = Activity_Menu_Modes.mode;

        // this is just to deal with a bug for now. Game starts with points
        // -1, -5, -10, -1 to each of the modes respectively.
        properInitialization();

    }


    //loading thread
    private class Loading1 extends AsyncTask<Object, Integer, Void> {
        @Override
        protected Void doInBackground(Object... theObjects) {

            for (int i = 0; i < NUMBER_OF_MINIONS / 4; i++) {
                allMinions.add(new Game_Enemy(getResources(), true));
            }
            gameBackground1 = new Game_Background(myScreenX, myScreenY, getResources());
            return null;
        }


        @Override
        protected void onPostExecute(Void bitmap) {
            super.onPostExecute(bitmap);
            doneLoading1 = true;
        }
    }


    //loading thread
    private class Loading2 extends AsyncTask<Object, Integer, Void> {
        @Override
        protected Void doInBackground(Object... theObjects) {

            for (int i = 0; i < NUMBER_OF_MINIONS / 4; i++) {
                allMinions.add(new Game_Enemy(getResources(), true));
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void bitmap) {
            super.onPostExecute(bitmap);
            doneLoading2 = true;
        }
    }

    //loading thread
    private class Loading3 extends AsyncTask<Object, Integer, Void> {
        @Override
        protected Void doInBackground(Object... theObjects) {
            gameAsteroid = new Game_Enemy(getResources(), false);
            theGameBullet = new Game_Laser(getResources());
            gameHeart = new Game_Heart(((int)theObjects[2]), getResources());
            gameSpaceship = new Game_Spaceship((Game_Display)theObjects[0], myScreenY, getResources());
            return null;
        }


        @Override
        protected void onPostExecute(Void bitmap) {
            super.onPostExecute(bitmap);
            doneLoading3 = true;
        }
    }

    //loading thread
    private class Loading4 extends AsyncTask<Object, Integer, Void> {
        @Override
        protected Void doInBackground(Object... theObjects) {

            for (int i = 0; i < NUMBER_OF_MINIONS / 4; i++) {
                allMinions.add(new Game_Enemy(getResources(), true));
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void bitmap) {
            super.onPostExecute(bitmap);
            doneLoading4 = true;
        }
    }

    //loading thread
    private class Loading5 extends AsyncTask<Object, Integer, Void> {
        @Override
        protected Void doInBackground(Object... theObjects) {

            for (int i = 0; i < NUMBER_OF_MINIONS / 4; i++) {
                allMinions.add(new Game_Enemy(getResources(), true));
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void bitmap) {
            super.onPostExecute(bitmap);
            doneLoading5 = true;
        }
    }

    //loading thread
    private class Loading6 extends AsyncTask<Object, Integer, Void> {
        @Override
        protected Void doInBackground(Object... theObjects) {
            gameBackground2 = new Game_Background(myScreenX, myScreenY, getResources());
            gameBackground2.x = myScreenX;
            return null;
        }


        @Override
        protected void onPostExecute(Void bitmap) {
            super.onPostExecute(bitmap);
            doneLoading6 = true;
        }
    }
    @Override
    public void run() {

        while (isPlaying) {
            doneLoadingAll = doneLoading1 && doneLoading2 && doneLoading3 && doneLoading4 && doneLoading5 && doneLoading6;
            if(doneLoadingAll)
            {
                if(activityGame.myAudio.getMediaPlayer(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP) != null &&
                        !(activityGame.myAudio.getMediaPlayer(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP).isPlaying())) {
                    activityGame.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myLoadingScreen.setVisibility(INVISIBLE);
                        }
                    });
                    if (!isBossMusic) {
                        if(myAudio.myPlayers[0] != null)
                        myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
                    }
                }

                update();
                draw();
                sleep();
            }

        }
    }

    private void sleep() {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void deductPoint(){

        if(theScore == 0){
            gameHeart.lives--;
            if(myAudio.myPlayers[0] != null)
            myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_LOST_LIFE);
            theScore = 0;
        }
        this.theScore = 0;
    }

    // allows movements of the ship, background, bullet, and asteroids
    private void update() {

        // speed increments after every x (20) points
        increaseDifficulty();

        // if score reaches 10, end asteroids, begin boss stage
        if (theScore >= SCORE_TILL_BOSS && (!mode.equals("endless"))) {
            gameAsteroid.bossStageBegins = true;
            gameAsteroid.y = (myScreenY - gameAsteroid.height) / 2 + (-50);
            isBossStage = true;
        }
        if (isBossStage) {
            playBossMusic();
        }

        gameAsteroid.crashed = false;

        gameBackground1.x -= 10 * screenRatioX;
        gameBackground2.x -= 10 * screenRatioX;

        if (gameBackground1.x + gameBackground1.background.getWidth() < 700) {
            gameBackground1.x = myScreenX;
        }
        if (gameBackground2.x + gameBackground2.background.getWidth() < 700) {
            gameBackground2.x = myScreenX;
        }
        gameSpaceship.y = (myScreenY / 2) - 100;    // put spaceship to center
        theGameBullet.y = (myScreenY / 2);      // bullet shoots from the center

        if (gameSpaceship.hasShot) {
            //plays laser sound whenever laser is shot
            theGameBullet.x += (theGameBullet.speed);
            if(myAudio.myPlayers[0] != null)
            myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_LASER);
        }
        // allows for explosion when bullet and asteroid gets closer
        if (Math.abs(gameAsteroid.x - theGameBullet.x) < 300) {
            gameAsteroid.crashed = true;
        }

        if (Rect.intersects(gameAsteroid.getCollisionShape(), theGameBullet.getCollisionShape())) {
            if (!gameAsteroid.bossStageBegins) {
                if(myAudio.myPlayers[0] != null)
                myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_EXPLOSION_ASTEROID);
                gameAsteroid.x = -500;  // asteroid regenerates on the right
            } else {
                if(myAudio.myPlayers[0] != null)
                myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_BOSS_HIT);
                gameAsteroid.bossLife--;
            }
            //theScore++;
            incrementScore();
            theGameBullet.x = 0;   // stops bullet from continuing, goes back to left screen
            gameSpaceship.hasShot = false;
        }
        gameAsteroid.x -= (gameAsteroid.speed); // asteroid moves

        // 4 (bossLife) is just an arbitrary point in the boss stage to deploy minions
        if (gameAsteroid.bossLife < 4) {
            for (Game_Enemy minion : allMinions) {
                minion.x -= (gameAsteroid.speed + 4);
            }
        }

        if (gameAsteroid.x + gameAsteroid.width < 500) {
            if (gameHeart.lives == 0) {
                isGameOver = true;
                return;
            }
            if (gameAsteroid.speed < 10 * screenRatioX)
                gameAsteroid.speed = (int) (10 * screenRatioX);
            gameAsteroid.x = myScreenX - 5000;
            gameAsteroid.y = (myScreenY - gameAsteroid.height) / 2;

            for (Game_Enemy minion : allMinions) {
                minion.x = produceRandomXCoordinate();
                minion.y = produceRandomYCoordinate();
            }
        }
        // if asteroid hits the ship
        if (Rect.intersects(gameAsteroid.getCollisionShape(), gameSpaceship.getCollisionShape())) {
            gameAsteroid.crashed = true;
            gameAsteroid.x = -500;

            //plays heart is lost sound
            if(myAudio.myPlayers[0] != null)
            myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_HIT);
            if(myAudio.myPlayers[0] != null)
            myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_LOST_LIFE);

            if (gameAsteroid.bossStageBegins) {
                gameHeart.lives = 0;
            } else {
                gameHeart.lives--;
            }
            theScore--;
        }
        if (gameHeart.lives == 0) {
            //plays all lives lost sound
            if(myAudio.myPlayers[0] != null)
            myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_LOST_ALL_LIVES);
            isGameOver = true;
            activityGame.gameDonePlayAgain(isGameOver);
        }

        else if(gameAsteroid.bossLife <= 0)
        {
            if(myAudio.myPlayers[0] != null)
            myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_EXPLOSION_BOSS);
            if(myAudio.myPlayers[0] != null)
            myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_LEVEL_VICTORY);
            isGameOver = false;
            activityGame.gameDonePlayAgain(isGameOver);
        }
    }

    //increase speed after every x points
    public void increaseDifficulty() {
        if (mode.equals("endless")) {
            if (theScore % SCORE_TILL_SPEED_UP == 0 && theScore > 0) {
                gameAsteroid.speed++;
            }
        }
    }

    public void incrementScore() {
        if (mode.equals("novice")) {
            theScore++;
        } else if (mode.equals("intermediate")) {
            theScore += 3;
        } else if (mode.equals("advanced")) {
            theScore += 5;
        } else if (mode.equals("endless")) {
            theScore++;
        }
    }

    // this is just to deal with a bug for now. Game starts with points
    // -1, -5, -10, -1 to each of the modes respectively.
    public void properInitialization() {
        if (mode.equals("novice")) {
            theScore = -1;
        } else if (mode.equals("intermediate")) {
            theScore = -3;
        } else if (mode.equals("advanced")) {
            theScore = -5;
        } else if (mode.equals("endless")) {
            theScore = -1;
        }
    }

    // produces random y coordinate for the minions
    public int produceRandomYCoordinate() {
        Random rand = new Random();
        int random = rand.nextInt(350 + 350) - 350;
        int yCoordinate = (myScreenY - gameAsteroid.height) / 2 + random;
        return yCoordinate;
    }

    // produce random x coordinate for the minions
    public int produceRandomXCoordinate() {
        Random rand = new Random();
        int random = rand.nextInt(2000 - 0) + 0;
        int xCoordinate = myScreenX - 5000 + random;
        return xCoordinate;
    }


    // allow for asteroid, bullet, background visibility
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(gameBackground1.background, gameBackground1.x, gameBackground1.y, paint);
            canvas.drawBitmap(gameBackground2.background, gameBackground2.x, gameBackground2.y, paint);
            //canvas.drawText("Score: " + theScore, gameSpaceship.x + 850, gameSpaceship.y - 300, paint);
            drawScore(canvas);
            canvas.drawBitmap(gameSpaceship.getFlight(), gameSpaceship.x, gameSpaceship.y, paint);

            if (theGameBullet.x > 100) {
                canvas.drawBitmap(theGameBullet.getBullet(), theGameBullet.x, theGameBullet.y, paint);
            }
            canvas.drawBitmap(gameAsteroid.getAsteroid(), gameAsteroid.x, gameAsteroid.y, paint);

            if (isGameOver) {
                activityGame.gameDonePlayAgain(isGameOver);
                isPlaying = false;
            }
            drawLives(canvas);

            if (mode.equals("advanced")) {
                if (gameAsteroid.bossLife < 4) {
                    deployMinions(canvas);
                }
            }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    // Display all minions
    private void deployMinions(Canvas canvas) {
        for (Game_Enemy minion : allMinions) {
            canvas.drawBitmap(minion.minion, minion.x, minion.y, paint);
        }
    }

    // updates the health bar
    public void drawLives(Canvas canvas) {
        int topPadding = gameHeart.height / 4;
        int gap = gameHeart.width;

        if (gameHeart.lives == 3) {
            canvas.drawBitmap(gameHeart.heart,  screenRatioX, topPadding, paint);
            canvas.drawBitmap(gameHeart.heart,  screenRatioX + gap, topPadding, paint);
            canvas.drawBitmap(gameHeart.heart,  screenRatioX + 2 * gap, topPadding, paint);
        } else if (gameHeart.lives == 2) {
            canvas.drawBitmap(gameHeart.heart,  screenRatioX, topPadding, paint);
            canvas.drawBitmap(gameHeart.heart,  screenRatioX + gap, topPadding, paint);
        } else if (gameHeart.lives == 1) {
            canvas.drawBitmap(gameHeart.heart,  screenRatioX, topPadding, paint);
        }
    }

    public void drawScore(Canvas canvas) {

        //To get the height and width of the running screen
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;

//        paint = new Paint();
//        paint.setColor(Color.RED);
        int fontSize = 80;
        paint.setTextSize(fontSize);
        Typeface tf = Typeface.create("casual", Typeface.ITALIC);
        paint.setTypeface(tf);
//        paint.setTextAlign(Paint.Align.RIGHT);

        int topPadding = gameHeart.height;
        String textToBe = "Score: " + theScore;

        canvas.drawText(textToBe,this.getWidth()-paint.measureText(textToBe)-40, topPadding-40, paint);
//        canvas.drawText("Score: " + theScore, gameSpaceship.x + 850, topPadding, paint);
    }

    public void resume() {
        isPlaying = true;
        displayThread = new Thread(this);
        displayThread.start();
    }

    public void donePlaying() {
        try {
            isPlaying = false;
            displayThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Game_Spaceship getFlightObj(){
        return gameSpaceship;
    }

    //start playing the boss music instead of regular
    public void playBossMusic() {
        isBossMusic = true;
        //stop regular music
        if(activityGame.myAudio.myPlayers[0] != null)
        {
            myAudio.stopMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
            //begin boss music
            myAudio.startMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_BOSS);
        }

    }
}
