package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
	Sprite sprite;
	float speedX = -5f;
	float speedY = 3f;
	int x;
	int y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		texture = new Texture("attackhelicopter.png");
		sprite = new Sprite(texture);
		x = Gdx.graphics.getWidth()/2;
		y = Gdx.graphics.getHeight()/2;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//When the helis x position is on either side of the screen.
		//The width of the sprite is taken into account.
		if (x > Gdx.graphics.getWidth() - texture.getWidth() / 2 || x < 0 + texture.getWidth() / 2) {
			//Here we flip the speed, so it bonces the other way.
			speedX = -speedX;
			sprite.flip(true, false);
		}
		//Same as above, but with on the y-axis.
		if (y > Gdx.graphics.getHeight() - texture.getHeight() / 2 || y < 0 + texture.getHeight() / 2) {
			speedY = -speedY;
		}

		//Move the ball according to the speed.
		x += speedX;
		y += speedY;

		batch.begin();
		//Draw the ball so the center is at x and y. Normally it would be drawn from the lower left corner.
		batch.draw(sprite, x - texture.getWidth() / 2, y - texture.getHeight() / 2);
		batch.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		texture.dispose();
	}
}
