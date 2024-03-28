package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.Managers.ScreenManager;
import com.mygdx.game.MyGdxGame;


public class SettingsScreen implements Screen {
    private MyGdxGame game;
    private SpriteBatch batch;
    private ScreenManager screenManager;
    private Stage stage;
    private Slider volumeSlider;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Texture backgroundTexture;
    private Music backgroundMusic;
    private int selectedOptions = 0;
    private float newVolume = 0;

    
    
    public SettingsScreen(MyGdxGame game) {
        this.game = game;
        screenManager = new ScreenManager(game);
        backgroundTexture = new Texture("Background/starfield.png");
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1580,900, camera);
    }


    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        if (backgroundMusic == null) {
        backgroundMusic = screenManager.getoutputManager().musicStart(0);
        }
        
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/skin.json")); 
        
        // Create volume slider
        volumeSlider = new Slider(0, 1, 0.1f, false, skin); // Range from 0 to 1, step size of 0.1
        table.add(volumeSlider).width(350).pad(20);
        
        volumeSlider.addListener(new DragListener(){
        	private float startX;
        	@Override            
        	public void dragStart(InputEvent event, float x, float y, int pointer) {
                startX = x;
            }

            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                // Handle slider drag event
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer) {
                // Calculate the delta change in slider position
                float delta = x - startX;

                // Calculate the new volume based on the delta change
                newVolume = volumeSlider.getValue() + delta / volumeSlider.getWidth();

                // Ensure that the new volume is within the valid range (0 to 1)
                newVolume = MathUtils.clamp(newVolume, 0f, 1f);
                screenManager.getoutputManager().setVolume(newVolume);
                startX = x;	
            }
        });

        Button button = new Button(skin);
        table.add(button);
        button.addListener(new ChangeListener(){
        	@Override
        	public void changed(ChangeEvent event, Actor actor) {
        		if (button.isChecked()) {
        			screenManager.getoutputManager().mute(true);
        		}else {
        			screenManager.getoutputManager().mute(false);
        			float previousVolume = screenManager.getoutputManager().getPreviousVolume();
                    screenManager.getoutputManager().setVolume(previousVolume);
        		}
        	}
        });
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        screenManager.getoutputManager().draw(batch,backgroundTexture,0,0,viewport.getWorldWidth(), viewport.getWorldHeight());
        String volumeText = "Volume";
		screenManager.getoutputManager().draw(batch,volumeText,400, 465);
        
        String menuText = "Back to Menu";
        screenManager.getoutputManager().draw(batch,menuText,620, 290, selectedOptions == 0);
        batch.end();
        
        // Update stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        
        mouseSelection();
        handleInputs();
    }
    
    private void mouseSelection() {
    	Rectangle menurectangle = new Rectangle(612, 245, 297, 55);
    	float textX = Gdx.input.getX();
        float textY = Gdx.graphics.getHeight() - Gdx.input.getY();
        
        if (menurectangle.contains(textX,textY)) {
            selectedOptions=0;
        }

    	if (screenManager.getinputManager().leftClick()) {
            // Check if the touch coordinates are inside the rectangle
            if (menurectangle.contains(textX, textY)) {
            	backToMainMenu();
            }
    	}
    }
    

    private void handleInputs() {
    	if(selectedOptions == 0) {
            if (screenManager.getinputManager().isEnterKeyJustPressed()||screenManager.getinputManager().isEscapeKeyPressed()) {
            	backToMainMenu();
            }
    	}
    }
    
	public void backToMainMenu() {
		screenManager.pushScreen(new MainMenuScreen(game));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
        if (backgroundMusic != null)
        {
        	backgroundMusic.dispose();
        }
        if (stage != null)
        {
        	stage.dispose();
        }
    }

    @Override
    public void pause() {
    	
    }

    @Override
    public void resume() {
    	
    }

    @Override
    public void hide() {
    	screenManager.getoutputManager().soundEnd(backgroundMusic);
    }
}
