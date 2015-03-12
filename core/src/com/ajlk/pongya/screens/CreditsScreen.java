package com.ajlk.pongya.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class CreditsScreen implements Screen {
	
	private SpriteBatch batch = new SpriteBatch();
	private Stage stage = new Stage(new FitViewport(1600,900));
	private BitmapFont white, black;
	private String heading;
	private TextBounds headingBounds;
	TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonBack;

	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);	
		
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = black;
		
		table = new Table(skin);
		table.setBounds(0, -100, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
		
		heading = "Programmer\n\n"+"Abdul Jalil Laguindab\n"+"Louie Kert Basay\n";
		headingBounds = white.getMultiLineBounds(heading);
		white.scale(1);
		
		buttonBack = new TextButton("Back", textButtonStyle);
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Assets.buttonPressed.play();
				((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});
		//buttonBack.setHeight(50);
		//buttonBack.setWidth(250);
		//buttonBack.setBounds(0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
		//buttonBack.setOrigin(Align.center);
		
		table.add(buttonBack).minWidth(250).minHeight(50);
		table.row();
		
		//stage.addActor(buttonBack);
		stage.addActor(table);
		Gdx.input.setCatchBackKey(true);
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
