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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class GameScreen implements Screen,InputProcessor, GestureDetector.GestureListener {
	SpriteBatch batch = new SpriteBatch();
	OrthographicCamera camera = new OrthographicCamera();;
	GestureDetector gestureDetector;
	BitmapFont.TextBounds bounds;
	String score;
	
	final float PONG_WORLD_WIDTH = 1024;
	final float PONG_WORLD_HEIGHT = 768;
	
	Viewport viewport = new FitViewport(PONG_WORLD_WIDTH, PONG_WORLD_HEIGHT,camera);
	
	Sprite background = new Sprite(new Texture(Gdx.files.internal("ui/bg.png")));
	Sprite ball = new Sprite(new Texture(Gdx.files.internal("ui/ball.png")));
	Sprite playerPaddle = new Sprite(new Texture(Gdx.files.internal("ui/paddleRight.png")));
	Sprite aiPaddle = new Sprite(new Texture(Gdx.files.internal("ui/paddleLeft.png")));
	
	float playerPaddleHeight = viewport.getWorldHeight()/4;
	float aiPaddleHeight = viewport.getWorldHeight()/4;
	float ballDiameter = ball.getHeight();
	
	BitmapFont white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
	
	
	@Override
	public void show() {
		viewport.apply();
		camera.position.set(PONG_WORLD_WIDTH/2-ballDiameter/2,PONG_WORLD_HEIGHT/2-ballDiameter/2,0);
		
		background.setSize(PONG_WORLD_WIDTH, PONG_WORLD_HEIGHT);
		ball.setBounds(PONG_WORLD_WIDTH/2, PONG_WORLD_HEIGHT/2, viewport.getWorldWidth()/20, viewport.getWorldWidth()/20);
		playerPaddle.setBounds(PONG_WORLD_WIDTH-playerPaddle.getWidth()-10, 
				PONG_WORLD_HEIGHT/2 - playerPaddleHeight/2,
				playerPaddle.getWidth(), playerPaddleHeight);
		
		aiPaddle.setBounds(10, PONG_WORLD_HEIGHT/2 - aiPaddleHeight/2, 
				aiPaddle.getWidth(),aiPaddleHeight);
		
		score = "100";
		bounds = white.getBounds(score);
		white.scale(1.5f);
		
		gestureDetector = new GestureDetector(this);
		Gdx.input.setInputProcessor(gestureDetector);
	}

	@Override
	public void render(float delta) {
		camera.update();
		
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}
		
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		background.draw(batch);
		ball.draw(batch);
		playerPaddle.draw(batch);
		aiPaddle.draw(batch);
		white.draw(batch, score, 
				viewport.getWorldWidth()/2 - (bounds.width /2), 
				viewport.getWorldHeight() - bounds.height);
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

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		float alpha = background.getColor().a;
		
		if(alpha>=0.f)
			background.setAlpha(alpha-0.25f);
		else
			background.setAlpha(1f);
		
		return true;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		Vector3 touchPos = new Vector3(x,y,0);
		camera.unproject(touchPos);
		
		camera.translate(touchPos.x, touchPos.y);
		
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
