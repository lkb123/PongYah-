package com.ajlk.pongya.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EnemyPaddle extends Actor{
	private Viewport viewport;
	private  Texture texture = new Texture(Gdx.files.internal("ui/paddleLeft.png"));
	private  Sprite enemySprite = new Sprite(texture);


	
	public EnemyPaddle(Viewport viewport) {
		this.viewport = viewport;
		this.setBounds(enemySprite.getX(), enemySprite.getY(), enemySprite.getWidth(), enemySprite.getHeight());
		this.setPosition(new Vector2(viewport.getWorldWidth(),viewport.getWorldHeight()));
		enemySprite.setSize(viewport.getWorldWidth()/30, viewport.getWorldHeight()/4);
		
	}
	
	public float getYPosition(){
		return enemySprite.getY();
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		enemySprite.draw(batch);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public  Rectangle getBoundingRectangle() {
		return enemySprite.getBoundingRectangle();
	}
	
	public void update(float currentVel, Vector2 currentPos ){
		
		float x = enemySprite.getX();
		boolean canUpdate;
		
		if((currentPos.y - enemySprite.getHeight()/2) < (viewport.getWorldHeight()-enemySprite.getHeight()))
			canUpdate = true;
		else
			canUpdate = false;
		
		if(enemySprite.getY()<viewport.getWorldHeight()-enemySprite.getHeight() && currentVel > 0)
			enemySprite.setPosition(x, currentPos.y - enemySprite.getHeight()/2);
		
		if(enemySprite.getY()>viewport.getWorldHeight()-enemySprite.getHeight())
			enemySprite.setPosition(x, viewport.getWorldHeight()- enemySprite.getHeight() );
		

		
		if(enemySprite.getY()>5 && currentVel < 0  && canUpdate)
			enemySprite.setPosition(x, currentPos.y - enemySprite.getHeight()/2);
		
		if(enemySprite.getY()<5 )
			enemySprite.setPosition(x, 0);
	}

	public void setPosition(Vector2 position) {
		enemySprite.setPosition(5, position.y/2 - enemySprite.getHeight()/2);
	}
	
	
	public void dispose(){
		texture.dispose();
	}

	


}
