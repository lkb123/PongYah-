package com.ajlk.pongya.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Assets {
	
	public static Sound paddle1;
	public static Sound paddle2;
	public static Sound table[] = new Sound[2];
	public static Sound buttonPressed;
	public static Sound backgroundMusic;
	public static Sound highScore;
	
	public static boolean isPlayingBackgroundMusic = false;
	
	public static void load(){
		paddle1 = Gdx.audio.newSound(Gdx.files.internal("sounds/paddleSound1.mp3"));
		paddle2 = Gdx.audio.newSound(Gdx.files.internal("sounds/paddleSound2.mp3"));
		table[0] = Gdx.audio.newSound(Gdx.files.internal("sounds/tableSound1.mp3"));
		table[1] = Gdx.audio.newSound(Gdx.files.internal("sounds/tableSound2.mp3"));
		buttonPressed = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonSound.wav"));
		backgroundMusic =  Gdx.audio.newSound(Gdx.files.internal("sounds/backgroundMusic.wav"));
		highScore =  Gdx.audio.newSound(Gdx.files.internal("sounds/highScore.wav"));
	}

	public static void dispose() {
		paddle1.dispose();
		paddle2.dispose();
		table[0].dispose();
		table[1].dispose();
		buttonPressed.dispose();
		backgroundMusic.dispose();
		highScore.dispose();
	}
	
	

}
