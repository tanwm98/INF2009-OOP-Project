package com.mygdx.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Player;
import com.mygdx.game.Managers.InputManager;
import com.mygdx.game.Managers.OutputManager;

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
        super.setCollideable(Collideable);
        this.camera = camera;
        super.setWidth(tex.getWidth());
        super.setHeight(tex.getHeight());
        this.game = game;
        this.player = player;
    }

    @Override
    public void render() {
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if(ishit && getCollisionCD() > 0) {
            flickerTimer += Gdx.graphics.getDeltaTime();
            if (flickerTimer < 0.3f) {
                batch.setColor(1, 1, 1, 0); // Fully transparent
            } else if (flickerTimer < 0.6f) {
                batch.setColor(1, 1, 1, 1); // Fully opaque
            } else {
                flickerTimer = 0;
            }
        } else {
            batch.setColor(1, 1, 1, 1); // Fully opaque
        }
        OutputManager.getInstance().draw(batch,tex, getX(), getY(), tex.getWidth(),tex.getHeight()); // Draw the spaceship texture at the spaceship's position
        batch.end();
    }
    @Override
    public void update(float delta) {
        super.update(delta);
        if(ishit && getCollisionCD() > 0) {
            flickerTimer += delta;
        }
    }
    public void move()
    {
        float delta = Gdx.graphics.getDeltaTime();
        float moveSpeed = super.getYSpeed() * delta;

        if(InputManager.getInstance().isRightKeyPressed() || InputManager.getInstance().isDKeyPressed())
        {
            setX(getX() + moveSpeed);
            if(getX() - getWidth() / 2 > Gdx.graphics.getWidth())
            {
                setX(-getWidth() / 2); // Wrap around
            }
        }
        else if(InputManager.getInstance().isLeftKeyPressed() || InputManager.getInstance().isAKeyPressed())
        {
            setX(Math.max(0, getX() - moveSpeed)); // Prevent from going off screen
        }

        if(InputManager.getInstance().isUpKeyPressed() ||InputManager.getInstance().isWKeyPressed())
        {
            setY(Math.min(Gdx.graphics.getHeight() - getHeight(), getY() + moveSpeed)); // Prevent from going off screen
        }
        else if(InputManager.getInstance().isDownKeyPressed() || InputManager.getInstance().isSKeyPressed())
        {
            setY(Math.max(0, getY() - moveSpeed)); // Prevent from going off screen
        }
    }
    public void collide(boolean collide) {
        if (collide) {
            if (collide && getCollisionCD() <= 0) {
                ishit = true;
                setCollisionCD(getCD_period());
            }
        }
        else
        {
            ishit = false;
        }
    }
    public void dispose()
    {
        tex.dispose();
        batch.dispose();
    }
}
