package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Player;

public class MiniGameScreen implements Screen{
    private Stage stage;
    private DragAndDrop dragAndDrop;
    private int partsAssembled = 0;
    private final int totalParts = 3;
    private MyGdxGame game;
    private Image backgroundImage;
    private Image[] parts;
    private BitmapFont font;
    private GlyphLayout layout;
    private SpriteBatch batch;
    private String gameText = "Match the pieces to the silhouette!";
    private OutputManager outputManager;
    private Player player;
    private boolean timeUp = false;
    private float timeLeft = 5; // Set the initial time left
    private long startTime;

    public MiniGameScreen(MyGdxGame game, Player player) {
        this.game = game;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        dragAndDrop = new DragAndDrop();
        setupBaseSilhouette();
        this.font = new BitmapFont();
        this.layout = new GlyphLayout();
        batch = new SpriteBatch();
        this.outputManager = new OutputManager();
        this.player = player;
        startTime = TimeUtils.millis();
    }
    private void setupBaseSilhouette() {
        backgroundImage = new Image(new Texture(Gdx.files.internal("Background/MiniGame.png")));
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Image baseSilhouette = new Image(new Texture(Gdx.files.internal("Objects/Satellites/satellite_silhouette.png")));
        baseSilhouette.setPosition(Gdx.graphics.getWidth() / 2 - baseSilhouette.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - baseSilhouette.getHeight() / 2); // set to the center of the screen
        stage.addActor(backgroundImage);
        stage.addActor(baseSilhouette);
        setupParts(baseSilhouette);
    }
    private void setupParts(Image baseSilhouette) {
        boolean[] partPlaced = new boolean[totalParts];
        //Define the target positions on the silhouette for each part
        float[][] targetPositions = new float[][]{
                {baseSilhouette.getX()-63, baseSilhouette.getY()+47}, // positions for each part to be placed
                {baseSilhouette.getX()+10, baseSilhouette.getY()-2},
                {baseSilhouette.getX()+76, baseSilhouette.getY()-55}
        };
        float partSpacing = Gdx.graphics.getWidth() / (totalParts + 1); // Spacing between parts
        parts = new Image[totalParts];

        for (int i = 1; i <= totalParts; i++) {
            parts[i - 1] = new Image(new Texture(Gdx.files.internal("Objects/Satellites/satellite_" + i + ".png")));
            parts[i - 1].setPosition(partSpacing * i - parts[i - 1].getWidth() / 2, 50); // Adjust spacing based on asset size
            Image targetArea = new Image(new Texture(Gdx.files.internal("Objects/Satellites/transparent.png"))); // A placeholder, size should match or be slightly larger than parts for easier targeting
            targetArea.setPosition(baseSilhouette.getX(),baseSilhouette.getY());
            targetArea.setSize(baseSilhouette.getWidth()+100, baseSilhouette.getHeight()+100);
            stage.addActor(targetArea);

            final int partIndex = i;
            dragAndDrop.addSource(new DragAndDrop.Source(parts[i - 1])
            {
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    if (partPlaced[partIndex - 1]) { // Add this check to prevent dragging parts that are already placed
                        return null;
                    }
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setDragActor(parts[partIndex - 1]); // Used the original part as the drag actor for simplicity
                    dragAndDrop.setDragActorPosition(parts[partIndex - 1].getWidth() / 2, -parts[partIndex - 1].getHeight() / 2);
                    payload.setObject(partIndex); // Identifier for the part
                    return payload;
                }
            });
            dragAndDrop.addTarget(new DragAndDrop.Target(targetArea) {
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    return true;
                }
                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    Vector2 stageCoords = targetArea.localToStageCoordinates(new Vector2(x, y));
                    // Check if the part was dropped close enough to its target position.
                    int partIndex = (Integer) payload.getObject() - 1;
                    Vector2 target = new Vector2(targetPositions[partIndex][0], targetPositions[partIndex][1]);
                    Vector2 dropPoint = new Vector2(stageCoords.x, stageCoords.y);
                    float distance = dropPoint.dst(target);
                    if (distance <= 215 && !partPlaced[partIndex]) { // Adjust this value based on your game's layout
                        partsAssembled++;
                        partPlaced[partIndex] = true;
                        parts[partIndex].setTouchable(Touchable.disabled);
                        parts[partIndex].setPosition(target.x, target.y); // Center the part on the target
                    }
                }
            });
        }
        // Add parts to the stage after all target areas
        for (Image part : parts) {
            stage.addActor(part);
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        layout.setText(outputManager.getFont(), gameText);
        float instructionX = (stage.getWidth() - layout.width ) / 2; // Center the text
        float instructionY = layout.height+40;
        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        outputManager.draw(batch, gameText, instructionX, instructionY);
        float elapsedTime = (TimeUtils.millis() - startTime) / 1000f; // Convert milliseconds to seconds
        timeLeft -= elapsedTime; // Update timeLeft based on elapsed time
        startTime = TimeUtils.millis(); // Update the start time for the next frame
        String timeText = "Time left: " + Math.max(0, (int) timeLeft); // Ensure timeLeft doesn't go below 0
        layout.setText(font, timeText);
        float timeX = 10;
        float timeY = stage.getHeight() - layout.height - 10;
        outputManager.draw(batch, timeText, timeX, timeY); //draw the time left
        batch.end();
        checkGameCompletion();


    }

    private void checkGameCompletion() {
        if(timeLeft > 0 && partsAssembled == totalParts && !timeUp) {
            timeUp = true;
            game.getPlayer().addScore(1000);
            timeLeft = 3;
            gameText = "Well done! Returning to the game...";
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(game.getGameScreen());
                }
            }, timeLeft); // Delay in seconds
        }
        else if (timeLeft <= 0 && partsAssembled < totalParts && !timeUp) {
            timeUp = true;
            for (Image part : parts) {
                part.setTouchable(Touchable.disabled);
            }
            game.getPlayer().decreaseLives(1);
            timeLeft = 3;
            gameText = "Time's up! You lost one health. Try again next time..";
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(game.getGameScreen());
                }
            }, timeLeft); // Delay in seconds
        }
    }
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void pause() {}


    public void resume() {}


    public void hide() {}


    public void dispose() {
        stage.dispose();
        font.dispose();
        batch.dispose();
    }
}
