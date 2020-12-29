/*
 * This class represents the display of the game
 */
package com.example.myfirstapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

/*
 * main visual display class used for updating the main gameplay screen
 */
public class Display extends SurfaceView implements Runnable {

    private Thread displayThread;
    private boolean isPlaying, isGameOver, isBossMusic = false;
    private int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private Asteroid asteroid;
    private Flight flight; // ship
    private Activity activity;
    private Background background1, background2;
    private Heart heart;
    private Bullet theBullet;
    public int theScore;
    private static final int SCORE_TILL_BOSS = 10;  // score that must be reached until boss appears
    private static final int NUMBER_OF_MINIONS = 20;
    private ActivityAudio myAudio;
    ArrayList<Asteroid> allMinions;


    // initializes fields
    public Display(Activity activity, int screenX, int screenY, ActivityAudio theAudio) {
        super(activity);
        this.activity = activity;

        myAudio = theAudio;

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        flight = new Flight(this, screenY, getResources());
        heart = new Heart(this, screenY, getResources());

        background2.x = screenX;
        paint = new Paint();
        paint.setTextSize(64);
        paint.setColor(Color.WHITE);
        asteroid = new Asteroid(getResources(), false);
        theBullet = new Bullet(getResources());
        //clicking play shoots, we need to fix that so we don't have to start score at -1
        theScore = -1;

        allMinions = new ArrayList<Asteroid>();
        for (int i = 0; i < NUMBER_OF_MINIONS; i++) {
            Asteroid minion = new Asteroid(getResources(), true);
            allMinions.add(minion);
        }
    }

    // summary method
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

    // allows movements of the ship, background, bullet, and asteroids
    private void update() {
        // if score reaches 10, end asteroids, begin boss stage
        if (theScore >= SCORE_TILL_BOSS) {
            asteroid.bossStageBegins = true;
            asteroid.y = (screenY - asteroid.height) / 2 + (-50);
        }

        if (theScore >= SCORE_TILL_BOSS) {
            if (!isBossMusic) {
                playBossMusic();
            }
        }
        asteroid.crashed = false;

        background1.x -= 10 * screenRatioX;
        background2.x -= 10 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 700) {
            background1.x = screenX;
        }
        if (background2.x + background2.background.getWidth() < 700) {
            background2.x = screenX;
        }


        flight.y = (screenY / 2) - 100;    // put spaceship to center
        theBullet.y = (screenY / 2);      // bullet shoots from the center

        if (flight.hasShot) {
            //plays laser sound whenever laser is shot
            myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.SFX_ROCKET_LASER);
            theBullet.x += (theBullet.speed);
        }
        // allows for explosion when bullet and asteroid gets closer
        if (Math.abs(asteroid.x - theBullet.x) < 300) {
            asteroid.crashed = true;
        }

        if (Rect.intersects(asteroid.getCollisionShape(), theBullet.getCollisionShape())) {
            if (!asteroid.bossStageBegins) {
                myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.SFX_EXPLOSION_ASTEROID);
                asteroid.x = -500;  // asteroid regenerates on the right
            } else {
                myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.SFX_BOSS_HIT);
                asteroid.bossLife--;
            }
            theScore++;
            theBullet.x = 0;   // stops bullet from continuing, goes back to left screen
            flight.hasShot = false;
        }

        asteroid.x -= (asteroid.speed); // asteroid moves

        // 4 (bossLife) is just an arbitrary point in the boss stage to deploy minions
        if (asteroid.bossLife < 4) {
            for (Asteroid minion : allMinions) {
                minion.x -= (asteroid.speed + 4);
            }
        }


        if (asteroid.x + asteroid.width < 0) {
            if (heart.lives == 0) {
                isGameOver = true;
                return;
            }
            if (asteroid.speed < 10 * screenRatioX)
                asteroid.speed = (int) (10 * screenRatioX);
            asteroid.x = screenX - 5000;
            asteroid.y = (screenY - asteroid.height) / 2;


            for (Asteroid minion : allMinions) {
                minion.x =produceRandomXCoordinate();
                minion.y = produceRandomYCoordinate();
            }

            //asteroid.wasShot = false;
        }
        // if asteroid hits the ship
        if (Rect.intersects(asteroid.getCollisionShape(), flight.getCollisionShape())) {
            asteroid.crashed = true;
            asteroid.x = -500;
            //heart.lives--;
            //plays heart is lost sound
            myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.SFX_ROCKET_HIT);
            myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.SFX_ROCKET_LOST_LIFE);

            if (asteroid.bossStageBegins) {
                heart.lives = 0;
            } else {
                heart.lives--;
            }
            theScore--;
        }
        //we need to make a separate condition for victory and loss for sounds
        if (heart.lives == 0) {
            //plays all lives lost sound

            myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.SFX_ROCKET_LOST_ALL_LIVES);
            isGameOver = true;
            activity.gameDonePlayAgain();
        }

        else if(asteroid.bossLife <= 0)
        {
            myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.SFX_EXPLOSION_BOSS);
            myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.SFX_LEVEL_VICTORY);
            isGameOver = true;
            activity.gameDonePlayAgain();
        }
    }

    // produces random y coordinate for the minions
    public int produceRandomYCoordinate() {
        Random rand = new Random();
        int random = rand.nextInt(350 + 350) - 350;
        int yCoordinate = (screenY - asteroid.height) / 2 + random;
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
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);
            canvas.drawText("Score: " + theScore, flight.x + 850, flight.y - 300, paint);
            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

            if (theBullet.x > 100) {
                canvas.drawBitmap(theBullet.getBullet(), theBullet.x, theBullet.y, paint);
            }
            canvas.drawBitmap(asteroid.getAsteroid(), asteroid.x, asteroid.y, paint);

            if (isGameOver) {
                activity.gameDonePlayAgain();
                isPlaying = false;
//                goBack();
//                return;
            }


            drawLives(canvas);

            if (asteroid.bossLife < 4) {
                deployMinions(canvas);
            }


            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    // Display all minions
    private void deployMinions(Canvas canvas) {
        for (Asteroid minion : allMinions) {
            canvas.drawBitmap(minion.minion, minion.x, minion.y, paint);
        }
    }

    // updates the health bar
    public void drawLives(Canvas canvas) {
        if (heart.lives == 3) {
            canvas.drawBitmap(heart.heart, flight.x + 1800, flight.y - 350, paint);
            canvas.drawBitmap(heart.heart, flight.x + 1600, flight.y - 350, paint);
            canvas.drawBitmap(heart.heart, flight.x + 1400, flight.y - 350, paint);
        } else if (heart.lives == 2) {
            canvas.drawBitmap(heart.heart, flight.x + 1800, flight.y - 350, paint);
            canvas.drawBitmap(heart.heart, flight.x + 1600, flight.y - 350, paint);
        } else if (heart.lives == 1) {
            canvas.drawBitmap(heart.heart, flight.x + 1800, flight.y - 350, paint);
        }
    }

    // goes back to main menu
    private void goBack() {
        activity.onStop();
        activity.onRestart();
        activity.onStart();
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

    //start playing the boss music instead of regular
    public void playBossMusic() {
        isBossMusic = true;
        //stop regular music
        myAudio.stopMedia(ActivityAudio.MEDIA_PLAYERS.BGM_GAME_LOOP);
        //begin boss music
        myAudio.playMedia(ActivityAudio.MEDIA_PLAYERS.BGM_BOSS);
    }

    // where the user should touch to shoot
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getX() > 0) {
            flight.hasShot = true;
        }
        return true;
    }

}