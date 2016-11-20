/* Enemy framework
	-Justin
*/


package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.Random;
import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.cell;

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
	
	private OrthographicCamera camera;
	private ArrayList<NewProjectile> newProjectileArrayList;
	private Sprite projectileSprite;
	private Texture projectileTexture;

	private int count;

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
		newProjectileArrayList = new ArrayList<NewProjectile>();
		count = 0;

		projectileTexture = new Texture("textures/bullet.png");
		projectileTexture = new Texture(Gdx.files.internal("textures/bullet.png"));
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
        System.out.println(bounds.x +" , "+ bounds.y);

		Random random = new Random();

		if (Math.random() > 0.5 && count > random.nextInt((1000 - 100) + 1) + 100) {
			shoot();
			count = 0;
		}
		count += 1;
		bulletUpdate();

	}

	public void bulletUpdate() {
		for (int i = 0; i < newProjectileArrayList.size(); i++) {
			NewProjectile p = newProjectileArrayList.get(i);
			if (p.getPosition().x > 0 && p.getPosition().x < Floor.getRoom().getRoomXSize()
					&& p.getPosition().y > 0 && p.getPosition().y < Floor.getRoom().getRoomYSize()) {
				p.update();
				batch.begin();
				projectileSprite = new Sprite(projectileTexture, 0, 0, projectileTexture.getWidth(), projectileTexture.getHeight());
				projectileSprite.setOriginCenter();
				projectileSprite.setPosition(p.getPosition().x, p.getPosition().y);
				projectileSprite.draw(batch);
				batch.end();

				if (projectileSprite.getBoundingRectangle().overlaps(player.getBoundingBox())) {
					Gdx.app.log("SAD TIMES", "YOU GOT SHOT BY THE ENEMY");
					player.setHealth(player.getHealth() - 0.05f);
				}


			} else {
				newProjectileArrayList.remove(i);
			}
		}
	}

	public void shoot() {
		Random random = new Random();
		NewProjectile proj = new NewProjectile(Math.round(mafia.getX()) + 10, Math.round(mafia.getY() + 10));
		int x1 = player.getX() + random.nextInt(200 + 1 + 200) - 200;
		int y1 = player.getY() + random.nextInt(200 + 1 + 200) - 200;;

		float dX = x1 - x;
		float dY = y1 - y;
		proj.setDX((float)(dX*0.1));
		proj.setDY((float)(dY*0.1));
		proj.create();
		newProjectileArrayList.add(proj);

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