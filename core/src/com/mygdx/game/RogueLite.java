package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RogueLite extends Game {
	public SpriteBatch batch;
	public Texture img;
	public BitmapFont font;

	@Override
	public void create() {
		font = new BitmapFont();
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
		EnemiesManager.setGame(this);
		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();

	}


}