package ASTEROID_FIGHTER_FREE.Audio;

import android.content.Context;

import ASTEROID_FIGHTER_FREE.Activity.*;

/**
 * class for controlling all audio in the game
 */
public class Audio_Master_Control {

    public static boolean myMuted = false;

    public static void muteAllPlayers(Audio_Abstract_Class theAudio)
    {
        myMuted = true;
        theAudio.mutePlayers();
    }

    public static void unmuteAllPlayers(Audio_Abstract_Class theAudio)
    {
        myMuted = false;
        theAudio.unmutePlayers();
    }

    public static void checkMuteStatus(Audio_Abstract_Class theAudio)
    {
        if(myMuted)
        {
            muteAllPlayers(theAudio);
        }

        else
        {
            unmuteAllPlayers(theAudio);
        }
    }

}
