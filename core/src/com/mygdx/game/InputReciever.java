package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputReciever {
	public static boolean isMoveUp() {
		return Gdx.input.isKeyPressed(Input.Keys.UP) || 
				Gdx.input.isKeyPressed(Input.Keys.W);
	}

	public static boolean isMoveLeft() {
		return Gdx.input.isKeyPressed(Input.Keys.LEFT) || 
				Gdx.input.isKeyPressed(Input.Keys.A);
	}

	public static boolean isMoveRight() {
		return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.R);
	}

	public static boolean isMoveDown() {
		return Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);
	}
}
