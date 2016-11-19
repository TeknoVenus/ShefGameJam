package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputReciever {
	public boolean isMoveUp() {
		return Gdx.input.isKeyPressed(Input.Keys.UP) || 
				Gdx.input.isKeyPressed(Input.Keys.W);
	}

	public boolean isMoveLeft() {
		return Gdx.input.isKeyPressed(Input.Keys.LEFT) || 
				Gdx.input.isKeyPressed(Input.Keys.A);
	}

	public boolean isMoveRight() {
		return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.R);
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
}
