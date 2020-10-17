/**
 * This class represents the display of the game
 */
package com.example.myfirstapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Display extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY, score = 0;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private Rocket[] rockets;
    private Random random;
    private List<Bullet> bullets;
    private Flight flight;
    private Activity activity;
    private Background background1, background2;

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

        bullets = new ArrayList<>();

        background2.x = screenX;

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        rockets = new Rocket[4];

        for (int i = 0;i < 4;i++) {
            Rocket rocket = new Rocket(getResources());
            rockets[i] = rocket;
        }
        random = new Random();
    }

    // summary method
    @Override
    public void run() {
        while (isPlaying) {
            update ();
            draw ();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // allows movements of the ship, background, bullet, and asteroids
    private void update () {
        background1.x -= 10 * screenRatioX;
        background2.x -= 10 * screenRatioX;
        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }
        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }
        if (flight.isGoingUp)
            flight.y -= 30 * screenRatioY;
        else
            flight.y += 30 * screenRatioY;

        if (flight.y < 0)
            flight.y = 0;
        if (flight.y >= screenY - flight.height)
            flight.y = screenY - flight.height;
        List<Bullet> trash = new ArrayList<>();
        for (Bullet bullet : bullets) {
            if (bullet.x > screenX)
                trash.add(bullet);
            bullet.x += 50 * screenRatioX;
            for (Rocket rocket : rockets) {
                if (Rect.intersects(rocket.getCollisionShape(),
                        bullet.getCollisionShape())) {
                    score++;
                    rocket.x = -500;
                    bullet.x = screenX + 500;
                    rocket.wasShot = true;
                }
            }
        }
        for (Bullet bullet : trash)
            bullets.remove(bullet);
        for (Rocket rocket : rockets) {
            rocket.x -= rocket.speed;
            if (rocket.x + rocket.width < 0) {
                if (!rocket.wasShot) {
                    isGameOver = true;
                    return;
                }
                int bound = (int) (30 * screenRatioX);
                rocket.speed = random.nextInt(bound);
                if (rocket.speed < 10 * screenRatioX)
                    rocket.speed = (int) (10 * screenRatioX);
                rocket.x = screenX;
                rocket.y = random.nextInt(screenY - rocket.height);
                rocket.wasShot = false;
            }
            if (Rect.intersects(rocket.getCollisionShape(), flight.getCollisionShape())) {
                isGameOver = true;
                return;
            }
        }
    }

    // allow for asteroid, bullet, background visibility
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);
            for (Rocket rocket : rockets)
                canvas.drawBitmap(rocket.getRocket(), rocket.x, rocket.y, paint);
            canvas.drawText(score + "", screenX / 2f, 164, paint);
            if (isGameOver) {
                isPlaying = false;
                goBack();
                return;
            }
            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);
            for (Bullet bullet : bullets)
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
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

    // allows movement for the ship
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    flight.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp = false;
                if (event.getX() > screenX / 2)
                    flight.toShoot++;
                break;
        }
        return true;
    }

    // produces bullets
    public void newBullet() {
        Bullet bullet = new Bullet(getResources());
        bullet.x = flight.x + flight.width;
        bullet.y = flight.y + (flight.height / 2);
        bullets.add(bullet);
    }
}