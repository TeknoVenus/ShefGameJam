package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by steph on 20/11/2016
 */
public class DoorLayout {

    private final int DOOR_HEIGHT = 50;
    private final int DOOR_WIDTH = 100;

    private Rectangle Top;
    private Rectangle Right;
    private Rectangle Bottom;
    private Rectangle Left;
    private ShapeRenderer box = new ShapeRenderer();
    private RoomRepresentation floor;

    public DoorLayout(RoomRepresentation floor) {
        this.floor = floor;
    }

    public Rectangle getTop() {
        this.Top = new Rectangle((floor.getRoomXSize()) / 2 + floor.getPadding() - DOOR_WIDTH / 2, floor.getRoomYSize() + floor.getPadding(), DOOR_WIDTH, DOOR_HEIGHT);
        return Top;
    }

    public Rectangle getRight() {
        this.Right = new Rectangle(floor.getRoomXSize() + DOOR_HEIGHT, floor.getRoomYSize() /2 + floor.getPadding() - DOOR_WIDTH/2 ,DOOR_HEIGHT, DOOR_WIDTH);
        return Right;
    }

    public Rectangle getBottom() {
        this.Bottom = new Rectangle((floor.getRoomXSize()) / 2  + floor.getPadding() - DOOR_WIDTH/2, 0,DOOR_WIDTH,DOOR_HEIGHT);
        return Bottom;
    }

    public Rectangle getLeft() {
        this.Left = new Rectangle(0,  floor.getRoomYSize() /2 + floor.getPadding() - DOOR_WIDTH/2, DOOR_HEIGHT,DOOR_WIDTH);
        return Left;
    }

    public void draw(Rectangle door) {
        box.begin(ShapeRenderer.ShapeType.Filled);
        box.setColor(0, 1, 0, 1);

        box.rect(door.getX(), door.getY(), door.getWidth(), door.getHeight());
        box.end();
    }

    public void checkCollision(Sprite sprite) {
        Rectangle boundingRect = sprite.getBoundingRectangle();

        if (Top.overlaps(boundingRect)) {
        	int newRoomType = Floor.moveRoomUp();
        	if (newRoomType != -1) {
        		Floor.setRoom(new RoomRepresentation(newRoomType));
        	}
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
