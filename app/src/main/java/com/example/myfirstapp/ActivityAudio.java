package com.example.myfirstapp;

import android.content.Context;
import android.media.MediaPlayer;

public class ActivityAudio implements Runnable{
    private static Context myActivity;
    public static MediaPlayer[] myActivityPlayers;

    public ActivityAudio(Context theActivity)
    {
        myActivity = theActivity;
    }

    public enum MEDIA_PLAYERS
    {
        BGM_BOSS,
        BGM_GAME_LOOP,
        SFX_BOSS_HIT,
        SFX_EXPLOSION_ASTEROID,
        SFX_EXPLOSION_BOSS,
        SFX_LEVEL_VICTORY,
        SFX_PROBLEM_CORRECT,
        SFX_PROBLEM_INCORRECT,
        SFX_ROCKET_HIT,
        SFX_ROCKET_LASER,
        SFX_ROCKET_LOST_ALL_LIVES,
        SFX_ROCKET_LOST_LIFE
    }
    public void run()
    {
        MediaPlayer BGM_BOSS = MediaPlayer.create(myActivity, R.raw.bgm_boss_loop);
        BGM_BOSS.setLooping(true);
        MediaPlayer BGM_GAME_LOOP = MediaPlayer.create(myActivity, R.raw.bgm_game_loop);
        BGM_GAME_LOOP.setLooping(true);

        myActivityPlayers = new MediaPlayer[]{
                BGM_BOSS,
                BGM_GAME_LOOP,
                MediaPlayer.create(myActivity, R.raw.sfx_boss_hit),
                MediaPlayer.create(myActivity, R.raw.sfx_explosion_asteroid),
                MediaPlayer.create(myActivity, R.raw.sfx_explosion_boss),
                MediaPlayer.create(myActivity, R.raw.sfx_level_victory),
                MediaPlayer.create(myActivity, R.raw.sfx_problem_correct),
                MediaPlayer.create(myActivity, R.raw.sfx_problem_incorrect),
                MediaPlayer.create(myActivity, R.raw.sfx_rocket_hit),
                MediaPlayer.create(myActivity, R.raw.sfx_rocket_laser),
                MediaPlayer.create(myActivity, R.raw.sfx_rocket_lost_all_lives),
                MediaPlayer.create(myActivity, R.raw.sfx_rocket_lost_life)
        };
    }

    public void playMedia(MEDIA_PLAYERS thePlayer)
    {
        switch (thePlayer)
        {
            case BGM_BOSS:
                myActivityPlayers[MEDIA_PLAYERS.BGM_BOSS.ordinal()].start();
                break;
            case BGM_GAME_LOOP:
                myActivityPlayers[MEDIA_PLAYERS.BGM_GAME_LOOP.ordinal()].start();
                break;
            case SFX_BOSS_HIT:
                myActivityPlayers[MEDIA_PLAYERS.SFX_BOSS_HIT.ordinal()].start();
                break;
            case SFX_EXPLOSION_ASTEROID:
                myActivityPlayers[MEDIA_PLAYERS.SFX_EXPLOSION_ASTEROID.ordinal()].start();
                break;
            case SFX_EXPLOSION_BOSS:
                myActivityPlayers[MEDIA_PLAYERS.SFX_EXPLOSION_BOSS.ordinal()].start();
                break;
            case SFX_LEVEL_VICTORY:
                myActivityPlayers[MEDIA_PLAYERS.SFX_LEVEL_VICTORY.ordinal()].start();
                break;
            case SFX_PROBLEM_CORRECT:
                myActivityPlayers[MEDIA_PLAYERS.SFX_PROBLEM_CORRECT.ordinal()].start();
                break;
            case SFX_PROBLEM_INCORRECT:
                myActivityPlayers[MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT.ordinal()].start();
                break;
            case SFX_ROCKET_HIT:
                myActivityPlayers[MEDIA_PLAYERS.SFX_ROCKET_HIT.ordinal()].start();
                break;
            case SFX_ROCKET_LASER:
                myActivityPlayers[MEDIA_PLAYERS.SFX_ROCKET_LASER.ordinal()].start();
                break;
            case SFX_ROCKET_LOST_ALL_LIVES:
                myActivityPlayers[MEDIA_PLAYERS.SFX_ROCKET_LOST_ALL_LIVES.ordinal()].start();
                break;
            case SFX_ROCKET_LOST_LIFE:
                myActivityPlayers[MEDIA_PLAYERS.SFX_ROCKET_LOST_LIFE.ordinal()].start();
                break;
            default:
                try
                {
                    throw new Exception();
                }

                catch(Exception e)
                {
                    System.out.println(e + "\nNot a valid media player");
                }
                break;

        }
    }

    public void stopMedia(MEDIA_PLAYERS thePlayer) {
        switch (thePlayer) {
            case BGM_BOSS:
                myActivityPlayers[MEDIA_PLAYERS.BGM_BOSS.ordinal()].stop();
                break;
            case BGM_GAME_LOOP:
                myActivityPlayers[MEDIA_PLAYERS.BGM_GAME_LOOP.ordinal()].stop();
                break;
            case SFX_BOSS_HIT:
                myActivityPlayers[MEDIA_PLAYERS.SFX_BOSS_HIT.ordinal()].stop();
                break;
            case SFX_EXPLOSION_ASTEROID:
                myActivityPlayers[MEDIA_PLAYERS.SFX_EXPLOSION_ASTEROID.ordinal()].stop();
                break;
            case SFX_EXPLOSION_BOSS:
                myActivityPlayers[MEDIA_PLAYERS.SFX_EXPLOSION_BOSS.ordinal()].stop();
                break;
            case SFX_LEVEL_VICTORY:
                myActivityPlayers[MEDIA_PLAYERS.SFX_LEVEL_VICTORY.ordinal()].stop();
                break;
            case SFX_PROBLEM_CORRECT:
                myActivityPlayers[MEDIA_PLAYERS.SFX_PROBLEM_CORRECT.ordinal()].stop();
                break;
            case SFX_PROBLEM_INCORRECT:
                myActivityPlayers[MEDIA_PLAYERS.SFX_PROBLEM_INCORRECT.ordinal()].stop();
                break;
            case SFX_ROCKET_HIT:
                myActivityPlayers[MEDIA_PLAYERS.SFX_ROCKET_HIT.ordinal()].stop();
                break;
            case SFX_ROCKET_LASER:
                myActivityPlayers[MEDIA_PLAYERS.SFX_ROCKET_LASER.ordinal()].stop();
                break;
            case SFX_ROCKET_LOST_ALL_LIVES:
                myActivityPlayers[MEDIA_PLAYERS.SFX_ROCKET_LOST_ALL_LIVES.ordinal()].stop();
                break;
            case SFX_ROCKET_LOST_LIFE:
                myActivityPlayers[MEDIA_PLAYERS.SFX_ROCKET_LOST_LIFE.ordinal()].stop();
                break;
            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println(e + "\nNot a valid media player");
                }
                break;

        }
    }
}
