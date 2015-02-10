package com.ajlk.pongya.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameScreen implements Screen,InputProcessor {
	SpriteBatch batch;
	Sprite background;
	OrthographicCamera camera;
	final float PONG_WORLD_WIDTH = 1024;
	final float PONG_WORLD_HEIGHT = 768;
	
	Viewport viewport;
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		background = new Sprite(new Texture(Gdx.files.internal("ui/bg.png")));
		background.setSize(PONG_WORLD_WIDTH, PONG_WORLD_HEIGHT);
		
		camera = new OrthographicCamera();
		viewport = new StretchViewport(PONG_WORLD_WIDTH, PONG_WORLD_HEIGHT,camera);
		viewport.apply();
		camera.position.set(PONG_WORLD_WIDTH/2,PONG_WORLD_HEIGHT/2,0);
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}
		
		Gdx.gl.glClearColor(1,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		background.draw(batch);
		batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(PONG_WORLD_WIDTH/2,PONG_WORLD_HEIGHT/2,0);
	}

	@Override
	public void pause() {


	}

	@Override
	public void resume() {


	}

	@Override
	public void hide() {


	}

	@Override
	public void dispose() {
		batch.dispose();
		background.getTexture().dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT)
			camera.translate(-1f, 0f);
		if (keycode == Input.Keys.RIGHT)
			camera.translate(1f,0f);	
		if (keycode == Input.Keys.UP)
			camera.translate(0f,1f);
		if (keycode == Input.Keys.DOWN)
			camera.translate(0f,-1f);
		
		return false;		
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
