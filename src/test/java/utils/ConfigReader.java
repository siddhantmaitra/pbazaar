package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	public static Properties prop;

	public static Properties property() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream("src/test/resources/config.properties");
			try {

				prop.load(input);

			} catch (IOException e) {

				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}
		return prop;
	}

}