package com.ajlk.pongya.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class AchievementScreen implements Screen {
	
	Texture fbTxt, twitterTxt;
	Stage stage;
	TextureAtlas atlas;
	Skin skin;
	Texture headingTexture;
	Image headingImage;
	Table table;
	BitmapFont white;
	BitmapFont black;
	Image fbImg, twitterImg;
	Label highScore;
	TextField highScoreText;
	
	@Override
	public void show() {
		stage = new Stage(new FitViewport(1280, 720));
		Gdx.input.setInputProcessor(stage);
		fbTxt = new Texture("sharing/fb.jpg");
		twitterTxt = new Texture("sharing/twitter.jpg");
		
		fbImg = new Image(fbTxt);
		twitterImg = new Image(twitterTxt);
		
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		headingTexture =  new Texture("ui/heading.png");
		headingImage = new Image(headingTexture);


		table = new Table(skin);
		table.setBounds(0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
		
		white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);		
		
		//highScore = new Label("High Score", skin);
		//highScoreText = new TextField("0", skin);
		
		//creating buttons
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = black;

		fbImg.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
			
		});
		
		//buttonTwitter = new TextButton("Twitter", textButtonStyle);
		twitterImg.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
			
		});
		
		//table.add(highScore);
	    //table.add(highScoreText).width(100);
	    //table.row();
		table.add(headingImage).spaceBottom(100).minWidth(700).minHeight(100);
		table.row();
		table.add(fbImg).spaceBottom(20).minWidth(250);
		table.row();
		table.add(twitterImg).spaceBottom(20).minWidth(250);
		table.row();
		//table.
		
		stage.addActor(table);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		}
		
		stage.act(delta);
		stage.draw();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		fbTxt.dispose();
		twitterTxt.dispose();
	}

}
