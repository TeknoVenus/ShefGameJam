package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by steph on 20/11/2016
 */
public class NewProjectile extends ApplicationAdapter {
    private Texture img;
    private SpriteBatch batch;
    
	public NewProjectile(int startX, int startY) {
		this.position = new Vector2(startX, startY);
		visible = true;
	}

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture(Gdx.files.internal("textures/bullet.png"));
    }
	
    @Override
    public void render() {
        batch.begin();
        batch.draw(img, position.x, position.y);
        batch.end();
    }
	
	private Vector2 position;
	private boolean visible;
	private float dy = 0;
	private float dx = 0;

	public void update() {
		position.x += dx;
		position.y += dy;
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

	public void setDX(float dx) {
		this.dx = dx;
	}
	
	public void setDY(float dy) {
		this.dy = dy;
	}
}
