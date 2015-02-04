package com.ajlk.pongya;


import com.ajlk.pongya.screens.Splash;
import com.badlogic.gdx.Game;



public class PongYa extends Game {

	@Override
	public void create() {

		setScreen(new Splash());
		
	}
	
	public void dispose(){
		super.dispose();
	}
	
	public void render(){
		super.render();
	}
	
	public void resize(int width,int height){
		super.resize(width, height);
	}
	
	public void pause(){
		super.pause();
	}
	
	public void resume(){
		super.resume();
	}
}
