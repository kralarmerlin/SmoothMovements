package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.GdxGame;

public class Arena {

    // Tiled map variables
    public TiledMap tiledMap;
    public TiledMapTileLayer layer;

    public Arena() {
        GdxGame game = ((GdxGame) Gdx.app.getApplicationListener());

        tiledMap = game.rm.tiledMap;
        layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);


        
    }
}
