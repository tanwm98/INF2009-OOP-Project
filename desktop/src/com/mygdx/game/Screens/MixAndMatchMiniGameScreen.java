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
import com.badlogic.gdx.math.Vector2;

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
    }
    private void setupBaseSilhouette() {
        Image baseSilhouette = new Image(new Texture(Gdx.files.internal("satellite_silhouette.png")));
        baseSilhouette.setPosition(Gdx.graphics.getWidth() / 2 - baseSilhouette.getWidth() / 2, Gdx.graphics.getHeight() / 2 - baseSilhouette.getHeight() / 2); // Adjust position based on your game's layout
        stage.addActor(baseSilhouette);
        originalPhoto.setPosition(Gdx.graphics.getWidth() / 2 - originalPhoto.getWidth() / 2, Gdx.graphics.getHeight() / 2 - originalPhoto.getHeight() / 2); // Adjust position based on your game's layout
        originalPhoto.setVisible(false); // Initially, the original photo is not visible
        stage.addActor(originalPhoto);
        setupParts(baseSilhouette);
    }
    private void setupParts(Image baseSilhouette) {

        boolean[] partPlaced = new boolean[totalParts];
        // Define the target positions on the silhouette for each part
        float[][] targetPositions = new float[][]{
                {baseSilhouette.getWidth(), baseSilhouette.getHeight()}, // positions for each part
                {baseSilhouette.getWidth(), baseSilhouette.getHeight()},
                {baseSilhouette.getWidth(), baseSilhouette.getHeight()}
        };
        float partSpacing = Gdx.graphics.getWidth() / (totalParts + 1); // Spacing between parts

        Image[] parts = new Image[totalParts];

        for (int i = 1; i <= totalParts; i++) {
            parts[i - 1] = new Image(new Texture(Gdx.files.internal("satellite_" + i + ".png")));
            parts[i - 1].setPosition(partSpacing * i - parts[i - 1].getWidth() / 2, 50); // Adjust spacing based on asset size

            Image targetArea = new Image(new Texture(Gdx.files.internal("transparent.png"))); // A placeholder, size should match or be slightly larger than parts for easier targeting
            targetArea.setPosition(baseSilhouette.getWidth(),baseSilhouette.getHeight());
            stage.addActor(targetArea);

            final int partIndex = i;

            dragAndDrop.addSource(new DragAndDrop.Source(parts[i - 1]) {
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    if (partPlaced[partIndex - 1]) { // Add this check to prevent dragging parts that are already placed
                        return null;
                    }
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setDragActor(parts[partIndex - 1]); // Use the original part as the drag actor for simplicity
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
                    // Check if the part was dropped close enough to its target position.
                    int partIndex = (Integer) payload.getObject() - 1;
                    Vector2 target = new Vector2(targetPositions[partIndex][0], targetPositions[partIndex][1]);
                    Vector2 dropPoint = new Vector2(x, y);

                    float distance = dropPoint.dst(target);
                    System.out.println("Distance: " + distance); // Print statement
                    if (distance <= 150 && !partPlaced[partIndex]) { // Adjust this value based on your game's layout
                        partsAssembled++;
                        partPlaced[partIndex] = true;
                        parts[partIndex].setTouchable(Touchable.disabled);
                        parts[partIndex].setPosition(target.x,
                                target.y); // Center the part on the target
                        System.out.println("Part " + (partIndex + 1) + " placed."); // Print statement

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
    }
}

