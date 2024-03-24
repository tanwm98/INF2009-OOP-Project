package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MixAndMatchMiniGameScreen;
import com.mygdx.game.Managers.OutputManager;
import com.mygdx.game.Player;



public class Spaceship extends Entity {
    private Texture tex;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private boolean ishit;
    float flickerTimer = 0;
    private MyGdxGame game;
    private boolean playerFlag;
    private Player player;

    public Spaceship(String filePath, float posX, float posY,float speedY,OrthographicCamera camera,boolean aiFlag,boolean playerFlag,boolean Collideable,Player player, MyGdxGame game)
    {
        tex = new Texture(Gdx.files.internal(filePath));
        super.setX(posX);
        super.setY(posY);
        super.setYSpeed(speedY);
        super.setControl(aiFlag);
        super.setCollideable(Collideable);
        this.camera = camera;
        super.setWidth(tex.getWidth());
        super.setHeight(tex.getHeight());
        super.setPlayerControlled(playerFlag);
        this.game = game;
        this.player = player;
    }

    @Override
    public void render()
    {
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin(); // Start drawing
        if(ishit) {
            flickerTimer += Gdx.graphics.getDeltaTime();
            if (flickerTimer < 0.1f) {
                batch.setColor(1, 1, 1, 0); // Fully transparent
            } else if (flickerTimer < 0.2f) {
                batch.setColor(1, 1, 1, 1); // Fully opaque
            } else {
                flickerTimer = 0;
            }
        }
        getoutputManager().draw(batch,tex, getX(), getY(), tex.getWidth(),tex.getHeight()); // Draw the spaceship texture at the spaceship's position
        batch.end(); // End drawing
        ishit = false;
    }
    public void dispose()
    {
        tex.dispose();
        batch.dispose();
    }

    public void move()
    {
        float delta = Gdx.graphics.getDeltaTime();
        float moveSpeed = super.getYSpeed() * delta;

        if(getinputManager().isRightKeyPressed() || getinputManager().isDKeyPressed())
        {
            setX(getX() + moveSpeed);
            if(getX() - getWidth() / 2 > Gdx.graphics.getWidth())
            {
                setX(-getWidth() / 2); // Wrap around
            }
        }
        else if(getinputManager().isLeftKeyPressed() || getinputManager().isAKeyPressed())
        {
            setX(Math.max(0, getX() - moveSpeed)); // Prevent from going off screen
        }

        if(getinputManager().isUpKeyPressed() || getinputManager().isWKeyPressed())
        {
            setY(Math.min(Gdx.graphics.getHeight() - getHeight(), getY() + moveSpeed)); // Prevent from going off screen
        }
        else if(getinputManager().isDownKeyPressed() || getinputManager().isSKeyPressed())
        {
            setY(Math.max(0, getY() - moveSpeed)); // Prevent from going off screen
        }
    }
    public void collide(boolean collide) {
        if (collide) {
            ishit = true;
            game.setScreen(new MixAndMatchMiniGameScreen(game,player)); // Transition to the minigame screen
        }
    }
}
