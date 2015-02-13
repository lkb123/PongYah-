package com.ajlk.pongya.screens;

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
	
	public void reverseVelocityX(){
		this.ballVel.x = this.ballVel.x*-1;
	}
	public void reverseVelocityY() {
		this.ballVel.y = this.ballVel.y*-1;
	}
	public void setBallVelocity(float x,float y){
		this.ballVel = new Vector2(x, y);
	}
	
	public void update(){
		this.ballPos = new Vector2(ballSprite.getX(),ballSprite.getY());
		cornerCollisionChecker();
		ballSprite.setPosition(ballPos.x+ballVel.x, ballPos.y+ballVel.y);
	}

	public  Rectangle getBoundingRectangle() {
		return ballSprite.getBoundingRectangle();
	}
	
	public void cornerCollisionChecker(){
		if ((ballPos.y >= (viewport.getWorldHeight() - ballSprite.getHeight() -17)) || (ballPos.y <= ballSprite.getHeight()/2 +2)){
				ballVel.y = -ballVel.y;
		}
		if ((ballPos.x >= (viewport.getWorldWidth() - ballSprite.getWidth() - 17)) || (ballPos.x <= ballSprite.getHeight()/2 + 2)){
			ballVel.x = -ballVel.x;
		}
		
	}
	
	public void dispose(){
		texture.dispose();
	}


}
