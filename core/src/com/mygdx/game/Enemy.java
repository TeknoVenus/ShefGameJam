/* Enemy framework
	-Justin
*/


package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.graphics.Texture;
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2;


public class Enemy {
	private Vector position;
	private Texture enemyTexture;
	private Rectangle bounds;
	private Player player;
	
	
	public Enemy (Vector position, Player player) {
		enemyTexture = new Texture
		this.position = position;
		bounds = new Rectangle(position.x, position.y, 25,25);
		this.player = player;
	}
	
	
	public void update() {
		bounds = new Rectangle(position.x, position.y, 25, 25);
		if (player.getPosition().x > position.x) {
			position.x++;
		}
		
		else {
			position.x--;
		}
		
		if (player.getPosition().y > position.y) {
			position.y++;
		}
		else {
			position.y--;
		}
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public Texture getEnemyTexture() {
		return enemyTexture;
	}
	
	public void setEnemyTexture(Texture enemyTexture) {
		this.enemyTexture = enemyTexture;
	}
	
	public Rectangle getBounds() {
		return bounds
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
