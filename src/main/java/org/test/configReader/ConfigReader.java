package org.test.configReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	static Properties p=null;
	
	public ConfigReader() throws IOException {
		p=new Properties(); // object creation
		File file = null;
 		file=new File(System.getProperty("user.dir")+"\\Resources\\"+"\\ConfigFiles\\"+"Env.properties");
	 	FileInputStream in=new FileInputStream(file); // reading the file
		p.load(in);
	}
	
	
	public static String getValueFromPropertyFile(String key) {
		if(p==null)
		{
			try {
				new ConfigReader(); // calling the constructor. creating the object of ConfigReader class
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String value=p.getProperty(key);
		return value;
	}
}


