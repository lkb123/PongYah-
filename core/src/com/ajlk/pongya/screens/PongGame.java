package com.ajlk.pongya.screens;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PongGame implements Screen, GestureListener {
	
	private boolean gameModeAcce;
	private SpriteBatch batch = new SpriteBatch();
	
	final float GAMESCREEN_WIDTH = 1600;
	final float GAMESCREEN_HEIGHT = 900;
	
	private GestureDetector gestureDetector = new GestureDetector(this);
	private OrthographicCamera camera = new OrthographicCamera();
	private Viewport viewport = new FitViewport(GAMESCREEN_WIDTH,GAMESCREEN_HEIGHT,camera); 
	private Stage stage = new Stage(viewport);
	private Ball ball = new Ball(viewport);
	private PlayerPaddle playerPaddle = new PlayerPaddle(viewport);
	private EnemyPaddle enemyPaddle = new EnemyPaddle(viewport);
	
	private BitmapFont white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
	private BitmapFont.TextBounds bounds;
	private String score;
	
	Sprite background = new Sprite(new Texture(Gdx.files.internal("ui/bg.png")));


	public PongGame(boolean gameMode) {
		this.gameModeAcce = gameMode;
	}

	@Override
	public void show() {

		stage.getViewport().apply();
		stage.getCamera().position.set(GAMESCREEN_WIDTH/2,GAMESCREEN_HEIGHT/2,0);
		
		background.setSize(GAMESCREEN_WIDTH, GAMESCREEN_HEIGHT);
		
		stage.addActor(ball);
		stage.addActor(playerPaddle);
		stage.addActor(enemyPaddle);
		
		score = "100";
		
		bounds = white.getBounds(score);
		white.scale(1.5f);
		
		Gdx.input.setInputProcessor(gestureDetector);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.getCamera().update();
		
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		background.draw(batch);
		white.draw(batch, score, 
				viewport.getWorldWidth()/2 - (bounds.width /2), 
				viewport.getWorldHeight() - bounds.height);
		batch.end();
		
		ball.update();
		
		stage.getBatch().setProjectionMatrix(camera.combined); 
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		this.score = String.valueOf(getAcceY());
		
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}
		
		if(ball.getBoundingRectangle().overlaps(playerPaddle.getBoundingRectangle())){
			if(playerPaddle.getBoundingRectangle().getY() > ball.getBoundingRectangle().getY() ||
					playerPaddle.getBoundingRectangle().getY()+playerPaddle.getHeight() < ball.getBoundingRectangle().getY())
			{
				ball.reverseVelocityX();
				ball.reverseVelocityY();
			}
			else
				ball.reverseVelocityX();
		}
		
		if(ball.getBoundingRectangle().overlaps(enemyPaddle.getBoundingRectangle())){
			if(enemyPaddle.getBoundingRectangle().getY() >= ball.getBoundingRectangle().getY() ||
					enemyPaddle.getBoundingRectangle().getY()+enemyPaddle.getHeight() <= ball.getBoundingRectangle().getY())
			{
				ball.reverseVelocityX();
				ball.reverseVelocityY();
			}
			else
				ball.reverseVelocityX();
		}
		
		if(gameModeAcce){
			playerPaddle.updateYposition(getAcceY());
		}
		
	}

	private float getAcceY() {
		return Gdx.input.getAccelerometerX();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.getCamera().position.set(GAMESCREEN_WIDTH/2,GAMESCREEN_HEIGHT/2,0);
	}

	@Override
	public void pause() {


	}

	@Override
	public void resume() {


	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
		white.dispose();
		ball.dispose();
		playerPaddle.dispose();
		enemyPaddle.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Vector3 pos = new Vector3(x,y,0);
		camera.unproject(pos);
		Random rand = new Random();
		int[] posNeg = {1,-1};
		if(ball.getBoundingRectangle().contains(pos.x,pos.y))
			ball.setBallVelocity(10, rand.nextInt(20)*posNeg[rand.nextInt(2)]);
		return true;
		
	}

	@Override
	public boolean longPress(float x, float y) {
		((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		return true;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if(this.gameModeAcce){
			//do nothing
		}else
			playerPaddle.updatePosition(deltaY);
		
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}
	

}
