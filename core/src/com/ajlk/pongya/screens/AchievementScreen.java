package com.ajlk.pongya.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
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
	//TextField highScoreText;
	LabelStyle labelStyle;
	
	private Preferences highScores;
	private int highScoreTilt;
	private int highScoreSwipe;
	private Label highScore2;
	
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
		
		/*
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
				//Gdx.app.exit();
			}
			
		});
		
		twitterImg.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//Gdx.app.exit();
			}
			
		});
		
		*/
		
		highScores = Gdx.app.getPreferences("highScores");
		highScoreTilt = highScores.getInteger("tiltScore", 0);
		highScoreSwipe = highScores.getInteger("swipeScore",0);
		
		
		labelStyle = new LabelStyle(white, Color.WHITE);
		highScore = new Label("Tilt High Score: " + highScoreTilt, labelStyle);
		highScore.scaleBy(3);
		highScore2 = new Label("Swipe High Score: " + highScoreSwipe, labelStyle);
		highScore2.scaleBy(3);
		
		table.add(headingImage).spaceBottom(100).minWidth(700).minHeight(100);
		table.row();
		table.add(highScore).width(250).spaceBottom(50);
		table.row();
		table.add(highScore2).width(250);
		table.row();
		table.row();
				
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
