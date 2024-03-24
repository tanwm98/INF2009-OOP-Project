package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Managers.ScreenManager;
import com.mygdx.game.MyGdxGame;


public class SettingsScreen implements Screen {

    private MyGdxGame game;
    private ScreenManager screenManager;
    public SettingsScreen(MyGdxGame game) {
        this.game = game;
        screenManager = new ScreenManager(game);

    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
    }

    private void handleInput(float delta) {}


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
