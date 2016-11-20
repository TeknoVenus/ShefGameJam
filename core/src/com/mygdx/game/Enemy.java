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
	private int x =20;
	private int y =20;
	private Texture enemyTexture;
	private Rectangle bounds;
	private Player player;
	private Rectangle screenBounds;
	private SpriteBatch batch;
	private Sprite mafia;
	
	
	public Enemy (int x, int y, Player player, SpriteBatch batch) {
		enemyTexture = new Texture(Gdx.files.internal("textures/enemy.png"));
		mafia = new Sprite(enemyTexture, 0,0, 32, 32);
		mafia.scale(2);
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x, y, 25,25);
		this.player = player;
		this.batch = batch;
	}

	@Override
	public void render() {
		update();

		//final Rectangle bounds = mafia.getBoundingRectangle();
		//TODO:: Viewport if using camera? Check for screen resizing issues?




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
		System.out.println(player.getX());
		System.out.println(player.getY());
		if (player.getX() > mafia.getX()) {
			mafia.translateX(1);
			Gdx.app.log("Enemy", "RIGHT");
		}

		else {
			mafia.translateX(-1);
			Gdx.app.log("Enemy", "LEFT");

		}

		if (player.getY() > mafia.getY()) {
			mafia.translateY(1);
			Gdx.app.log("Enemy", "UP");
		}
		else {
			mafia.translateY(-1);
			Gdx.app.log("Enemy", "DOWN");
		}}


	public void dispose(){
		player.setKillCount(player.getKillCount() + 1);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
