package com.sgstudio.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sgstudio.main.Main;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.addIcon("icons/logo.png", FileType.Internal);
		config.width = 800;
		config.height = 600;
		config.resizable = false;
		config.title = "FireMan";
		new LwjglApplication(new Main(), config);
	}
}