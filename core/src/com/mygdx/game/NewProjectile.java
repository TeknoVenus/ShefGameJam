package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by steph on 20/11/2016
 */
public class NewProjectile {

	public NewProjectile(int startX, int startY) {
		this.position = new Vector2(startX, startY);
		speedX = 5;
		visible = true;

	}

	private int speedX;
	private Vector2 position;
	private boolean visible;

	public void update(Vector2 mousePos) {
		float x = position.x;
		float y = position.y;

		x+= mousePos.x;
		y+=mousePos.y;

		position.set(x,y);

		if (position.x <= Gdx.graphics.getWidth()) {
			visible = true;
		}
		else visible = false;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
