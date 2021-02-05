package ASTEROID_FIGHTER_FREE.Audio;

import android.content.Context;
import android.media.MediaPlayer;

public abstract class Audio_Abstract_Class {
    //list of media players
    public static MediaPlayer[] myPlayers;
    private static int myLoopers;

    /**
     * makes the array of media players and sets the ones that are supposed to loop to loop
     * @param theContext the activity
     * @param theLoopers the number of looping players
     * @param theMedia the R.raw files; put the ones that need looping first
     */
    public Audio_Abstract_Class(Context theContext, int theLoopers, int... theMedia)
    {
        myPlayers = new MediaPlayer[theMedia.length];

        myLoopers = theLoopers;

        for(int i = 0; i < theMedia.length; i++)
        {
            myPlayers[i] = MediaPlayer.create(theContext, theMedia[i]);
        }

        for(int i = 0; i < theLoopers; i++)
        {
            myPlayers[i].setLooping(true);
        }

    }

    //gets the corresponding media player according to the enum in the class
    public abstract MediaPlayer getMediaPlayer(Object thePlayer);

    //plays the corresponding media player that is inputted into th method
    public abstract void startMedia(Object thePlayer);

    //stops the corresponding media player that is inputted
    public abstract void stopMedia(Object thePlayer);

    //releases the resources associated with all the players
    public static void releasePlayers() {
        for(int i = 0; i < myPlayers.length; i++)
        {
            if (myPlayers[i] != null) {
                if (myPlayers[i].isPlaying())
                    myPlayers[i].stop();
                myPlayers[i].reset();
                myPlayers[i].release();
                myPlayers[i] = null;
            }
        }
    }

    public static void mutePlayers()
    {
        for(MediaPlayer player : myPlayers)
        {
            if (player != null)
                player.setVolume(0, 0);
        }
    }

    public static void unmutePlayers()
    {
        for(MediaPlayer player : myPlayers)
        {
            if (player != null)
                player.setVolume(1, 1);
        }
    }

    public static void pauseLoopers()
    {
        for(int i = 0; i < myLoopers; i++)
        {
            if(myPlayers[i] != null)
            {
                if(myPlayers[i].isPlaying())
                {
                    myPlayers[i].pause();
                }
            }

        }
    }

    public static void resumeLoopers()
    {
        for(int i = 0; i < myLoopers; i++)
        {
            if(myPlayers[i] != null)
            {
                if(!myPlayers[i].isPlaying())
                {
                    myPlayers[i].start();
                }
            }

        }
    }

}
