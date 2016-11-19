package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends ApplicationAdapter {
	private int x = 50;
	private int y = 50;
	private boolean shoot;
	private SpriteBatch batch;
	private Texture img;
	private InputReciever controller = new InputReciever();

	public Player() {

	}
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	public void update() {
		x += controller.resultingMovementX();
		y += controller.resultingMovementY();
		shoot = controller.isShoot();
	}
	
	@Override
	public void render() {
		update();
		batch.begin();
		batch.draw(img, x, y);
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
}
