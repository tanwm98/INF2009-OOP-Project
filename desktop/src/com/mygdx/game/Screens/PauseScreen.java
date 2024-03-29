package com.mygdx.game.Screens;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Managers.InputManager;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.Managers.ScreenManager;

public class PauseScreen implements Screen {
    private Skin skin;
    private MyGdxGame game;
    private Stage stage;
    private ScreenManager screenManager;
    private String[] options = {"Resume Game", "Exit to Menu"};
    private int selectedOptionIndex = 0;
    private Label[] optionLabels;
    private Music backgroundMusic;
	private BitmapFont font;
	private SpriteBatch batch;
    
    public PauseScreen(final MyGdxGame game, ScreenManager screenManager) {
    	this.game = game;
        this.screenManager = screenManager;
        this.skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));
        this.stage = new Stage(new ScreenViewport());
        this.font = OutputManager.getInstance().getFont();

        // Dialog box
        Dialog dialog = new Dialog("", skin);
        // Add text labels for each option
        optionLabels = new Label[options.length];
        for (int i = 0; i < options.length; i++) {
            optionLabels[i] = new Label(options[i], skin);
            optionLabels[i].setAlignment(Align.center);
            
            optionLabels[i].setStyle(new Label.LabelStyle(font, font.getColor()));
            dialog.getContentTable().add(optionLabels[i]).width(300).pad(20).row();
        }
        // Set the position and size of the dialog
        dialog.setSize(500, 300);
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, (stage.getHeight() - dialog.getHeight()) / 2);
        stage.addActor(dialog);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    	Gdx.input.setInputProcessor(stage);
    	if (backgroundMusic == null) {
    		backgroundMusic=OutputManager.getInstance().musicStart(2);
    	}
    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Check for input events
        if (InputManager.getInstance().isUpKeyJustPressed()||InputManager.getInstance().isWKeyJustPressed()) {
            selectedOptionIndex--;
            if (selectedOptionIndex < 0) 
                selectedOptionIndex = options.length - 1;
        } else if (InputManager.getInstance().isDownKeyJustPressed()||InputManager.getInstance().isSKeyJustPressed()) {
            selectedOptionIndex++;
            if (selectedOptionIndex >= options.length) 
                selectedOptionIndex = 0;
        } else if (InputManager.getInstance().isEnterKeyJustPressed()) {
            // Handle selection
            switch (selectedOptionIndex) {
                case 0:
                	ScreenManager.getInstance(game).popScreen();
                    break;
                case 1:
                	ScreenManager.getInstance(game).setScreen(new MainMenuScreen(game));
                    game.getPlayer().setLives(3); // Reset if Player Exits
                    game.getPlayer().setScore(0); // Reset if Player Exits
                    break;
            }
        }
        for (int i = 0; i < optionLabels.length; i++) {
            boolean isSelected = (i == selectedOptionIndex);

            // Update font color based on selection
            if (isSelected) {
                optionLabels[i].setStyle(new Label.LabelStyle(font, Color.YELLOW));
            } else {
                optionLabels[i].setStyle(new Label.LabelStyle(font, Color.WHITE));
            }
        }
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    

    @Override
    public void pause() {}

    @Override
    public void resume() {}
    

    public void hide() {
    	if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.dispose();
        }
    	
    }

    public void dispose() {
        skin.dispose();
        stage.dispose();
        if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.dispose();
        }
    }
}
