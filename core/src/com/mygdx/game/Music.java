/* Music class (to add music, obviously)
    - Written by Justin
    - For Team Wumpus++ */

package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils;
import com.badlogic.gdx.audio.Music;

public class Music extends Disposable{
    private Music music;

    public Music {
        Music music = Gdx.audio.newMusic(Gdx.files.internal());
    }

    public void play(Music music){
        music.play();
        music.setLooping(true);
    }

    public void pause(Music music) {
        ApplicationListener.pause(music);
    }

    public void resume(Music music) {
        ApplicationListener.resume(music);
    }

    public void dispose(Music music){
        music.dispose();
    }

}