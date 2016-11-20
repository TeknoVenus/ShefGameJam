package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by steph on 20/11/2016
 */
public class Health {
	private int percentage;
	private Texture texture;
	private TextureRegion textureRegion;
	private Sprite sprite;
	private SpriteBatch batch;

	public Health(int percentage, SpriteBatch batch) {
		this.percentage = percentage;
		this.batch = batch;
		texture = new Texture(Gdx.files.internal("textures/healthBar.png"));
	}

	public void draw(float percentage) {
		if (percentage <= 1 && percentage >= 0) {
			sprite = new Sprite(texture, 0, 0, (int) (texture.getWidth() * percentage), texture.getHeight());
			sprite.scale(5);
			batch.begin();
			sprite.draw(batch);
			batch.end();
		} else if (percentage <= 0) {
			Gdx.app.log("Health", "GAME OVER - " + percentage);
		} else {
			Gdx.app.log("Health", "ERROR: percentage invald - " + percentage);
		}
	}
}
