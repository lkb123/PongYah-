package com.ajlk.pongya.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ball extends Actor{
	private Texture texture = new Texture(Gdx.files.internal("ui/ball.png"));
	private Sprite ballSprite = new Sprite(texture);

	private Vector2 ballPos;
	private Vector2 ballVel = new Vector2(0,0);
	private Viewport viewport;
	private Integer score = 0;
	private boolean gameOver = false;

	public Ball(Viewport viewport) {
		this.viewport = viewport;
		this.setBounds(this.ballSprite.getX(), this.ballSprite.getY(), this.ballSprite.getWidth(), this.ballSprite.getHeight());
		this.setTouchable(Touchable.enabled);
		this.ballSprite.setScale(2);
		this.ballSprite.setPosition(viewport.getWorldWidth()/2-ballSprite.getWidth()/2, viewport.getWorldHeight()/2+ballSprite.getHeight()/2);
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		ballSprite.draw(batch);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public float getBallVelX(){
		return ballVel.x;
	}
	
	public Vector2 getBallPos(){
		return this.ballPos;
	}
	
	public void reverseVelocityX(){
		if(ballVel.x <30 && ballVel.x >-30){
			if(ballVel.x >0)
				ballVel.x = (ballVel.x + 1) *-1;
			else
				ballVel.x = (ballVel.x -1) *-1;
		}else
			ballVel.x = ballVel.x  *-1;
			
	}
	public void reverseVelocityY() {
		if(ballVel.y <20 && ballVel.y >-20){
			if(ballVel.y >0)
				ballVel.y = (ballVel.y + 1) *-1;
			else
				ballVel.y = (ballVel.y -1) *-1;
		}else
			ballVel.y = ballVel.y  *-1;
	}
	
	public void setBallVelocity(float x,float y){
		this.ballVel = new Vector2(x, y);
	}
	
	public void update(){
		this.ballPos = new Vector2(ballSprite.getX(),ballSprite.getY());
		cornerCollisionChecker();
		ballSprite.setPosition(ballPos.x+ballVel.x, ballPos.y+ballVel.y);
		
		System.out.println("X: "+ ballVel.x +" Y: "+ ballVel.y);
	}

	public  Rectangle getBoundingRectangle() {
		return ballSprite.getBoundingRectangle();
	}
	
	public void cornerCollisionChecker(){
		Random rand = new Random();
		if ((ballPos.y >= (viewport.getWorldHeight() - ballSprite.getHeight() -17)) || (ballPos.y <= ballSprite.getHeight()/2 +2)){
				ballVel.y = -ballVel.y;
				Assets.table[rand.nextInt(2)].play();
		}
		if (ballPos.x <= 0){
			resetBall();
			score = score + 10;
		}
		if (ballPos.x >= (viewport.getWorldWidth() - ballSprite.getWidth())){
			gameOver = true;
		}
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
	
	private void resetBall() {
		ballPos.x = viewport.getWorldWidth()/2-ballSprite.getWidth()/2;
		ballPos.y = viewport.getWorldHeight()/2+ballSprite.getHeight()/2;
		ballVel= new Vector2(0,0);
		
	}

	public void dispose(){
		texture.dispose();
	}

	public Integer getScore() {
		return this.score;
	}


}
