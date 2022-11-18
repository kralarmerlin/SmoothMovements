package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GdxGame;
import com.mygdx.game.OrthogonalTiledMapRendererBleeding;
import com.mygdx.game.entities.Arena;
import com.mygdx.game.entities.Player;

public class ArenaScreen extends ScreenAdapter implements InputProcessor {

    private GdxGame game;

    private ExtendViewport viewport;
    private OrthographicCamera gameCam;

    Player player;
    Arena arena;

    public OrthogonalTiledMapRendererBleeding mapRenderer;

    public ArenaScreen(GdxGame game) {
        this.game = game;

        viewport = new ExtendViewport(320, 180);
        //viewport.apply();
        gameCam = (OrthographicCamera) viewport.getCamera();

        arena = new Arena();

        mapRenderer = new OrthogonalTiledMapRendererBleeding(arena.tiledMap, game.batch);

        player = new Player(game.rm.gladiatorTexture, 0, 0);

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        /* Example: This first 'if' occurs if the player is not going in any other direction
        when they stop going right. If the player has stopped going right but is going up or down,
        player.direction.x becomes 0 (stop moving to the right instantly, not smoothly).
         */
        if(!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) &&
                !Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.stopMoving = true;
            return;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D))
            player.direction.x = 0f;
        else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.direction.x = -1f;
            player.stopMoving = false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.direction.x = +1f;
            player.stopMoving = false;
        }
        else {
            player.direction.x = 0f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.S))
            player.direction.y = 0f;
        else if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.direction.y = +1f;
            player.stopMoving = false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.direction.y = -1f;
            player.stopMoving = false;
        }
        else
            player.direction.y = 0f;
    }

    public void update(float dt) {
        handleInput(dt);
        player.update(dt);

        gameCam.position.x = player.getX() + player.getOriginX();
        gameCam.position.y = player.getY() + player.getOriginY();
    }

    Vector2 startPointCamera = new Vector2();
    Vector2 targetPointCamera = new Vector2();
    float elapsedTime = 0;
    float lifeTime = 0.2f;
    public void handleCamera(float dt) {
        targetPointCamera.x = player.getX() + player.getOriginX();
        targetPointCamera.y = player.getY() + player.getOriginY();

        if(player.stopMoving && player.instantSpeed != 0) {
            elapsedTime = 0f;
            startPointCamera.x = gameCam.position.x;
            startPointCamera.y = gameCam.position.y;
        }
        else {
            elapsedTime += dt;
            float progress = Math.min(1f, elapsedTime / lifeTime);

            gameCam.position.x = Interpolation.smooth.apply(startPointCamera.x, targetPointCamera.x, progress);
            gameCam.position.y = Interpolation.smooth.apply(startPointCamera.y, targetPointCamera.y, progress);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clear the game screen with black
        ScreenUtils.clear(0, 0, 0, 1);

        gameCam.update();

        mapRenderer.setView(gameCam);
        mapRenderer.render();

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin(); // ----- BEGIN WORLD RENDER ----- //

        player.draw(game.batch);

        game.batch.end(); // ----- END WORLD RENDER ----- //
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                player.direction.x = -30f;
                break;
            case Input.Keys.D:
                player.direction.x = +30f;
                break;
        };
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                player.direction.x = -30f;
                break;
            case Input.Keys.D:
                player.direction.x = +30f;
                break;
        };
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
