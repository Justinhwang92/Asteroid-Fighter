/*
 * This class represents the display of the game
 */
package com.example.myfirstapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceView;

import static java.lang.Thread.*;

public class Display extends SurfaceView implements Runnable {

    private Thread thread;
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

    // initializes fields
    public Display(Activity activity, int screenX, int screenY) {
        super(activity);
        this.activity = activity;
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
        asteroid = new Asteroid(getResources());
        theBullet = new Bullet(getResources());

        theScore = -1;
}
    // summary method
    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // allows movements of the ship, background, bullet, and asteroids
    private void update () {
        // if score reaches 10, end asteroids, begin boss stage
        if (theScore == 10) {
            asteroid.bossStageBegins = true;
            asteroid.y = (screenY - asteroid.height) / 2 - 250; //adjust boss to center
        }

        if(theScore >= 10)
        {
            if(!isBossMusic)
            {
                playBossMusic();
            }
        }
        asteroid.crashed = false;
        background1.x -= 10 * screenRatioX;
        background2.x -= 10 * screenRatioX;
        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }
        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }

        flight.y = (screenY / 2) - 100;    // put spaceship to center
        theBullet.y = (screenY / 2);      // bullet shoots from the center

        if (flight.hasShot) {
            theBullet.x += (theBullet.speed);
        }
        // allows for explosion when bullet and asteroid gets closer
        if (Math.abs(asteroid.x - theBullet.x) < 300) {
            asteroid.crashed = true;
        }

        if (Rect.intersects(asteroid.getCollisionShape(), theBullet.getCollisionShape())) {
            if (!asteroid.bossStageBegins) {
                MediaPlayer asteroidCrashPlayer = MediaPlayer.create(activity, R.raw.basic_explosion);
                asteroidCrashPlayer.start();
                asteroid.x = -500;  // asteroid regenerates on the right
            }
            theScore++;
            theBullet.x = 0;   // stops bullet from continuing, goes back to left screen
            flight.hasShot = false;
        }

        asteroid.x -= (asteroid.speed); // asteroid moves
        if (asteroid.x + asteroid.width < 0) {
            if (heart.lives == 0) {
                isGameOver = true;
                return;
            }
            if (asteroid.speed < 10 * screenRatioX)
                asteroid.speed = (int) (10 * screenRatioX);
            asteroid.x = screenX - 5000;
            asteroid.y = (screenY - asteroid.height) / 2;
            asteroid.wasShot = false;
        }
        // if asteroid hits the ship
        if (Rect.intersects(asteroid.getCollisionShape(), flight.getCollisionShape())) {
            asteroid.crashed = true;
            asteroid.x = -500;
            heart.lives--;
            //plays heart is lost sound
            MediaPlayer heartLostPlayer = MediaPlayer.create(activity, R.raw.spaceship_lost_life);
            heartLostPlayer.start();

            theScore--;
        }
        if (heart.lives == 0) {
            //plays all lives lost sound
            MediaPlayer deadPlayer = MediaPlayer.create(activity, R.raw.lost_all_lives);
            deadPlayer.start();
            isGameOver = true;
            return;
        }
    }

    // allow for asteroid, bullet, background visibility
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);
            //canvas.drawText("Score: "+theScore, flight.x + 750, flight.y - 300, paint);
            canvas.drawText("Score: "+theScore, screenX / 2 - 300, flight.y - 300, paint);
            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

            if (theBullet.x > 100) {
                canvas.drawBitmap(theBullet.getBullet(), theBullet.x, theBullet.y, paint);
            }
            canvas.drawBitmap(asteroid.getAsteroid(), asteroid.x, asteroid.y, paint);

            if (isGameOver) {
                isPlaying = false;
                goBack();
                return;
            }

            drawLives(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    // updates the health bar
    public void drawLives(Canvas canvas) {
        if (heart.lives == 3) {
            canvas.drawBitmap(heart.heart, flight.x + 1800, flight.y - 450, paint);
            canvas.drawBitmap(heart.heart, flight.x + 1600, flight.y - 450, paint);
            canvas.drawBitmap(heart.heart, flight.x + 1400, flight.y - 450, paint);
        } else if (heart.lives == 2) {
            canvas.drawBitmap(heart.heart, flight.x + 1800, flight.y - 450, paint);
            canvas.drawBitmap(heart.heart, flight.x + 1600, flight.y - 450, paint);
        } else if (heart.lives == 1) {
            canvas.drawBitmap(heart.heart, flight.x + 1800, flight.y - 450, paint);
        }
    }

    private void disableAsteroids() {

    }

    // goes back to main menu
    private void goBack() {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void donePlaying() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //start playing the boss music instead of regular
    public void playBossMusic()
    {
        isBossMusic = true;
        //stop regular music
        activity.getMyConstantSong().stop();
        //begin boss music
        MediaPlayer bossPlayer = MediaPlayer.create(activity, R.raw.boss_ibragame);
        bossPlayer.setLooping(true);
        bossPlayer.start();
    }

    // where the user should touch to shoot
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //plays lazer sound whenever lazer is shot
        MediaPlayer lazerPlayer = MediaPlayer.create(activity, R.raw.spaceship_lazer);
        lazerPlayer.start();

        if (event.getX() > 0) {
            flight.hasShot = true;
        }
        return true;
    }
}