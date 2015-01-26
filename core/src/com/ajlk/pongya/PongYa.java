package com.ajlk.pongya;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PongYa extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("images/ajlkTransparent.png");
	}

	@Override
	public void render () {
		camera.update();
        
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, -20, -50);
		batch.end();
	}
	
	@Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        camera = new OrthographicCamera(2f * aspectRatio, 2f);
    }
}
