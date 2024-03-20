package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.scenes.scene2d.Touchable;


public class MixAndMatchMiniGameScreen implements Screen{
    private Stage stage;
    private DragAndDrop dragAndDrop;
    private int partsAssembled = 0;
    private final int totalParts = 3;
    private MyGdxGame game;
    private Image originalPhoto;

    public MixAndMatchMiniGameScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        originalPhoto = new Image(new Texture(Gdx.files.internal("satelite.png")));

        Gdx.input.setInputProcessor(stage);
        dragAndDrop = new DragAndDrop();
        setupBaseSilhouette();
        setupParts();
    }
    private void setupBaseSilhouette() {
        Image baseSilhouette = new Image(new Texture(Gdx.files.internal("satellite_silhouette.png")));
        baseSilhouette.setPosition(Gdx.graphics.getWidth() / 2 - baseSilhouette.getWidth() / 2, Gdx.graphics.getHeight() / 2 - baseSilhouette.getHeight() / 2); // Adjust position based on your game's layout
        stage.addActor(baseSilhouette);
        originalPhoto.setPosition(Gdx.graphics.getWidth() / 2 - originalPhoto.getWidth() / 2, Gdx.graphics.getHeight() / 2 - originalPhoto.getHeight() / 2); // Adjust position based on your game's layout
        originalPhoto.setVisible(false); // Initially, the original photo is not visible
        stage.addActor(originalPhoto);
    }
    private void setupParts() {
        boolean[] partPlaced = new boolean[totalParts];
        // Define the target positions on the silhouette for each part
        float[][] targetPositions = new float[][]{
                {30, 30}, // Example positions for each part
                {30, 60},
                {40, 30}
        };
        float partSpacing = Gdx.graphics.getWidth() / (totalParts + 1); // Spacing between parts

        Image[] parts = new Image[totalParts];

        for (int i = 1; i <= totalParts; i++) {
            parts[i-1] = new Image(new Texture(Gdx.files.internal("satellite_" + i + ".png")));
            parts[i-1].setPosition(partSpacing * i - parts[i-1].getWidth() / 2, 50); // Adjust spacing based on asset size

            // Note: Ensure "transparent.png" exists and is set correctly for target areas
            Image targetArea = new Image(new Texture(Gdx.files.internal("transparent.png"))); // A placeholder, size should match or be slightly larger than parts for easier targeting
            targetArea.setSize(300,300); // Match the part size for easier drop targeting
            targetArea.setPosition(targetPositions[i-1][0], targetPositions[i-1][1]);
            stage.addActor(targetArea);

            final int partIndex = i;

            dragAndDrop.addSource(new DragAndDrop.Source(parts[i-1]) {
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    if (partPlaced[partIndex - 1]) { // Add this check
                        return null;
                    }
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setDragActor(parts[partIndex-1]); // Use the original part as the drag actor for simplicity
                    dragAndDrop.setDragActorPosition(parts[partIndex-1].getWidth() / 2, -parts[partIndex-1].getHeight() / 2);

                    payload.setObject(partIndex); // Identifier for the part
                    return payload;
                }
            });

            dragAndDrop.addTarget(new DragAndDrop.Target(targetArea) {
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    return true; // Always accept drags
                }

                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    float targetX = targetPositions[partIndex - 1][0];
                    float targetY = targetPositions[partIndex - 1][1];
                    float distance = (float) Math.sqrt(Math.pow(x - targetX, 2) + Math.pow(y - targetY, 2));
                    System.out.println("Distance: " + distance); // Print the calculated distance for debugging

                    if (distance <= 500) { // Adjust this value based on your game's layout
                        partsAssembled++;
                        System.out.println("Parts Assembled: " + partsAssembled); // Print the number of parts assembled for debugging

                        partPlaced[partIndex - 1] = true;
                        parts[partIndex-1].setTouchable(Touchable.disabled); // Disable the part from being dragged again
                        parts[partIndex-1].setPosition(targetX, targetY); // Lock the part in the target area
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
            System.out.println("Game Completed!");
            originalPhoto.setVisible(true); // Show the original photo

            // Here you can transition to a game over or completion screen
        }
    }

    public void show() {

    }

@Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }


    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void pause() {}


    public void resume() {}


    public void hide() {}


    public void dispose() {
        stage.dispose();
        // Remember to dispose of any other disposable resources you create
    }
}

