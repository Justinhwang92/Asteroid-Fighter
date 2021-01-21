package com.example.myfirstapp.Audio;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.myfirstapp.Activity.*;

/**
 * class for controlling all audio in the game
 */
public class Audio_Master_Control {

    public static boolean myMuted = false;

    public static void muteAllPlayers(Context theContext)
    {
        myMuted = true;

        if(theContext instanceof Activity_Game)
        {
            Audio_Activity_Game.mutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Backstory1)
        {
            Audio_Activity_Menu_Backstory1.mutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Backstory2)
        {
            Audio_Activity_Menu_Backstory2.mutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Backstory3)
        {
            Audio_Activity_Menu_Backstory3.mutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Credits)
        {
            Audio_Activity_Menu_Credits.mutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Instructions)
        {
            Audio_Activity_Menu_Instructions.mutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Main)
        {
            Audio_Activity_Menu_Main.mutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Modes)
        {
            Audio_Activity_Menu_Modes.mutePlayers();
        }
    }

    public static void unmuteAllPlayers(Context theContext)
    {
        myMuted = false;

        if(theContext instanceof Activity_Game)
        {
            Audio_Activity_Game.unmutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Backstory1)
        {
            Audio_Activity_Menu_Backstory1.unmutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Backstory2)
        {
            Audio_Activity_Menu_Backstory2.unmutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Backstory3)
        {
            Audio_Activity_Menu_Backstory3.unmutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Credits)
        {
            Audio_Activity_Menu_Credits.unmutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Instructions)
        {
            Audio_Activity_Menu_Instructions.unmutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Main)
        {
            Audio_Activity_Menu_Main.unmutePlayers();
        }

        else if(theContext instanceof Activity_Menu_Modes)
        {
            Audio_Activity_Menu_Modes.unmutePlayers();
        }
    }

    public static void checkMuteStatus(Context theContext)
    {
        if(myMuted)
        {
            muteAllPlayers(theContext);
        }

        else
        {
            unmuteAllPlayers(theContext);
        }
    }

    public static void onPause(Audio_Abstract_Class theAudio, Context theContext)
    {
        if(theAudio.myPlayers[0] != null)
        {
            theAudio.releasePlayers(theContext);
        }
    }

    public static void setAllVolume()
    {
        //for more precise audio adjustment
    }
}
