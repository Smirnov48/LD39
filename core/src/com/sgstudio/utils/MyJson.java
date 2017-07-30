package com.sgstudio.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.utils.Json;
import com.sgstudio.settings.Settings;

public class MyJson {
	private Settings settings;
	private Json json;
	
	private String file;
	
	public MyJson(){
		settings = new Settings();
		json = new Json();
		
		String routePath = this.getClass().getClassLoader().getResource(File.separator).getPath();
		file = routePath+File.separator+".."+File.separator+"json"+File.separator+"settings.json";
	}
	
	public void setPlayingMusic(boolean playing){ settings.setPlayingMusic(playing); }
	
	public void setResolution(int width, int height){
		settings.setWidth(width);
		settings.setHeight(height);
	}
	
	@SuppressWarnings({ "resource" })
	public void writeToFile(){
		try
		{
			FileWriter writer = new FileWriter(file, false);
			System.out.println("--JSON-WRITE-BEGIN--\n"+json.prettyPrint(settings)+"\n--JSON---END---");
            writer.write(json.prettyPrint(settings));
            writer.flush();
        }
        catch(IOException ex){
        	ex.printStackTrace();
        }
	}
	
	@SuppressWarnings("resource")
	public void read(){
		String j = "";
		try
        {
			FileReader reader = new FileReader(file);
            int c;
            while((c=reader.read())!=-1){
                 j+=(char)c;
            } 
        }
        catch(IOException ex){
        	ex.printStackTrace();
        }
		
		System.out.println("--JSON-READ-BEGIN--\n"+j+"\n--JSON---END---");
		settings = json.fromJson(Settings.class, j);
	}
	
	public int getWidth(){ return settings.getWidth(); }
	public int getHeight(){ return settings.getHeight(); }
	public boolean getPlayingMusic(){ return settings.getPlayingMusic(); }
}
