package com.br.onecloud.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class PropertiesUtil {
	private static String USER_PROPERTIES = "properties/BR_user_connection.properties";
	private static String DRIVER_PROPERTIES = "properties/BR_driver.properties";

	private static final String KEY = "user";

	public static AppInfo[] GetAppProperties() {
		ArrayList<AppInfo> array = new ArrayList<AppInfo>();
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(USER_PROPERTIES));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo de propriedades!"); 
			e.printStackTrace();
		}
		int i = 1;
		while (properties.containsKey(KEY + i + ".host")) {
			String host = properties.getProperty(KEY + i + ".host");
			String port = properties.getProperty(KEY + i + ".port");
			String user = properties.getProperty(KEY + i + ".user");
			String pass = properties.getProperty(KEY + i + ".pass");
			String path = properties.getProperty(KEY + i + ".path");

			AppInfo appInfo = new AppInfo(host, port, user, pass, path);
			array.add(appInfo);
			i++;
		}
		return array.toArray(new AppInfo[array.size()]);
	}
	
	public static AppInfo GetAppUrl() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(USER_PROPERTIES));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo de propriedades!"); 
			e.printStackTrace();
		}
		String host = properties.getProperty(KEY + 1 + ".host");
		String port = properties.getProperty(KEY + 1 + ".port");
		String path = properties.getProperty(KEY + 1 + ".path");

		AppInfo appInfo = new AppInfo(host, port, "", "", path);

		return appInfo;
	}
	
	public static String[] GetDriverProperties() {
		String array[] = new String [3];
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(DRIVER_PROPERTIES));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo de propriedades!"); 
			e.printStackTrace();
		}
		array[0] = properties.getProperty("driver.chrome");
		array[1] = properties.getProperty("driver.ie");
		array[2] = properties.getProperty("driver.firefox");
		return array;
	}
}
