package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
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
    public Texture img;

    private Texture backTexture;
    private Sprite backSprite;

    private Player player;
    
    public GameScreen(RogueLite game) {
        this.game = game;
        backTexture = new Texture(Gdx.files.internal("textures/bg.png"));
        backSprite = new Sprite(backTexture);
        this.player = new Player(game.batch);
        EnemiesManager.setPlayer(this.player);
        Floor.generate();
        Floor.setRoom(new 
        		RoomRepresentation(Floor.getStartFloorInt()));
        img = new Texture("badlogic.jpg");
    }


    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Draw background
        int roomLeft = 0;
        int roomRight = Floor.getRoom().getRoomXSize();
        int roomTop = 0;
        int roomBottom = Floor.getRoom().getRoomYSize();
        game.batch.begin();
        game.batch.draw(backTexture,Floor.getRoom().getPadding() + roomLeft,
                Floor.getRoom().getPadding() + roomTop,
                roomRight - roomLeft, roomBottom - roomTop);
        new BitmapFont().draw(game.batch, Floor.getActiveRoomX() + " | " +
                Floor.getActiveRoomY() ,100, 100);
        game.batch.end();
        player.render();

        for (Enemy enemy : EnemiesManager.getEnemies()) {
        	enemy.render();	
        }
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


	public static void spawnEnemies() {
		// TODO Auto-generated method stub
		
	}
}

