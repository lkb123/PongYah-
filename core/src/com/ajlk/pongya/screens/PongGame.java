package com.ajlk.pongya.screens;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PongGame implements Screen, GestureListener {
	
	public final int GAME_READY = 0; 
	public final int GAME_RUNNING = 1; 
	public final int GAME_PAUSED = 2; 
	public final int GAME_OVER = 4;
	public int state;
	
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
	private int scorePerPaddle = 0;
	private String score = "0";
	private Preferences highScores;
	private int highScoreTilt;
	private int highScoreSwipe;
	private int currentPlayerScore;
	
	private Sprite background = new Sprite(new Texture(Gdx.files.internal("ui/bg.png")));
	private ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	


	public PongGame(boolean gameMode) {
		this.gameModeAcce = gameMode;
		this.state = this.GAME_READY;
		
	}

	@Override
	public void show() {
		Assets.loadGameSound();
		Assets.backgroundMusic.setVolume(Assets.backgroundMusicId, (float) 0.3);
		
		stage.getViewport().apply();
		stage.getCamera().position.set(GAMESCREEN_WIDTH/2,GAMESCREEN_HEIGHT/2,0);
		
		background.setSize(GAMESCREEN_WIDTH, GAMESCREEN_HEIGHT);
		
		stage.addActor(ball);
		stage.addActor(playerPaddle);
		stage.addActor(enemyPaddle);
		
		bounds = white.getBounds(score);
		white.scale(1.5f);
		
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setInputProcessor(gestureDetector);
		Gdx.input.setCatchBackKey(true);
		
		highScores = Gdx.app.getPreferences("highScores");
		highScoreTilt = highScores.getInteger("tiltScore", 0);
		highScoreSwipe = highScores.getInteger("swipeScore",0);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(ball.isGameOver()){
			state = GAME_OVER;
		}
		
		
		
		switch (state) {
	    case GAME_READY:
	    	drawGame();
	        break;
	    case GAME_RUNNING:
	    	drawGame();
	    	updateGame();
	        break;
	    case GAME_PAUSED:
	        break;
	    case GAME_OVER:
	    	gameOver();
	        break;
	    }
		
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}
	}

	private void gameOver() {
		boolean beatHighScore;
		
		if(gameModeAcce){
			if(this.highScoreTilt > this.currentPlayerScore){
				beatHighScore = false;
			}else{
				beatHighScore = true;
				highScores.putInteger("tiltScore", currentPlayerScore);
				highScores.flush();
			}
		}else{
			if(this.highScoreSwipe > this.currentPlayerScore){
				beatHighScore = false;
			}else{
				beatHighScore = true;
				highScores.putInteger("swipeScore", currentPlayerScore);
				highScores.flush();
			}
		}
		((Game)Gdx.app.getApplicationListener()).setScreen(new GameOverScreen(this.gameModeAcce,currentPlayerScore,beatHighScore));
	}

	private void drawGame() {
		stage.getCamera().update();
		
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		background.draw(batch);
		
		white.draw(batch, score, 
				viewport.getWorldWidth()/2 - (bounds.width /2), 
				viewport.getWorldHeight() - bounds.height);
		batch.end();
		
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.line(40, 0, 40, viewport.getWorldHeight(),Color.BLACK, Color.BLACK);
		shapeRenderer.line(viewport.getWorldWidth() - 45, 0, viewport.getWorldWidth() - 45, viewport.getWorldHeight(),Color.BLACK, Color.BLACK);
		shapeRenderer.end();
		
		stage.getBatch().setProjectionMatrix(camera.combined); 
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		
	}

	private void updateGame() {
		if(gameModeAcce){
			playerPaddle.updateYposition(getAcceY());
		}
		
		detectAndBallPaddleCollision();
		ball.update();
		Vector2 currentPos = ball.getBallPos();
		float currentVel = ball.getBallVelY();
		enemyPaddle.update(currentVel,currentPos);
		int totalScore = ball.getScore()+scorePerPaddle;
		this.currentPlayerScore = totalScore;
		this.score = String.valueOf(totalScore);
	}

	private void detectAndBallPaddleCollision() {
		if(ball.getBoundingRectangle().overlaps(playerPaddle.getBoundingRectangle())){
			Assets.paddle1.play();
			if(playerPaddle.getBoundingRectangle().getY() > ball.getBoundingRectangle().getY() ||
					playerPaddle.getBoundingRectangle().getY()+playerPaddle.getHeight() < ball.getBoundingRectangle().getY())
			{
				ball.reverseVelocityX();
				ball.reverseVelocityY();
			}
			else
				{
				ball.reverseVelocityX();
			}
		}
		
		if(ball.getBoundingRectangle().overlaps(enemyPaddle.getBoundingRectangle())){
			Assets.paddle2.play();
			if(enemyPaddle.getBoundingRectangle().getY() >= ball.getBoundingRectangle().getY() ||
					enemyPaddle.getBoundingRectangle().getY()+enemyPaddle.getHeight() <= ball.getBoundingRectangle().getY())
			{
				ball.reverseVelocityX();
				ball.reverseVelocityY();
				scorePerPaddle += 1;
			}
			else{
				ball.reverseVelocityX();
				scorePerPaddle += 1;
			}
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
		this.state = GAME_PAUSED;
	}

	@Override
	public void resume() {
		this.state = GAME_RUNNING;
	}

	@Override
	public void hide() {
		//this.dispose();
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
		
		if(this.state == GAME_READY){
			this.state = GAME_RUNNING;
			initializeBallMovement();
		}
		return true;
		
	}

	private void initializeBallMovement() {
			Random rand = new Random();
			int[] posNeg = {1,-1};
			
			ball.setBallVelocity(10, 5*posNeg[rand.nextInt(2)]);
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		if(this.gameModeAcce == false && this.state == GAME_RUNNING)
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
