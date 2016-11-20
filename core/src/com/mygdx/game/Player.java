package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Player extends ApplicationAdapter {
	private int x = 50;
	private int y = 50;

	private int health = 100;
	private int killCount = 0;
	private boolean shoot;
	private boolean facingLeft = true;
	private SpriteBatch batch;
	private Texture img;
	private InputReciever controller = new InputReciever();
	private Rectangle screenBounds;
	private Sprite cell;
	private Texture cellTexture;
	private Sprite projectileSprite;
	private Texture projectileTexture;
	private ArrayList<Rectangle> projectiles = new ArrayList<Rectangle>();
	private OrthographicCamera camera;
	private DoorLayout doorLayout;
	private Rectangle spriteBounds;
	private Rectangle projectileBounds;
	// debug only
	private ShapeRenderer box = new ShapeRenderer();
	private final int BULLET_WIDTH = 8;
	public Player(SpriteBatch batch) {
		this.batch = batch;
		cellTexture = new Texture("textures/cell.png");

		screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBounds = new Rectangle(x,y, cellTexture.getWidth(),cellTexture.getHeight());

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		cell = new Sprite(cellTexture, 0,0, cellTexture.getWidth(), cellTexture.getHeight());
		cell.scale(2.0f);
		cell.setOriginCenter();
		cell.setPosition(w/2 -cell.getWidth()/2, h/2 - cell.getHeight()/2);

		projectileTexture = new Texture(Gdx.files.internal("textures/bullet.png"));


		camera = new OrthographicCamera();
		camera.setToOrtho(false, RoomRepresentation.getWindowSize(),
				RoomRepresentation.getWindowSize());

		doorLayout = new DoorLayout(Floor.getRoom(), batch);
		
	}

	public boolean getFacingLeft(){
		return facingLeft;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}

	@Override
	public void create() {
	}

	public void update() {

		if (!this.isFacingLeft()) {
			cell.setTexture( new Texture("textures/cellL.png"));
		}
		if(this.isFacingLeft()){
			cell.setTexture( new Texture("textures/cellR.png"));
		}
		shoot = controller.isShoot();
		if(shoot)spawnProjectile();
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

		x += 2*controller.resultingMovementX();
		y += 2*controller.resultingMovementY();

		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}

		if (x > Floor.getRoom().getRoomXSize()) {
			x = Floor.getRoom().getRoomXSize();
		}
		if (y > Floor.getRoom().getRoomYSize()) {
			y = Floor.getRoom().getRoomYSize();
		}

	}
	public boolean isFacingLeft(){
		if (controller.resultingMovementX() == 1){
			return true;
		}
		else if (controller.resultingMovementX()== -1){
			return false;
		}
		else
			return true;
	}
	@Override
	public void render() {
		update();
		drawDoors();
		cell.setX(this.x+(Floor.getRoom().getPadding()));
		cell.setY(this.y+(Floor.getRoom().getPadding()));
		batch.begin();
		cell.draw(batch);

		camera.update();
		if (projectiles != null) {
			for (Rectangle projectile : projectiles) {
				batch.draw(projectileTexture, projectile.x, projectile.y);
				Rectangle projectileBounds = new Rectangle(projectile.x,projectile.y,BULLET_WIDTH,BULLET_WIDTH);
			}
		}
		batch.end();
		doorLayout.checkCollision(cell);
	}

	public void checkCollision(Sprite sprite) {

		if (spriteBounds.overlaps(projectileBounds)) {
			Gdx.app.log("Player", "COLLISION");

	}}
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
		controller.setLastShotTime(TimeUtils.nanoTime());
		System.out.println("Spawned Projectile");
	}
	private void manageProjectiles() {
		Iterator<Rectangle> iter = projectiles.iterator();
		while (iter.hasNext()) {
			Rectangle p = iter.next();
			p.setPosition(getNewProjectilePos(p.x, p.y));
			if (p.y > Gdx.graphics.getHeight() || p.x > Gdx.graphics.getWidth()
					|| p.x < 0 || p.y < 0) {
				iter.remove();
				System.out.println("Removed Projectile");
			}
		}
	}
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	private void drawDoors() {
		if (Floor.getRoom().bottomDoor()) {
			doorLayout.draw(doorLayout.getBottom(),false);
		}
		if (Floor.getRoom().topDoor()) {
		doorLayout.draw(doorLayout.getTop(),false);
		}
		if (Floor.getRoom().leftDoor()) {
		doorLayout.draw(doorLayout.getLeft(),true);
		}
		if (Floor.getRoom().rightDoor()) {
			doorLayout.draw(doorLayout.getRight(),true);
		}
	}
	
	private Vector2 getNewProjectilePos(float positionX, float positionY){
		Vector2 position = new Vector2(positionX,positionY);
		Vector3 mousePos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
		camera.unproject(mousePos);
		Vector2 mousePos2 = new Vector2(mousePos.x,mousePos.y);
		float angle = position.angle();
		float newX = positionX + (float)(Math.sin(angle)* 5f);
		float newY = positionY + (float)(Math.cos(angle)* 5f);
		Vector2 output = new Vector2(newX, newY);
		return output;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x, int y) {
		setX(x);
		setY(y);
	}
}
