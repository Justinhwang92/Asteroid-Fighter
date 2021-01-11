package com.example.myfirstapp.Audio;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.myfirstapp.R;

public class Audio_Activity_Menu_Main {
    private Context myContext;
    public static MediaPlayer[] myMainActivityPlayers;

    public Audio_Activity_Menu_Main(Context theContext)
    {
        myContext = theContext;
        MediaPlayer BGM_MENU = MediaPlayer.create(myContext, R.raw.bgm_menu_loop);
        BGM_MENU.setLooping(true);

        myMainActivityPlayers = new MediaPlayer[]{
                BGM_MENU,
                MediaPlayer.create(myContext, R.raw.sfx_menu_click)
        };
    }

    public enum MEDIA_PLAYERS
    {
        BGM_MENU,
        SFX_MENU_CLICK,
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
            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println(e + "\nNot a valid media player");
                }
                break;

        }
    }

    public static void releasePlayers()
    {
        for(MediaPlayer player : Audio_Activity_Menu_Main.myMainActivityPlayers)
        {
            if(player!=null) {
                if(player.isPlaying())
                    player.stop();
                player.reset();
                player.release();
                player=null;
            }
        }
    }
}
