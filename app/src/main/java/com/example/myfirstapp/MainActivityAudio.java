package com.example.myfirstapp;

import android.content.Context;
import android.media.MediaPlayer;

public class MainActivityAudio implements Runnable{
    private static Context myActivity;
    public static MediaPlayer[] myMainActivityPlayers;

    public MainActivityAudio(Context theActivity)
    {
        myActivity = theActivity;
    }

    public enum MEDIA_PLAYERS
    {
        BGM_MENU,
        SFX_MENU_CLICK,
        SFX_MENU_HOVER
    }
    public void run()
    {
        MediaPlayer BGM_MENU = MediaPlayer.create(myActivity, R.raw.bgm_menu_loop);
        BGM_MENU.setLooping(true);

        myMainActivityPlayers = new MediaPlayer[]{
                BGM_MENU,
                MediaPlayer.create(myActivity, R.raw.sfx_menu_click),
                MediaPlayer.create(myActivity, R.raw.sfx_menu_hover)
        };
    }

    public void playMedia(MEDIA_PLAYERS thePlayer)
    {
        switch (thePlayer)
        {
            case BGM_MENU:
                myMainActivityPlayers[MEDIA_PLAYERS.BGM_MENU.ordinal()].start();
                break;
            case SFX_MENU_CLICK:
                myMainActivityPlayers[MEDIA_PLAYERS.SFX_MENU_CLICK.ordinal()].start();
                break;
            case SFX_MENU_HOVER:
                myMainActivityPlayers[MEDIA_PLAYERS.SFX_MENU_HOVER.ordinal()].start();
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
            case BGM_MENU:
                myMainActivityPlayers[MEDIA_PLAYERS.BGM_MENU.ordinal()].stop();
                break;
            case SFX_MENU_CLICK:
                myMainActivityPlayers[MEDIA_PLAYERS.SFX_MENU_CLICK.ordinal()].stop();
                break;
            case SFX_MENU_HOVER:
                myMainActivityPlayers[MEDIA_PLAYERS.SFX_MENU_HOVER.ordinal()].stop();
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
