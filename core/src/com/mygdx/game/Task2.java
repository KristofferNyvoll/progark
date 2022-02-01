package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Task2 extends ApplicationAdapter {
    SpriteBatch batch;
    Texture texture;
    Sprite sprite;
    BitmapFont textCoordinates;
    float speedX = 0;
    float speedY = 0;
    int x;
    int y;

    int toX;
    int toY;



    @Override
    public void create () {
        batch = new SpriteBatch();
        texture = new Texture("attackhelicopter.png");
        sprite = new Sprite(texture);
        textCoordinates = new BitmapFont();
        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;
        toX = x;
        toY = y;
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                if (screenX == x) {
                    speedX = 0;
                } else {
                    toX = screenX;
                    speedX = (toX - x) / 10;
                    if (speedX > 0) {
                        sprite.setFlip(true, false);
                    } else {
                        sprite.setFlip(false, false);
                    }
                }
                if (screenY == y) {
                    speedY = 0;
                } else {
                    toY = Gdx.graphics.getHeight() - screenY;
                    speedY = (toY - y) / 10;
                }
                return true;
            }
        });
    }
    @Override
    public void render () {
        Gdx.gl.glClearColor(256, 0, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Math.abs(toX - x) < 10){
            speedX = 0;
        }
        if (Math.abs(toY - y) < 10){
            speedY = 0;
        }

        //Move the chopper according to the speed.
        x += speedX;
        y += speedY;

        batch.begin();
        //Draw the ball so the center is at x and y. Normally it would be drawn from the lower left corner.
        batch.draw(sprite, x - texture.getWidth() / 2, y - texture.getHeight() / 2);
        textCoordinates.draw(batch, "x: " + x + ", y: " +y, 10, Gdx.graphics.getHeight()-10);
        batch.end();
    }


    @Override
    public void dispose () {
        batch.dispose();
        texture.dispose();
    }
}

