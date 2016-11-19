package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Player extends ApplicationAdapter {
	private int x = 50;
	private int y = 50;
	private int killCount = 0;
	private boolean shoot;
	private SpriteBatch batch;
	private Texture img;
	private InputReciever controller = new InputReciever();

	private Array<Projectile> projectiles;

	public Player() {

	}
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("texture/cell1.png");
	}

	public void update() {
		x += controller.resultingMovementX();
		y += controller.resultingMovementY();
		shoot = controller.isShoot();
		manageProjectiles();
	}
	
	@Override
	public void render() {
		update();
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
	public boolean isShoot() {
		return shoot;
	}

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	public void setKillCount(int killCount) {
		this.killCount = killCount;
	}

	public int getKillCount() {
		return killCount;
	}

	private void manageProjectiles(){
		for(Iterator<Projectile> itr = projectiles.iterator(); itr.hasNext();){
			Projectile p = itr.next();
			p.update();
			if (p.getPosition().y > Gdx.graphics.getHeight() || p.getPosition().x > Gdx.graphics.getWidth()
					|| p.getPosition().x < 0 || p.getPosition().y < 0){
				itr.remove();
			}
		}
	}
}
