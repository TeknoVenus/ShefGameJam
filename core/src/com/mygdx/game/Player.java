package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player extends ApplicationAdapter {
	private final int BULLET_WIDTH = 8;
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
	private Sprite ProjectileSprite;
	private ArrayList<Rectangle> projectiles = new ArrayList<Rectangle>();
	private OrthographicCamera camera;
	private DoorLayout doorLayout;
	private Rectangle spriteBounds;
	private Rectangle projectileBounds;
	private ArrayList<NewProjectile> NewProjectileArrayList = new ArrayList<NewProjectile>();
	private int evolveStage = 0;
	private int evolveCounter = 0;
	private Sound evolveSound;
	private Sound shotSound;
	private Sound introSound;




	// debug only
	private ShapeRenderer shapeRenderer = new ShapeRenderer();





	public Player(SpriteBatch batch) {
		shotSound = Gdx.audio.newSound(Gdx.files.internal("sound/gunshot.wav"));
		evolveSound = Gdx.audio.newSound((Gdx.files.internal("sound/evolve.wav")));
		introSound = Gdx.audio.newSound(Gdx.files.internal("sound/introscale.wav"));
		introSound.play();
		this.batch = batch;
		cellTexture = new Texture("textures/cell.png");
		projectileTexture = new Texture("textures/bullet.png");

		screenBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		spriteBounds = new Rectangle(x, y, cellTexture.getWidth(), cellTexture.getHeight());

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		cell = new Sprite(cellTexture, 0,0, cellTexture.getWidth(), cellTexture.getHeight());
		cell.scale(1);
		cell.setOriginCenter();
		cell.setPosition(w / 2 - cell.getWidth() / 2, h / 2 - cell.getHeight() / 2);

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



	public void shoot() {
		for (int i = 0; i < NewProjectileArrayList.size(); i++) {
			NewProjectile p = (NewProjectile) NewProjectileArrayList.get(i);
			if (p.isVisible()) {
				p.update(getNewProjectilePos(p.getPosition().x, p.getPosition().y));
			} else {
				NewProjectileArrayList.remove(i);
			}
		}
		NewProjectile proj = new NewProjectile(Math.round(cell.getX()) + 10, Math.round(cell.getY() + 10));
		NewProjectileArrayList.add(proj);
	}


	@Override
	public void create() {
	}


	private void spawnProjectile() {
		for (NewProjectile aNewProjectileArrayList : NewProjectileArrayList) {
			shotSound.play();
			NewProjectile p = (NewProjectile) aNewProjectileArrayList;
			/*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			shapeRenderer.circle(p.getPosition().x, p.getPosition().y, 10, 5);
			shapeRenderer.end();*/
			batch.begin();
			projectileSprite = new Sprite(projectileTexture, 0,0, projectileTexture.getWidth(), projectileTexture.getHeight());
			projectileSprite.setOriginCenter();
			projectileSprite.setPosition(p.getPosition().x, p.getPosition().y);
			projectileSprite.draw(batch);
			batch.end();

			for (int i=0; i<EnemiesManager.getEnemies().size(); i++) {
				Enemy enemy = EnemiesManager.getEnemies().get(i);
				if (projectileSprite.getBoundingRectangle().overlaps(enemy.getBounds())) {
					enemy.dispose();
					EnemiesManager.getEnemies().remove(i);
					System.out.println(killCount);
					System.out.println(evolveCounter);
				}

			}
		}

	}

	public int getEvolveStage() {
		return evolveStage;
	}

	public void setEvolveStage(int evolveStage) {
		this.evolveStage = evolveStage;
	}

	public int getEvolveCounter() {
		return evolveCounter;
	}

	public void setEvolveCounter(int evolveCounter) {
		this.evolveCounter = evolveCounter;
	}

	public void update() {

		/*if (!this.isFacingLeft()) {
			cell.setTexture( new Texture("textures/cellL.png"));
		}
		if(this.isFacingLeft()){
			cell.setTexture( new Texture("textures/cellR.png"));
		}*/
		shoot = controller.isShoot();
		if (shoot) {
			shoot();
			spawnProjectile();
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

		x += 2 * controller.resultingMovementX();
		y += 2 * controller.resultingMovementY();

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

		if (evolveCounter == 10){
			evolve();
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
		cell.setX(this.x + (Floor.getRoom().getPadding()));
		cell.setY(this.y + (Floor.getRoom().getPadding()));
		batch.begin();
		cell.draw(batch);


		batch.end();
		doorLayout.checkCollision(cell);
	}

	public void evolve() {
		evolveStage++;
		evolveCounter = 0;
		evolveSound.play();
		switch(evolveStage){
			case 1:
				cell.setTexture(new Texture("textures/microbe.png"));
				break;
			case 2:
				cell.setTexture(new Texture("textures/fish.png"));
				break;

	}}


	public void checkCollision(Sprite sprite) {

		if (spriteBounds.overlaps(projectileBounds)) {
			Gdx.app.log("Player", "COLLISION");


		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
	public boolean isShoot() {
		return shoot;
	}

	public int getX() {
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


	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	private void drawDoors() {
		if (Floor.getRoom().bottomDoor()) {
			if (Floor.getActiveRoomY() > 0 && Floor.isValidBelowUp(
					Floor.getFloor()[Floor.getActiveRoomY()+1][Floor.getActiveRoomX()],
					Floor.getRoom().getID())) {
				doorLayout.draw(doorLayout.getBottom(),false,false);
			} else {
				doorLayout.draw(doorLayout.getBottom(),false,true);
			}
		}
		if (Floor.getRoom().topDoor()) {
			if (Floor.getActiveRoomY() < Floor.getRoom().getRoomYSize()-1 && Floor.isValidBelowUp(Floor.getRoom().getID()
					,Floor.getFloor()[Floor.getActiveRoomY()-1][Floor.getActiveRoomX()])) {
				doorLayout.draw(doorLayout.getTop(),false,false);
			} else {
				doorLayout.draw(doorLayout.getTop(),false,true);
			}
		}
		if (Floor.getRoom().leftDoor()) {
			if (Floor.getActiveRoomX() > 0 && Floor.isValidLeftRight(
					Floor.getFloor()[Floor.getActiveRoomY()][Floor.getActiveRoomX()-1],
					Floor.getRoom().getID())) {
				doorLayout.draw(doorLayout.getLeft(),true,false);
			} else {
				doorLayout.draw(doorLayout.getLeft(),true,true);
			}
		}
		if (Floor.getRoom().rightDoor()) {
			if (Floor.getActiveRoomX() > 0 && Floor.isValidLeftRight(
					Floor.getRoom().getID(),
					Floor.getFloor()[Floor.getActiveRoomY()][Floor.getActiveRoomX()+1]
					)) {
				doorLayout.draw(doorLayout.getRight(),true,false);
			} else {
				doorLayout.draw(doorLayout.getRight(),true,true);
			}
		}
	}

	private Vector2 getNewProjectilePos(float positionX, float positionY) {
		/*Vector2 position = new Vector2(positionX, positionY);
		Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mousePos);
		Vector2 mousePos2 = new Vector2(mousePos.x, mousePos.y);
		float angle = position.angle();
		float newX = positionX + (float) (Math.sin(angle) * 5f);
		float newY = positionY + (float) (Math.cos(angle) * 5f);
		Vector2 output = new Vector2(newX, newY);
		return output;*/

		int x1 = Gdx.input.getX();
		int y1 = Gdx.input.getY();
		Vector3 input = new Vector3(x1, y1, 0);
		camera.unproject(input);

		float tX = input.x - 110;
		float tY = input.y - 110;
		//float mag = (float) java.lang.Math.hypot( tX, tY);
		float mag = 5;
		tX/=mag;
		tY/=mag;
		return new Vector2(tX, tY);


	}

	public void setXY(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
