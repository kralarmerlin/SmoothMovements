package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.screens.ArenaScreen;

public class GdxGame extends Game {

	private static final int minFPS = 10;

	public SpriteBatch batch;
	public ResourceManager rm;

	ArenaScreen arenaScreen;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		rm = new ResourceManager();

		arenaScreen = new ArenaScreen(this);

		this.setScreen(arenaScreen);
	}

	@Override
	public void render () {
		if (screen != null) screen.render(Math.min(Gdx.graphics.getDeltaTime() , 1.0f / minFPS));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
