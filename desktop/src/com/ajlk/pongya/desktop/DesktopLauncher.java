package com.ajlk.pongya.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ajlk.pongya.PongYa;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Pongya";
		config.vSyncEnabled = true;
		config.width = 960;
		config.height = 640;
		
		new LwjglApplication(new PongYa(), config);
	}
}
