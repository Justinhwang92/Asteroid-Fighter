package ASTEROID_FIGHTER_FREE.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import ASTEROID_FIGHTER_FREE.R;

/**
 * class that manages all the game audio
 */
public class Audio_Activity_Game extends Audio_Abstract_Class{

    //enumeration of mediaplayers; to easier access mediaplayers from the array
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

    /**
     * initializes all the MediaPlayers
     * @param theContext activity to play audio in
     */
    public Audio_Activity_Game(Context theContext)
    {
        super(theContext, 2,
                R.raw.bgm_boss_loop,
                R.raw.bgm_game_loop,
                R.raw.sfx_boss_hit,
                R.raw.sfx_explosion_asteroid,
                R.raw.sfx_explosion_boss,
                R.raw.sfx_level_victory,
                R.raw.sfx_problem_correct,
                R.raw.sfx_problem_incorrect,
                R.raw.sfx_rocket_hit,
                R.raw.sfx_rocket_laser,
                R.raw.sfx_rocket_lost_all_lives,
                R.raw.sfx_rocket_lost_life);
    }
    @Override
    public MediaPlayer getMediaPlayer(Object thePlayer) {
        return myPlayers[((MEDIA_PLAYERS)thePlayer).ordinal()];
    }

    @Override
    public void startMedia(Object thePlayer) {
        getMediaPlayer(thePlayer).start();
    }

    @Override
    public void stopMedia(Object thePlayer) {
        getMediaPlayer(thePlayer).stop();
    }
}
