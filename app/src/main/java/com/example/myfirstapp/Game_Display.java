/*
 * This class represents the display of the game
 */
package com.example.myfirstapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;
import java.util.LinkedList;
import java.util.Random;

/*
 * main visual display class used for updating the main gameplay screen
 */
public class Game_Display extends SurfaceView implements Runnable {

    private Thread displayThread;
    private boolean isPlaying, isGameOver, isBossMusic = false;
    private int screenX, screenY;
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
    private static final int SCORE_TILL_BOSS = 10;
    private static final int NUMBER_OF_MINIONS = 20;
    private Audio_Activity_Game myAudio;
    private LinkedList<Game_Enemy> allMinions;
    private String mode;

    public Game_Display(Activity_Game activityGame, int screenX, int screenY, Audio_Activity_Game theAudio) {
        super(activityGame);
        this.activityGame = activityGame;

        myAudio = theAudio;

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        gameBackground1 = new Game_Background(screenX, screenY, getResources());
        gameBackground2 = new Game_Background(screenX, screenY, getResources());

        gameSpaceship = new Game_Spaceship(this, screenY, getResources());
        gameHeart = new Game_Heart(this, screenY, getResources());

        gameBackground2.x = screenX;
        paint = new Paint();
        paint.setTextSize(64);
        paint.setColor(Color.WHITE);
        gameAsteroid = new Game_Enemy(getResources(), false);
        theGameBullet = new Game_Laser(getResources());
        //clicking play shoots, we need to fix that so we don't have to start score at -1
        theScore = -1;

        allMinions = new LinkedList<>();
        for (int i = 0; i < NUMBER_OF_MINIONS; i++) {
            Game_Enemy minion = new Game_Enemy(getResources(), true);
            allMinions.add(minion);
        }
        mode = Activity_Menu_Modes.mode;
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
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
        this.theScore--;
        if(theScore < 0){
            gameHeart.lives--;
            myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_LOST_LIFE);
            theScore = 0;
        }
    }

    // allows movements of the ship, background, bullet, and asteroids
    private void update() {
        // if score reaches 10, end asteroids, begin boss stage
        if (theScore >= SCORE_TILL_BOSS && (!mode.equals("endless"))) {
            gameAsteroid.bossStageBegins = true;
            gameAsteroid.y = (screenY - gameAsteroid.height) / 2 + (-50);
            isBossStage = true;
        }
        if (isBossStage) {
            playBossMusic();
        }

        gameAsteroid.crashed = false;

        gameBackground1.x -= 10 * screenRatioX;
        gameBackground2.x -= 10 * screenRatioX;

        if (gameBackground1.x + gameBackground1.background.getWidth() < 700) {
            gameBackground1.x = screenX;
        }
        if (gameBackground2.x + gameBackground2.background.getWidth() < 700) {
            gameBackground2.x = screenX;
        }
        gameSpaceship.y = (screenY / 2) - 100;    // put spaceship to center
        theGameBullet.y = (screenY / 2);      // bullet shoots from the center

        if (gameSpaceship.hasShot) {
            //plays laser sound whenever laser is shot
            theGameBullet.x += (theGameBullet.speed);
            myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_LASER);
        }
        // allows for explosion when bullet and asteroid gets closer
        if (Math.abs(gameAsteroid.x - theGameBullet.x) < 300) {
            gameAsteroid.crashed = true;
        }

        if (Rect.intersects(gameAsteroid.getCollisionShape(), theGameBullet.getCollisionShape())) {
            if (!gameAsteroid.bossStageBegins) {
                myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_EXPLOSION_ASTEROID);
                gameAsteroid.x = -500;  // asteroid regenerates on the right
            } else {
                myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_BOSS_HIT);
                gameAsteroid.bossLife--;
            }
            theScore++;
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

        if (gameAsteroid.x + gameAsteroid.width < 0) {
            if (gameHeart.lives == 0) {
                isGameOver = true;
                return;
            }
            if (gameAsteroid.speed < 10 * screenRatioX)
                gameAsteroid.speed = (int) (10 * screenRatioX);
            gameAsteroid.x = screenX - 5000;
            gameAsteroid.y = (screenY - gameAsteroid.height) / 2;

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
            myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_HIT);
            myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_LOST_LIFE);

            if (gameAsteroid.bossStageBegins) {
                gameHeart.lives = 0;
            } else {
                gameHeart.lives--;
            }
            theScore--;
        }
        if (gameHeart.lives == 0) {
            //plays all lives lost sound
            myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_ROCKET_LOST_ALL_LIVES);
            isGameOver = true;
            activityGame.gameDonePlayAgain();
        }

        else if(gameAsteroid.bossLife <= 0)
        {
            myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_EXPLOSION_BOSS);
            myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.SFX_LEVEL_VICTORY);
            isGameOver = true;
            activityGame.gameDonePlayAgain();
        }
    }

    // produces random y coordinate for the minions
    public int produceRandomYCoordinate() {
        Random rand = new Random();
        int random = rand.nextInt(350 + 350) - 350;
        int yCoordinate = (screenY - gameAsteroid.height) / 2 + random;
        return yCoordinate;
    }

    // produce random x coordinate for the minions
    public int produceRandomXCoordinate() {
        Random rand = new Random();
        int random = rand.nextInt(2000 - 0) + 0;
        int xCoordinate = screenX - 5000 + random;
        return xCoordinate;
    }


    // allow for asteroid, bullet, background visibility
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(gameBackground1.background, gameBackground1.x, gameBackground1.y, paint);
            canvas.drawBitmap(gameBackground2.background, gameBackground2.x, gameBackground2.y, paint);
            canvas.drawText("Score: " + theScore, gameSpaceship.x + 850, gameSpaceship.y - 300, paint);
            canvas.drawBitmap(gameSpaceship.getFlight(), gameSpaceship.x, gameSpaceship.y, paint);

            if (theGameBullet.x > 100) {
                canvas.drawBitmap(theGameBullet.getBullet(), theGameBullet.x, theGameBullet.y, paint);
            }
            canvas.drawBitmap(gameAsteroid.getAsteroid(), gameAsteroid.x, gameAsteroid.y, paint);

            if (isGameOver) {
                activityGame.gameDonePlayAgain();
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
        if (gameHeart.lives == 3) {
            canvas.drawBitmap(gameHeart.heart, gameSpaceship.x + 1800, gameSpaceship.y - 350, paint);
            canvas.drawBitmap(gameHeart.heart, gameSpaceship.x + 1600, gameSpaceship.y - 350, paint);
            canvas.drawBitmap(gameHeart.heart, gameSpaceship.x + 1400, gameSpaceship.y - 350, paint);
        } else if (gameHeart.lives == 2) {
            canvas.drawBitmap(gameHeart.heart, gameSpaceship.x + 1800, gameSpaceship.y - 350, paint);
            canvas.drawBitmap(gameHeart.heart, gameSpaceship.x + 1600, gameSpaceship.y - 350, paint);
        } else if (gameHeart.lives == 1) {
            canvas.drawBitmap(gameHeart.heart, gameSpaceship.x + 1800, gameSpaceship.y - 350, paint);
        }
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
        myAudio.stopMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_GAME_LOOP);
        //begin boss music
        myAudio.playMedia(Audio_Activity_Game.MEDIA_PLAYERS.BGM_BOSS);
    }
}
