package com.sgstudio.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sgstudio.main.Main;
import com.sgstudio.utils.MyJson;

public class DesktopLauncher {
	private static MyJson json;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.addIcon("icons/logo.png", FileType.Internal);
		json = new MyJson();
		json.read();
		config.width = json.getWidth();
		config.height = json.getHeight();
		config.resizable = false;
		config.title = "FireMan";
		new LwjglApplication(new Main(), config);
	}
	
	public MyJson getJson(){ return json; }
}