package com.ajlk.pongya.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu implements Screen {
	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private TextButton buttonExit, buttonPlay, buttonAchievement, buttonCredits;
	private BitmapFont white,black;
	private Texture headingTexture;
	private Image headingImage;

	
	@Override
	public void show() {

		stage = new Stage(new FitViewport(1280, 720));

		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		
		atlas = new TextureAtlas("ui/button.pack");
		skin = new Skin(atlas);
		
		headingTexture =  new Texture("ui/heading.png");
		headingImage = new Image(headingTexture);
		

		table = new Table(skin);
		table.setBounds(0, 0, stage.getWidth(), stage.getHeight());
		
		white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);		
		
		
		
		//creating buttons
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = black;
		
		
		buttonExit = new TextButton("Exit", textButtonStyle);
		buttonExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Assets.dispose();
				Gdx.app.exit();
			}
			
		});
		buttonExit.pad(20);
		
		
		buttonPlay = new TextButton("Play", textButtonStyle);
		buttonPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Assets.buttonPressed.play();
				((Game)Gdx.app.getApplicationListener()).setScreen(new PongGameMenu());
			}
		});
		buttonPlay.pad(20);
		
		buttonAchievement = new TextButton("Achievement", textButtonStyle);
		buttonAchievement.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Assets.buttonPressed.play();
				((Game)Gdx.app.getApplicationListener()).setScreen(new AchievementScreen());
			}
		});
		buttonAchievement.pad(20);
		
		buttonCredits = new TextButton("Credits", textButtonStyle);
		buttonCredits.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Assets.buttonPressed.play();
				((Game)Gdx.app.getApplicationListener()).setScreen(new CreditsScreen());
			}
		});
		buttonCredits.pad(20);
		
		table.add(headingImage).spaceBottom(100).minWidth(800).minHeight(100);
		table.row();
		table.add(buttonPlay).spaceBottom(20).minWidth(250);
		table.row();
		table.add(buttonAchievement).expandX().spaceBottom(20).minWidth(250);
		table.row();
		table.add(buttonCredits).expandX().spaceBottom(20).minWidth(250);
		table.row();
		table.add(buttonExit).minWidth(250);
		stage.addActor(table);
		
		if(!Assets.isPlayingBackgroundMusic){
			Assets.backgroundMusicId = Assets.backgroundMusic.loop();
			Assets.isPlayingBackgroundMusic = true;
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//table.setDebug(true);
		
		stage.act(delta);
		stage.draw();
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
