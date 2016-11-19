package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FillViewport;
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

    private Player player;

    public GameScreen(RogueLite game) {
        this.game = game;
        this.player = new Player(game.batch);
        /*this.input = new InputReciever();

        camera = new OrthographicCamera();
        viewport = new FillViewport(100,100,camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);

        screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        balltxt = new Texture("textures/cell1.png");
        cell = new Sprite(balltxt, 0,0, 128, 128);
        cell.setOriginCenter();
        cell.setPosition(w/2 - cell.getWidth()/2, h/2 - cell.getHeight()/2);*/

    }


    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.render();
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

