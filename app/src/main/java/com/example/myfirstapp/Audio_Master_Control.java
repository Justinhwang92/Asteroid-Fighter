package com.example.myfirstapp;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * class for controlling all audio in the game
 */
public class Audio_Master_Control {

    static boolean myMuted = false;

    public static void muteAllPlayers(Context theContext)
    {
        myMuted = true;
        if(theContext instanceof Activity_Menu_Main)
        {
            for(MediaPlayer m : Audio_Activity_Menu_Main.myMainActivityPlayers)
            {
                try {
                    if (m != null)
                        m.setVolume(0, 0);
                } catch (Exception e) {
                    System.out.println("error " + m);
                }
            }
        }


        if(theContext instanceof Activity_Game)
        {
            for(MediaPlayer m : Audio_Activity_Game.myActivityPlayers)
            {
                try
                {
                    if (m != null)
                        m.setVolume(0, 0);
                }
                catch(Exception e)
                {
                    System.out.println("error " + m);
                }
            }
        }



    }

    public static void unmuteAllPlayers(Context theContext)
    {
        myMuted = false;

        if(theContext instanceof Activity_Menu_Main)
        {
            for(MediaPlayer m : Audio_Activity_Menu_Main.myMainActivityPlayers)
            {
                try {
                    if (m != null)
                        m.setVolume(1, 1);
                } catch (Exception e) {
                    System.out.println("error " + m);
                }
            }
        }


        if(theContext instanceof Activity_Game)
        {
            for(MediaPlayer m : Audio_Activity_Game.myActivityPlayers)
            {
                try
                {
                    if (m != null)
                        m.setVolume(1, 1);
                }
                catch(Exception e)
                {
                    System.out.println("error " + m);
                }
            }
        }

    }

    public static void setAllVolume()
    {
        //for more precise audio adjustment
    }
}
