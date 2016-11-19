package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Matt, 19/11/2016
 */
public class Projectile {
    private Vector3 position;
    private float speed;
    private Vector3 target;
    Texture img;
    SpriteBatch batch;


    //Constructor: needs the position of the player, a target, & projectile speed
    public Projectile(Vector3 currentPos,Vector3 targetPos, float s){
        position = currentPos;
        target = targetPos;
        speed = s;
    }

    public void update(){
        position = position.interpolate(target, speed, Interpolation.linear);
    }

    public void setPosition (Vector3 pos){
        position = pos;
    }

    public Vector3 getPosition(){
        return position;
    }

    public void setSpeed(float vel){
        speed = vel;
    }

    public float getSpeed(){
        return speed;
    }

}
