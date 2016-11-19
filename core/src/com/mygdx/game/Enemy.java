/* Enemy framework
	-Justin
*/


package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends ApplicationAdapter{
	private Vector2 position;
	private Texture enemyTexture;
	private Rectangle bounds;
	private Player player;
	private Rectangle screenBounds;
	private SpriteBatch batch;
	private Sprite mafia;
	
	
	public Enemy (Vector2 position, Player player) {
		enemyTexture = new Texture(Gdx.files.internal("textures/enemy.png"));
		this.position = position;
		bounds = new Rectangle(position.x, position.y, 25,25);
		this.player = player;
	}

	@Override
	public void render() {
		update();

		final Rectangle bounds = mafia.getBoundingRectangle();
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
/*
		int xMove = controller.resultingMovementX();
		int yMove = controller.resultingMovementY();

		if (xMove != 0) {
			mafia.translateX(xMove);
		} else if (yMove != 0) {
			mafia.translateY(yMove);
		}
*/

		batch.begin();
		mafia.draw(batch);
		batch.end();
	}
	
	public void update() {
		bounds = new Rectangle(position.x, position.y, 25, 25);
		if (player.getX() > position.x) {
			position.x++;
		}
		
		else {
			position.x--;
		}
		
		if (player.getY() > position.y) {
			position.y++;
		}
		else {
			position.y--;
		}
	}

	public void dispose(){
		player.setKillCount(player.getKillCount() + 1);
	}

	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public Texture getEnemyTexture() {
		return enemyTexture;
	}
	
	public void setEnemyTexture(Texture enemyTexture) {
		this.enemyTexture = enemyTexture;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
