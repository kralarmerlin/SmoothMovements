package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Player extends TextureAtlas.AtlasSprite{

    public float velocity;
    public Vector2 direction = new Vector2();
    public Vector2 directionNormalized = new Vector2();

    public boolean stopMoving;
    public float instantSpeed;

    public Player(TextureAtlas.AtlasRegion region, float startX, float startY) {
        super(region);

        setOriginBasedPosition(0, 0);

        velocity = +60f;
    }

    public void update(float dt){

        // Smooth start of movement
        if(!stopMoving && instantSpeed != velocity) {
            System.out.println("smooth starting");
            instantSpeed += velocity * 2 * dt;
            if(instantSpeed > velocity)
                instantSpeed = velocity;
        }

        // Smooth stop of movement
        if(stopMoving && instantSpeed != 0) {
            System.out.println("smooth stopping");
            instantSpeed -= velocity * 2 * dt;
            if(instantSpeed < 0)
                instantSpeed = 0;
        }

        //
        direction.nor().scl(instantSpeed);
        translate(direction.x * dt, direction.y * dt);
    }
}
