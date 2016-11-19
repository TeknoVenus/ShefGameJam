package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends ApplicationAdapter {
	private int x = 50;
	private int y = 50;
	private int killCount = 0;
	private boolean shoot;
	private SpriteBatch batch;
	private Texture img;
	private InputReciever controller = new InputReciever();
	private Rectangle screenBounds;
	private Sprite cell;
	private Texture cellTexture;

	public Player(SpriteBatch batch) {
		this.batch = batch;

		batch = new SpriteBatch();
		cellTexture = new Texture("textures/cell1.png");

		screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();

		cell = new Sprite(cellTexture, 0,0, 32, 32);
		cell.scale(5.0f);
		cell.setOriginCenter();
		cell.setPosition(w/2 -cell.getWidth()/2, h/2 - cell.getHeight()/2);
	}
	
	@Override
	public void create() {

	}

	public void update() {
/*		x += controller.resultingMovementX();
		y += controller.resultingMovementY();*/
		shoot = controller.isShoot();
	}
	
	@Override
	public void render() {
		update();

		final Rectangle bounds = cell.getBoundingRectangle();
		//TODO:: Viewport if using camera? Check for screen resizing issues?

		float left = bounds.getX();
		float bottom = bounds.getY();
		float top = bottom + bounds.getHeight();
		float right = left + bounds.getWidth();

		// Screen
		float screenLeft = screenBounds.getX();
		float screenBottom = screenBounds.getY();
		float screenTop = screenBottom + screenBounds.getHeight();
		float screenRight = screenLeft + screenBounds.getWidth();

		int xMove = controller.resultingMovementX();
		int yMove = controller.resultingMovementY();

		if (xMove != 0) {
			cell.translateX(xMove);
		} else if (yMove != 0) {
			cell.translateY(yMove);
		}


		batch.begin();
		cell.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
	public boolean isShoot() {
		return shoot;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	public void setKillCount(int killCount) {
		this.killCount = killCount;
	}

	public int getKillCount() {
		return killCount;
	}
}
