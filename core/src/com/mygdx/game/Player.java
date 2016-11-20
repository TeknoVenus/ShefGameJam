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
import com.badlogic.gdx.math.Vector3;

public class Player extends ApplicationAdapter {
	private final int BULLET_WIDTH = 8;
	private int x = 50;
	private int y = 50;
	private float health = 1;
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
	private Rectangle projectileBounds;private int evolveStage = 0;
	private int evolveCounter = 0;
	private Sound evolveSound;
	private Sound shotSound;
	private Sound introSound;

	private ArrayList<NewProjectile> newProjectileArrayList = new ArrayList<NewProjectile>();

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
		NewProjectile proj = new NewProjectile(Math.round(cell.getX()) + 10, Math.round(cell.getY() + 10));
		int x1 = Gdx.input.getX();
		int y1 = Gdx.input.getY();
		Vector3 input = new Vector3(x1, y1, 0);
		camera.unproject(input);

		float dX = input.x - x;
		float dY = input.y - y;
		proj.setDX((float)(dX*0.1));
		proj.setDY((float)(dY*0.1));
		proj.create();
		newProjectileArrayList.add(proj);
		
	}

	public void bulletUpdate() {
		for (int i = 0; i < newProjectileArrayList.size(); i++) {
			NewProjectile p = newProjectileArrayList.get(i);
			if (p.getPosition().x > 0 && p.getPosition().x < Floor.getRoom().getRoomXSize()
					&& p.getPosition().y > 0 && p.getPosition().y < Floor.getRoom().getRoomYSize()) {
				p.update();
				batch.begin();
				projectileSprite = new Sprite(projectileTexture, 0,0, projectileTexture.getWidth(), projectileTexture.getHeight());
				projectileSprite.setOriginCenter();
				projectileSprite.setPosition(p.getPosition().x, p.getPosition().y);
				projectileSprite.draw(batch);
				batch.end();
				
				for (Enemy enemy : EnemiesManager.getEnemies()) {
					if (projectileSprite.getBoundingRectangle().overlaps(enemy.getBounds())) {
						Gdx.app.log("SUCCESS", "YOU HAVE SHOT " + enemy.toString());
					}
				}
			} else {
				newProjectileArrayList.remove(i);
			}
		}
	}
	
	@Override
	public void create() {
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

		if (!this.isFacingLeft()) {
			cell.setTexture( new Texture("textures/cellL.png"));
		}
		if(this.isFacingLeft()){
			cell.setTexture( new Texture("textures/cellR.png"));
		}
		shoot = controller.isShoot();
		if (shoot) {
			shoot();
			//spawnProjectile();
		}
		bulletUpdate();

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

		if (evolveCounter >= 10){
			System.out.println("You win.");
			evolveCounter = 0;
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
		evolveSound.play();
	}
	
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

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
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

	public Rectangle getBoundingBox() {
		return cell.getBoundingRectangle();
	}
}
