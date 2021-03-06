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

public class PlayerPaddle extends Actor{
	
	private Viewport viewport;
	private Texture texture = new Texture(Gdx.files.internal("ui/paddleRight.png"));
	private Sprite playerSprite = new Sprite(texture);

	public PlayerPaddle(Viewport viewport) {
		this.viewport = viewport;
		this.setBounds(playerSprite.getX(), playerSprite.getY(), playerSprite.getWidth(), playerSprite.getHeight());
		this.setTouchable(Touchable.enabled);
		this.setPosition(new Vector2(viewport.getWorldWidth(),viewport.getWorldHeight()));
		this.playerSprite.setSize(viewport.getWorldWidth()/30, viewport.getWorldHeight()/4);
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		playerSprite.draw(batch);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public  Rectangle getBoundingRectangle() {
		return playerSprite.getBoundingRectangle();
	}
	
	public void update(){

	}
	
	public void dispose(){
		texture.dispose();
	}
	
	//PANNING
	public void updatePosition(float y){
			if(playerSprite.getY()<viewport.getWorldHeight()-playerSprite.getHeight()&& y<0)
				playerSprite.setPosition(playerSprite.getX(), playerSprite.getY()+ (-y));
			if(playerSprite.getY()>viewport.getWorldHeight()-playerSprite.getHeight())
				playerSprite.setPosition(viewport.getWorldWidth()-(playerSprite.getWidth() + 7), viewport.getWorldHeight()-playerSprite.getHeight());
			if(playerSprite.getY()>5 && y>0)
				playerSprite.setPosition(playerSprite.getX(), playerSprite.getY()+ (-y));
			if(playerSprite.getY()<5)
				playerSprite.setPosition(viewport.getWorldWidth()-(playerSprite.getWidth() + 7), 0);
	}
	
	//TILTING
	public void updateYposition(float y) {
		if(playerSprite.getY()<viewport.getWorldHeight()-playerSprite.getHeight()&& y<0)
			playerSprite.setPosition(playerSprite.getX(), playerSprite.getY()+ (-y*5));
		if(playerSprite.getY()>viewport.getWorldHeight()-playerSprite.getHeight())
			playerSprite.setPosition(viewport.getWorldWidth()-(playerSprite.getWidth() + 7), viewport.getWorldHeight()-playerSprite.getHeight());
		if(playerSprite.getY()>5 && y>0)
			playerSprite.setPosition(playerSprite.getX(), playerSprite.getY()+ (-y*5));
		if(playerSprite.getY()<5)
			playerSprite.setPosition(viewport.getWorldWidth()-(playerSprite.getWidth() + 7), 0);	
	}

	public void setPosition(Vector2 position) {
		this.playerSprite.setPosition(position.x - (playerSprite.getWidth() + 35), position.y/2 - playerSprite.getHeight()/2);
	}


}
