package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Managers.InputManager;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.Managers.ScreenManager;

public class MainMenuScreen implements Screen {
    private SpriteBatch batch;
    private Texture backgroundImage;
    private MyGdxGame game;
    private int currentSelection = 0;
    private Music backgroundMusic;
    private Viewport viewport;
    private OrthographicCamera camera;
    private ScreenManager screenManager;
    private static InputManager inputManager;
    private static OutputManager outputManager;
    

    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
        backgroundImage = new Texture("Background/MenuScreen.png");
        screenManager = new ScreenManager(game);
        camera = new OrthographicCamera();
        viewport = new FitViewport(1200,900, camera);
        
        inputManager = InputManager.getInstance();
        outputManager = OutputManager.getInstance();
        screenManager = ScreenManager.getInstance(game);
    }

    @Override
    public void show() {
    	if (backgroundMusic == null) {
    		backgroundMusic=OutputManager.getInstance().musicStart(0);
    	}
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        outputManager.draw(batch,backgroundImage,0,0,viewport.getWorldWidth(), viewport.getWorldHeight());
        // Calculate the center of the screen
        float centerX = viewport.getWorldWidth() / 2f;
        float centerY = viewport.getWorldHeight() / 2f;
        float lineHeight = outputManager.getFont().getLineHeight(); // Get the height of the font
        float gap = Gdx.graphics.getPpcY(); // Initialize Gap between lines
        float startY = centerY + lineHeight;
        GlyphLayout layout = new GlyphLayout(); //Calculating text width

        // "Start Game" Centered
        String startText = "Start Game";
        layout.setText(outputManager.getFont(), startText);
        float startGameWidth = layout.width;
        float startX = centerX - startGameWidth / 2; // To Center the text
        outputManager.draw(batch,startText, startX, startY,currentSelection == 0);
        startY -= lineHeight + gap;
        
        // "Settings" Centered
        String settingsText = "Settings";
        layout.setText(outputManager.getFont(), settingsText);
        float settingsWidth = layout.width;
        startX = centerX - settingsWidth / 2;
        outputManager.draw(batch,settingsText, startX, startY,currentSelection == 1);
        startY -= lineHeight + gap;

        // "How to Play" Centered
        String aboutText = "How to Play";
        layout.setText(outputManager.getFont(), aboutText);
        float aboutWidth = layout.width;
        startX = centerX - aboutWidth / 2;
        outputManager.draw(batch,aboutText, startX, startY,currentSelection == 2);
        startY -= lineHeight + gap;


        // "Exit" centered
        String exitText = "Exit";
        layout.setText(outputManager.getFont(), exitText);
        float exitWidth = layout.width;
        startX = centerX - exitWidth / 2;
        outputManager.draw(batch,exitText, startX, startY,currentSelection == 3);
        batch.end(); //
        
        mouseSelection();
        updateCurrentSelection();
    }

    private void mouseSelection() {
    	Rectangle startrectangle = new Rectangle(665, 455, 260, 55);
    	Rectangle settingrectangle = new Rectangle(695, 340, 205, 55);
    	Rectangle helprectangle = new Rectangle(665, 225, 260, 55);
    	Rectangle exitrectangle = new Rectangle(750, 115, 95, 55);
    	float textX = Gdx.input.getX();
        float textY = Gdx.graphics.getHeight() - Gdx.input.getY();
        
        if (startrectangle.contains(textX,textY)) {
            currentSelection=0;
        }
        if (settingrectangle.contains(textX,textY)) {
        	currentSelection=1;
        } 
        if (helprectangle.contains(textX,textY)) {
        	currentSelection=2;
        } 
        if (exitrectangle.contains(textX,textY)) {
        	currentSelection=3;
        } 
    	if (inputManager.leftClick()) {
            // Check if the touch coordinates are inside the rectangle
            if (startrectangle.contains(textX, textY)||settingrectangle.contains(textX, textY)||helprectangle.contains(textX, textY)|| exitrectangle.contains(textX,textY)) {
            	selectOption();
            }
    	}
    }

    private void updateCurrentSelection() {
        if (inputManager.isUpKeyJustPressed() || inputManager.isWKeyJustPressed()) {
            currentSelection--;
            if (currentSelection < 0) {
                currentSelection = 3;
            }
        }
        if (inputManager.isDownKeyJustPressed() || inputManager.isSKeyJustPressed()) {
            currentSelection++;
            if (currentSelection > 3) {
                currentSelection = 0;
            }
        }
        if (inputManager.isEnterKeyJustPressed()) {
            selectOption();
        }
    }

    private void selectOption() {
    	switch (currentSelection) {
	        case 0:
	        	screenManager.pushScreen(new GameScreen(game));
	            break;
	        case 1:
	        	screenManager.pushScreen(new SettingsScreen(game));
	            break;
	        case 2:
	        	screenManager.pushScreen(new HelpScreen(game));
	            break;
	        case 3:
            Gdx.app.exit();
            break;
    }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    	outputManager.soundEnd(backgroundMusic);
    }

    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
        if (backgroundImage != null) {
            backgroundImage.dispose();
        }
    }
}
