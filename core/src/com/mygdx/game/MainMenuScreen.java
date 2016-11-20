package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Stephen on 19/11/2016
 */

public class MainMenuScreen implements Screen {
    final RogueLite game;
    private Stage stage;
    private Skin skin;
    private TextureAtlas atlas;
    private Viewport viewport;
    private TextureRegion backgroundTexture;
<<<<<<< HEAD
    private Music music;
=======
    private boolean isRunning = true;
    private TextButton playButton;
    private TextButton exitButton;
    private ClickListener playListener;
    private ClickListener quitListener;
    private Table mainTable;


    OrthographicCamera camera;

    public MainMenuScreen(final RogueLite gam) {
        game = gam;
        this.atlas = new TextureAtlas("skins/glassy/skin/glassy-ui.atlas");
        this.skin = new Skin(Gdx.files.internal("skins/glassy/skin/glassy-ui.json"), atlas);
        //backgroundTexture = new TextureRegion(new Texture("back.jpg"), 0, 0, 1920, 1080);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, RoomRepresentation.getWindowSize(), 
        		RoomRepresentation.getWindowSize());
        viewport = new FitViewport(RoomRepresentation.getWindowSize(), 
        		RoomRepresentation.getWindowSize(), camera);
        viewport.apply();
        stage = new Stage(viewport, game.batch);
    }

    public void create(){
        Music music = Gdx.audio.newMusic(Gdx.files.internal("music/mainmenumusic.mp3"));
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

        playButton.getLabel().setFontScale(1.3f, 1.3f);
        exitButton.getLabel().setFontScale(1.3f, 1.3f);

        //Add buttons to table
        mainTable.add(playButton).size((Gdx.graphics.getWidth()) - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 6).pad(40).row();

        mainTable.row();
        mainTable.add(exitButton).size((Gdx.graphics.getWidth()) - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 6).pad(40).row();

        //Add table to stage
        stage.addActor(mainTable);

        //Add menu music
        music = Gdx.audio.newMusic(Gdx.files.internal("music/mainmenumusic.mp3"));
    }

            //Create Table
            mainTable = new Table();
            //Set table to fill stage
            mainTable.setFillParent(true);
            //Set alignment of contents in the table.
            mainTable.center();

            //Create buttons
            playButton = new TextButton("Play", skin);
            exitButton = new TextButton("Exit", skin);

            playListener = new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new GameScreen(game));
                    dispose();
                    hide();
                    mainTable.remove();
                }};
            quitListener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                    dispose();
                    hide();
                    mainTable.remove();
                }};

            //Add listeners to buttons
            playButton.addListener(playListener);

            exitButton.addListener(quitListener);

            playButton.getLabel().setFontScale(1.3f, 1.3f);
            exitButton.getLabel().setFontScale(1.3f, 1.3f);

            //Add buttons to table
            mainTable.add(playButton).size((Gdx.graphics.getWidth()) - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 6).pad(40).row();

            mainTable.row();
            mainTable.add(exitButton).size((Gdx.graphics.getWidth()) - (Gdx.graphics.getWidth() / 4), Gdx.graphics.getHeight() / 6).pad(40).row();

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
        music.play();
        music.setLooping(true);

    }

    @Override
    public void resize(int width, int height) {
    	
    }

    @Override
    public void hide() {
        playButton.removeListener(quitListener);
        playButton.removeListener(playListener);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        music.dispose();
        playButton.removeListener(quitListener);
        playButton.removeListener(playListener);
    }
}