package com.ajlk.pongya.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameOverScreen implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private BitmapFont white;
	private BitmapFont black;
	private TextButton continueButton;
	private TextButton homeButton;
	private boolean gameModeAcce;

	public GameOverScreen(boolean gameModeAcce) {
		this.gameModeAcce = gameModeAcce;
	}

	@Override
	public void show() {
		stage = new Stage(new FitViewport(1280, 720));
		
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		table = new Table(skin);
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		
		white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);		
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = black;
		
		continueButton = new TextButton("Play Again", textButtonStyle);
		continueButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new PongGame(gameModeAcce));
			}
		});
		continueButton.pad(20);

		homeButton = new TextButton("Main Menu", textButtonStyle);
		homeButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});
		homeButton.pad(20);

		table.add(continueButton).minWidth(50).spaceRight(20);
		table.add(homeButton).minWidth(50);
		stage.addActor(table);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new PongGameMenu());
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
		this.dispose();

	}

	@Override
	public void dispose() {
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		white.dispose();
		black.dispose();

	}

}
