package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputReciever {

	private int counter = 15;
	
	public boolean isMoveUp() {
		return Gdx.input.isKeyPressed(Input.Keys.UP) || 
				Gdx.input.isKeyPressed(Input.Keys.W);
	}

	public boolean isMoveLeft() {
		return Gdx.input.isKeyPressed(Input.Keys.LEFT) || 
				Gdx.input.isKeyPressed(Input.Keys.A);
	}

	public boolean isMoveRight() {
		return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
	}

	public boolean isMoveDown() {
		return Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);
	}
	
	// these methods return -1 to 1 for extent of movement each frame
	
	public int resultingMovementX() {
		int x = 0;
		if (isMoveRight()) {
			x+= 1;
		}
		if (isMoveLeft()) {
			x-= 1;
		}
		return x;
	}
	
	public int resultingMovementY() {
		int y = 0;
		if (isMoveUp()) {
			y+= 1;
		}
		if (isMoveDown()) {
			y-= 1;
		}
		return y;
	}
	
	// triggers on frames when player can re/shoot
	public boolean isShoot() {
		counter += 1;
		if (counter > 20) {
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			counter = 0;
			return true;
			}
		}
		return false;
	}
}
