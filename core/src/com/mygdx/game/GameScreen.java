package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Stephen on 19/11/2016
 */
public class GameScreen implements Screen {

    final RogueLite game;

    Texture balltxt;
    Sprite cell;
    InputReciever input;
    Rectangle screenBounds;

    OrthographicCamera camera;
    Viewport viewport;
    Enemy enemy;
    public Texture img;

    private Player player;

    public GameScreen(RogueLite game) {
        this.game = game;
        Floor.generate();
        Floor.setRoom(new 
        		RoomRepresentation(Floor.getStartFloorInt()));
        this.player = new Player(game.batch);
        this.enemy = new Enemy(20, 20, player, game.batch);

        img = new Texture("badlogic.jpg");
        //this.enemy = new Enemy(2, player);
    }


    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.render();
        enemy.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
    }


    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}

