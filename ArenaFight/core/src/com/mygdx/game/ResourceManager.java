package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class ResourceManager extends AssetManager {

    // Texture Atlas
    TextureAtlas atlas;

    // Tile Map
    public TiledMap tiledMap;


    public TextureAtlas.AtlasRegion gladiatorTexture;

    public Animation<TextureAtlas.AtlasRegion> idleRightAnimation;

    public ResourceManager() {

        load("atlas.atlas", TextureAtlas.class);

        setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        load("tiled/arena_map.tmx", TiledMap.class);

        // ------------------------ //
        finishLoading();
        // ------------------------ //

        atlas = get("atlas.atlas");

        tiledMap = get("tiled/arena_map.tmx", TiledMap.class);

        gladiatorTexture = atlas.findRegion("idle right", 0);

        idleRightAnimation = new Animation<TextureAtlas.AtlasRegion>(0.1f, atlas.findRegions("idle right"), Animation.PlayMode.LOOP);


    }
}
