package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Stephen on 19/11/2016
 */

public class MainMenuScreen implements Screen {
    final RogueLite game;
    private Stage stage;
    private Skin skin;
    private TextureAtlas atlas;
    private Viewport viewport;

    OrthographicCamera camera;

    public MainMenuScreen(final RogueLite gam) {
        game = gam;
        this.atlas = new TextureAtlas("D:\\CompSocGameJam\\!ShefGameJam\\ShefGameJam\\core\\assets\\skins\\kenney-pixel\\skin\\skin.atlas");
        this.skin = new Skin(Gdx.files.internal("D:\\CompSocGameJam\\!ShefGameJam\\ShefGameJam\\core\\assets\\skins\\kenney-pixel\\skin\\skin.json"), atlas);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        viewport = new FitViewport(800, 400, camera);
        viewport.apply();
        stage = new Stage(viewport, game.batch);
    }

    @Override
    public void show() {
        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center();

        //Create buttons
        TextButton playButton = new TextButton("Play", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        //Add listeners to buttons
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               game.setScreen(new GameScreen(game));
               dispose();
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add buttons to table
        mainTable.add(playButton).size((Gdx.graphics.getWidth()) - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 8).row();
        mainTable.row();
        mainTable.add(exitButton).size((Gdx.graphics.getWidth()) - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 8).row();

        //Add table to stage
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
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