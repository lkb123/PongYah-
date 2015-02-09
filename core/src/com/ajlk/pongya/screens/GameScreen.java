package com.ajlk.pongya.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameScreen implements Screen {
	private SpriteBatch batch;
	private Sprite ballSprite;
	private Texture ballImage;
	private World world;
	private Body body;
	private CircleShape circle;
	
	private Box2DDebugRenderer dbr;
	private OrthographicCamera camera;
	
	@Override
	public void show() {
		//camera = new OrthographicCamera(Gdx.graphics.getWidth() * PIXEL_TO_METER,Gdx.graphics.getHeight() * PIXEL_TO_METER);
		batch = new SpriteBatch();
		
		ballImage = new Texture("ui/ball.png");
		ballSprite = new Sprite(ballImage);
		
		ballSprite.setPosition(Gdx.graphics.getWidth() / 2 - ballSprite.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);
		
		world = new World(new Vector2(0, 0), true);
		//dbr = new Box2DDebugRenderer();
		
		BodyDef ballBody = new BodyDef();
		ballBody.type = BodyDef.BodyType.DynamicBody;
        
		ballBody.position.set(ballSprite.getX(), ballSprite.getY());
        body = world.createBody(ballBody);
		body.isBullet();
		body.setLinearVelocity(10,10);
        
        circle = new CircleShape();
        circle.setRadius(6f);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f; 
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        
        Fixture fixture = body.createFixture(fixtureDef);
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}
		
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);

		ballSprite.setPosition(body.getPosition().x, body.getPosition().y);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //dbr.render(world, camera.combined);
        
        batch.begin();
        batch.draw(ballSprite, ballSprite.getX(), ballSprite.getY());
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
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
		circle.dispose();
		batch.dispose();
		ballImage.dispose();
		world.dispose();
	}

}
