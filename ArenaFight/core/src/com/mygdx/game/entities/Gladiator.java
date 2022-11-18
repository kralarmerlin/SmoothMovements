package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GdxGame;

public class Gladiator extends TextureAtlas.AtlasSprite {

    public Vector2 velocity = new Vector2();

    public Gladiator(TextureAtlas.AtlasRegion region) {
        super(region);

        GdxGame game = ((GdxGame) Gdx.app.getApplicationListener());

    }
}
