package mods.learncraft.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Settings {

	final static String fileName = "settings.conf";
	public Map<String, String> settings = new HashMap<String, String>();
	
	public Settings() {
		InputStream i = Settings.class.getResourceAsStream(fileName);
		ArrayList<String> lines = new ArrayList<String>();
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(i));
			String line = "";
			while((line = in.readLine()) != null) {
				String[] result = line.split(":",2);
				this.addSetting(result);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getSetting(String settingName) {
		return settings.get(settingName);
	}
	
	public void addSetting(String[] setting) {
		if(setting.length == 2) {
			settings.put(setting[0],setting[1]);
		}
	}
	
	public void addSetting(String settingName, String settingValue) {
		settings.put(settingName, settingValue);
	}
	
}
