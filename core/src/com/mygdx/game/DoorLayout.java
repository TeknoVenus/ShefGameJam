package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by steph on 20/11/2016
 */
public class DoorLayout {

    private final int DOOR_HEIGHT = 50;
    private final int DOOR_WIDTH = 100;

    private final int DOOR_VER_HEIGHT = 32 *2;
    private final int DOOR_VER_WIDTH = 28*2;

    private final int DOOR_SIDE_HEIGHT = 32 *3;
    private final int DOOR_SIDE_WIDTH = 8*3;


    private Rectangle Top;
    private Rectangle Right;
    private Rectangle Bottom;
    private Rectangle Left;
    private ShapeRenderer box = new ShapeRenderer();
    private SpriteBatch batch;
    private Texture doorTexture;
    private Sprite doorSprite;

    private Texture doorSideTexture;
    private Sprite doorSideSprite;

    public DoorLayout(RoomRepresentation room, SpriteBatch batch) {
        this.batch = batch;
        doorTexture = new Texture(Gdx.files.internal("textures/door.png"));
        doorSprite = new Sprite(doorTexture);

        doorSideTexture = new Texture(Gdx.files.internal("textures/doorSide.png"));
        doorSideSprite = new Sprite(doorTexture);
    }

    public Rectangle getTop() {
        this.Top = new Rectangle((Floor.getRoom().getRoomXSize()) / 2 + Floor.getRoom().getPadding() - DOOR_VER_WIDTH / 2, Floor.getRoom().getRoomYSize() - 156 - Floor.getRoom().getPadding() + DOOR_VER_HEIGHT, DOOR_VER_WIDTH, DOOR_VER_HEIGHT);
        return Top;
    }

    public Rectangle getRight() {
        this.Right = new Rectangle(Floor.getRoom().getRoomXSize() + DOOR_SIDE_WIDTH,  Floor.getRoom().getRoomYSize() /2 + Floor.getRoom().getPadding() - DOOR_SIDE_WIDTH/2, DOOR_SIDE_WIDTH,DOOR_SIDE_HEIGHT);
        return Right;
    }

    public Rectangle getBottom() {
        this.Bottom = new Rectangle((Floor.getRoom().getRoomXSize()) / 2  + Floor.getRoom().getPadding() - DOOR_WIDTH/2, 0,DOOR_WIDTH,DOOR_HEIGHT);
        return Bottom;
    }

    public Rectangle getLeft() {
        this.Left = new Rectangle(Floor.getRoom().getPadding(),  Floor.getRoom().getRoomYSize() /2 + Floor.getRoom().getPadding() - DOOR_SIDE_WIDTH/2, DOOR_SIDE_WIDTH,DOOR_SIDE_HEIGHT);
        return Left;
    }

    public void draw(Rectangle door, boolean side) {
        batch.begin();
        if (!side)
            batch.draw(doorSprite, door.getX(), door.getY(), door.getWidth(), door.getHeight());
        else
            batch.draw(doorSideTexture, door.getX(), door.getY(), door.getWidth(), door.getHeight());
        batch.end();
    }

    public void checkCollision(Sprite sprite) {
        Rectangle boundingRect = sprite.getBoundingRectangle();

        if (Top.overlaps(boundingRect)) {
            Gdx.app.log("Player", "*****OVERLAP TOP DOOR******");
        } else if (Right.overlaps(boundingRect)) {
            Gdx.app.log("Player", "*****OVERLAP RIGHT DOOR******");
        } else if (Bottom.overlaps(boundingRect)) {
            Gdx.app.log("Player", "*****OVERLAP BOTTOM DOOR******");
        } else if (Left.overlaps(boundingRect)) {
            Gdx.app.log("Player", "*****OVERLAP LEFT DOOR******");
        }
    }
}
