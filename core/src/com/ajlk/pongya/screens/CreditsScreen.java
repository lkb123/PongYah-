package com.ajlk.pongya.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class CreditsScreen implements Screen {
	
	private SpriteBatch batch = new SpriteBatch();
	private Stage stage = new Stage(new FitViewport(1600,900));
	private BitmapFont white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
	private String heading;
	private TextBounds headingBounds;

	
	@Override
	public void show() {

		Gdx.input.setCatchBackKey(true);
		
		heading = "Programmer\n\n"+"Abdul Jalil Laguindab\n"+"Louie Kert Basay\n";
		headingBounds = white.getMultiLineBounds(heading);
		white.scale(1);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.setProjectionMatrix(stage.getCamera().combined);
		white.drawMultiLine(batch, heading, 
				0,
				stage.getViewport().getWorldHeight()/2 + headingBounds.height,
				stage.getViewport().getWorldWidth(),
				BitmapFont.HAlignment.CENTER);
		batch.end();
		
		stage.act(delta);
		stage.draw();
		

		
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}


}
