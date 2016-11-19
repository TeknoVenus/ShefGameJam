package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Matt
 */
public class Projectile {
    private Vector2 position;
    private float velocity;

    public Projectile(int xToward, int yToward){
        position = new Vector2();
    }

    public void update(){
        position +=
    }

    public void setPosition (Vector2 pos){
        position = pos;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void setVelocity(float vel){
        velocity = vel;
    }

    public float getVelocity(){
        return velocity;
    }

}
