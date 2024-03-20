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


public class MixAndMatchMiniGameScreen implements Screen{
    private Stage stage;
    private DragAndDrop dragAndDrop;
    private int partsAssembled = 0;
    private final int totalParts = 3;
    private MyGdxGame game;

    public MixAndMatchMiniGameScreen(MyGdxGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        dragAndDrop = new DragAndDrop();
        setupBaseSilhouette();
        setupParts();
    }
    private void setupBaseSilhouette() {
        Image baseSilhouette = new Image(new Texture(Gdx.files.internal("satellite_silhouette.png")));
        baseSilhouette.setPosition(Gdx.graphics.getWidth() / 2 - baseSilhouette.getWidth() / 2, Gdx.graphics.getHeight() / 2 - baseSilhouette.getHeight() / 2); // Adjust position based on your game's layout
        stage.addActor(baseSilhouette);
    }

    private void setupParts() {
        // Define the target positions on the silhouette for each part
        float[][] targetPositions = new float[][]{
                {30, 30}, // Example positions for each part
                {30, 60},
                {40, 30}
        };
        float partSpacing = Gdx.graphics.getWidth() / (totalParts + 1); // Spacing between parts
        for (int i = 1; i <= totalParts; i++) {
            Image part = new Image(new Texture(Gdx.files.internal("satellite_" + i + ".png")));
            part.setPosition(partSpacing * i - part.getWidth() / 2, 50); // Adjust spacing based on asset size
            final int partIndex = i;
            stage.addActor(part);

            // Note: Ensure "transparent.png" exists and is set correctly for target areas
            Image targetArea = new Image(new Texture(Gdx.files.internal("transparent.png"))); // A placeholder, size should match or be slightly larger than parts for easier targeting
            targetArea.setSize(300, 300); // Match the part size for easier drop targeting
            targetArea.setPosition(targetPositions[i-1][0], targetPositions[i-1][1]);
            stage.addActor(targetArea);

            dragAndDrop.addSource(new DragAndDrop.Source(part) {
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setDragActor(part); // Use the original part as the drag actor for simplicity
                    payload.setObject(partIndex); // Identifier for the part
                    return payload;
                }
            });


            dragAndDrop.addTarget(new DragAndDrop.Target(targetArea) {
                public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    return true; // Always accept drags
                }

                public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                    if ((Integer) payload.getObject() == partIndex) {
                        partsAssembled++;
                        part.remove(); // This assumes you want the part to disappear upon correct placement
                        checkGameCompletion();
                    }
                }
            });
        }
    }
    private void checkGameCompletion() {
        if (partsAssembled == totalParts) {
            System.out.println("Game Completed!");
            // Here you can transition to a game over or completion screen
        }
    }


    public void show() {}

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

