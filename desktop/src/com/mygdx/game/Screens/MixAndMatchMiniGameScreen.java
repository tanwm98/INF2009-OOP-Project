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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Player;


public class MixAndMatchMiniGameScreen implements Screen{
    private Stage stage;
    private DragAndDrop dragAndDrop;
    private int partsAssembled = 0;
    private final int totalParts = 3;
    private MyGdxGame game;
    private Image originalPhoto;
    private Image backgroundImage;
    private Image[] parts;

    private BitmapFont font;
    private GlyphLayout layout;
    private float timeLeft = 30; // Set the initial time left
    private SpriteBatch batch;
    private String gameText = "Match the pieces to the silhouette!";
    private OutputManager outputManager;
    private Player player;

    public MixAndMatchMiniGameScreen(MyGdxGame game, Player player) {
        this.game = game;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        originalPhoto = new Image(new Texture(Gdx.files.internal("Objects/Satellites/satelite.png")));
        Gdx.input.setInputProcessor(stage);
        dragAndDrop = new DragAndDrop();
        setupBaseSilhouette();
        this.font = new BitmapFont();
        this.layout = new GlyphLayout();
        batch = new SpriteBatch();
        this.outputManager = new OutputManager();
        this.player = player;
    }
    private void setupBaseSilhouette() {
        backgroundImage = new Image(new Texture(Gdx.files.internal("Background/MiniGame.png")));
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Image baseSilhouette = new Image(new Texture(Gdx.files.internal("Objects/Satellites/satellite_silhouette.png")));
        baseSilhouette.setPosition(Gdx.graphics.getWidth() / 2 - baseSilhouette.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - baseSilhouette.getHeight() / 2); // set to the center of the screen
        originalPhoto.setPosition(Gdx.graphics.getWidth() / 2 - originalPhoto.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - originalPhoto.getHeight() / 2);
        originalPhoto.setVisible(false); // Hide the original photo
        stage.addActor(backgroundImage);
        stage.addActor(baseSilhouette);
        stage.addActor(originalPhoto);
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
                        checkGameCompletion();
                    }
                }
            });
        }
        // Add parts to the stage after all target areas
        for (Image part : parts) {
            stage.addActor(part);
        }
    }
    private void checkGameCompletion() {
        if (partsAssembled == totalParts) {
            originalPhoto.setVisible(true); // Show the original photo
            Timer.instance().clear(); // Cancel the timer
            player.addScore(1000);
            timeLeft = 3;
            gameText = "Well done! Returning to the game... ";
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new GameScreen(game,player));
                }
            }, timeLeft); // Delay in seconds
        }
    }
    @Override
    public void show() {
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                game.setScreen(new GameScreen(game));
            }
        }, timeLeft); // Delay in seconds
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        layout.setText(outputManager.getFont(), gameText);
        float instructionX = (stage.getWidth() - layout.width ) / 2; // Center the text
        float instructionY = layout.height+40; // 10 pixels from the bottom
        batch.setProjectionMatrix(stage.getCamera().combined);
        batch.begin();
        outputManager.draw(batch, gameText, instructionX, instructionY);
        timeLeft -= delta;
        String timeText = "Time left: " + Math.max(0, (int) timeLeft);
        layout.setText(font, timeText);
        float timeX = 10; // 10 pixels from the left
        float timeY = stage.getHeight() - layout.height - 10; // 10 pixels from the top
        outputManager.draw(batch, timeText, timeX, timeY);
        batch.end();
    }
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void pause() {}


    public void resume() {}


    public void hide() {}


    public void dispose() {
        stage.dispose();
    }
}
