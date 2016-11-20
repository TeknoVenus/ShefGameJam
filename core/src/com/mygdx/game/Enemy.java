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

public class Enemy extends ApplicationAdapter{
	private int x =20;
	private int y =20;
	private Texture enemyTexture;
	private Rectangle bounds;
	private Player player;
	private Rectangle screenBounds;
	private SpriteBatch batch;
	private Sprite mafia;
	private int targetX = 100;
	private int targetY = 100;
	
	public Enemy (int x, int y, Player player, SpriteBatch batch) {
		//Randomly selects file from array.
		int texChance = (int)Math.round((Math.random()));
		String[] texArray = {"textures/enemy.png", "textures/enemy2.png"};
		String texCurrent = texArray[texChance];

		enemyTexture = new Texture(Gdx.files.internal(texCurrent));
		mafia = new Sprite(enemyTexture, 0,0, enemyTexture.getWidth(), enemyTexture.getHeight());
		mafia.scale(1);
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x, y, enemyTexture.getWidth(),enemyTexture.getHeight());
		this.player = player;
		this.batch = batch;
		newTarget();
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

		mafia.translate(Floor.getRoom().getPadding(), 
				Floor.getRoom().getPadding());
		batch.begin();
		mafia.draw(batch);
		batch.end();
		
		mafia.translate(-Floor.getRoom().getPadding(), 
				-Floor.getRoom().getPadding());
	}
	
	private void newTarget() {
		if (Math.random() < 0.2) {
			targetX = player.getX();
			targetY = player.getY();
		} else {
			targetX = (int) (Math.random()*480);
			targetY = (int) (Math.random()*300);
		}
	}
	
	public void update() {
		//System.out.println(player.getX());
		//System.out.println(player.getY());
		
		if (mafia.getX()-5 < targetX && mafia.getX()+5 > targetX
				&& mafia.getY()-5 < targetY && mafia.getY()+5 > targetY) {
			newTarget();
		}
		
		if (targetX > mafia.getX()) {
			this.x += 1;
			//Gdx.app.log("Enemy", "RIGHT");
		}

		else {
            this.x -= 1;
			//Gdx.app.log("Enemy", "LEFT");

		}

		if (targetY > mafia.getY()) {
			this.y += 1;
			//Gdx.app.log("Enemy", "UP");
		}
		else {
			this.y -= 1;
			//Gdx.app.log("Enemy", "DOWN");
		}
	    bounds.set(this.x, this.y, mafia.getWidth(), mafia.getHeight());
        mafia.setPosition(this.x, this.y);
	}

@Override
	public void dispose(){
		player.setKillCount(player.getKillCount() + 1);
        player.setEvolveCounter(player.getEvolveCounter() + 1);
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