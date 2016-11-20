package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends ApplicationAdapter {
	private int x = 50;
	private int y = 50;
	private int killCount = 0;
	private boolean shoot;
	private SpriteBatch batch;
	private Texture img;
	private InputReciever controller = new InputReciever();
	private Rectangle screenBounds;
	private Sprite cell;
	private Texture cellTexture;
	private Sprite projectileSprite;
	private Texture projectileTexture;
	private ArrayList<Rectangle> projectiles = new ArrayList<Rectangle>();
	// debug only
	private ShapeRenderer box = new ShapeRenderer();
	
	public Player(SpriteBatch batch) {
		this.batch = batch;
		cellTexture = new Texture("textures/cell1.png");

		screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		cell = new Sprite(cellTexture, 0,0, 32, 32);
		cell.scale(5.0f);
		cell.setOriginCenter();
		cell.setPosition(w/2 -cell.getWidth()/2, h/2 - cell.getHeight()/2);

		projectileTexture = new Texture(Gdx.files.internal("textures/bullet.png"));
		projectileSprite = new Sprite(projectileTexture,0,0, 8,8);
		projectileSprite.scale(0f);
		projectileSprite.setOriginCenter();
	}
	
	@Override
	public void create() {
	}

	public void update() {
		shoot = controller.isShoot();
		spawnProjectile();
		Iterator<Rectangle> iter = projectiles.iterator();
		while(iter.hasNext()){
			Rectangle p = iter.next();
			p.x = getNewProjectilePos(p.x,p.y).x;
			p.y = getNewProjectilePos(p.x,p.y).y;
			if (p.y > Gdx.graphics.getHeight() || p.x > Gdx.graphics.getWidth()
					|| p.x < 0 || p.y < 0){
				iter.remove();
			}
		}

		final Rectangle bounds = cell.getBoundingRectangle();
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

		// Room
		int roomLeft = 0;
		int roomRight = Floor.getRoom().getRoomXSize();
		int roomTop = 0;
		int roomBottom = Floor.getRoom().getRoomYSize();

		x += controller.resultingMovementX();
		y += controller.resultingMovementY();

		int xBoundOffset = (int) (cell.getWidth()*cell.getScaleX());
		x = Math.min(roomRight, Math.max(x-xBoundOffset,roomLeft)
				+2*xBoundOffset)-xBoundOffset;
		int yBoundOffset = (int) (cell.getHeight()*cell.getScaleY());
		y = Math.min(roomBottom, Math.max(y-yBoundOffset,roomTop)
				+2*yBoundOffset)-yBoundOffset;
	
		// debug only
		box.begin(ShapeType.Filled);
		box.setColor(1, 1, 0, 1);
		box.rect(roomLeft, roomTop, 
				 roomRight-roomLeft, roomBottom-roomTop);
		box.end();
		box.begin(ShapeType.Filled);
		box.setColor(1, 0, 0, 1);
		box.rect(x, y, 
				 60, 60);
		box.end();
	}
	
	@Override
	public void render() {
		
		update();
		cell.setX(this.x);
		cell.setY(this.y);
		batch.begin();
		cell.draw(batch);
		if (projectiles != null) {
			for (Rectangle projectile : projectiles) {
				batch.draw(projectileTexture, projectile.x, projectile.y);
			}
		}
		 
		batch.end();
		// debug only
		box.begin(ShapeType.Filled);
		box.setColor(1, 0, 1, 1);
		box.rect(0, 0, 4, 4);
		box.end();
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


	private void spawnProjectile(){
		Rectangle projectile = new Rectangle();
		projectile.x = 100;
		projectile.y = 100;
		projectile.width = 8;
		projectile.height = 8;
		projectiles.add(projectile);
	}
	private void manageProjectiles(){

		Iterator<Rectangle> iter = projectiles.iterator();
		while(iter.hasNext()){
			Rectangle p = iter.next();
			p.setPosition(getNewProjectilePos(p.x,p.y));
			if (p.y > Gdx.graphics.getHeight() || p.x > Gdx.graphics.getWidth()
					|| p.x < 0 || p.y < 0){
				iter.remove();
			}
		}
	}
	private Vector2 getNewProjectilePos(float positionX, float positionY){
		Vector2 position = new Vector2(positionX,positionY);
		Vector2 mousePos = new Vector2(Gdx.input.getX(),Gdx.input.getY());
		Vector2 output = position.interpolate(mousePos,0.1f, Interpolation.linear);
		return output;
	}
}
