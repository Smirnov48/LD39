package com.sgstudio.game.desktop;

//import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sgstudio.main.Main;
import com.sgstudio.settings.Save;
import com.sgstudio.game.Village;

public class DesktopLauncher {
	private static Save save;
	private static Village vil;
	public static void main (String[] arg) {
		vil = new Village();
		vil.setStartTime(System.currentTimeMillis());
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.addIcon("icons/logo.png", FileType.Internal);
		save = new Save();
		try{
			save.preferences("Settings");
			config.width = save.getInt("width");
			config.height = save.getInt("height");
		} catch(java.lang.NullPointerException e){
			config.width = 800;
			config.height = 600;
		}
		config.title = "Savior";
		new LwjglApplication(new Main(), config);
	}
}