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
	private Texture texture = new Texture(Gdx.files.internal("ui/paddleLeft.png"));
	private Sprite enemySprite = new Sprite(texture);

	private Vector2 enemyPos;
	private float defaultVel = 10;

	
	public EnemyPaddle(Viewport viewport) {
		this.viewport = viewport;
		this.setBounds(enemySprite.getX(), enemySprite.getY(), enemySprite.getWidth(), enemySprite.getHeight());
		this.setPosition(new Vector2(viewport.getWorldWidth(),viewport.getWorldHeight()));
		this.enemySprite.setSize(viewport.getWorldWidth()/30, viewport.getWorldHeight()/4);
		
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
	
	public void update(){
		this.enemyPos = new Vector2(enemySprite.getX(),enemySprite.getY());

		enemySprite.setPosition(enemyPos.x, enemyPos.y + defaultVel);
	}

	public void setPosition(Vector2 position) {
		this.enemySprite.setPosition(5, position.y/2 - enemySprite.getHeight()/2);
		
	}
	
	public void dispose(){
		texture.dispose();
	}


}
