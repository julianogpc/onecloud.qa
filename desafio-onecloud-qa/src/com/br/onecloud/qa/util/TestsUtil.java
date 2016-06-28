package com.br.onecloud.qa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestsUtil {
	public static WebDriver EscolherNavegador(int navegador) {
		WebDriver driver;
		DesiredCapabilities capabilities;
		switch (navegador) {
		case 1:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			capabilities = DesiredCapabilities.chrome();
			capabilities.setBrowserName("chrome");
			capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			return driver;
		case 2:
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setBrowserName("iexplore");
			capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("nativeEvents", false);
			driver = new InternetExplorerDriver(capabilities);
			return driver;
		default:
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new MarionetteDriver(capabilities);
			return driver;
		}
	}

	public static void GetWebDriver() {
		String[] drivers = PropertiesUtil.GetDriverProperties();
		System.setProperty("webdriver.chrome.driver", drivers[0]);
		System.setProperty("webdriver.ie.driver", drivers[1]);
		System.setProperty("webdriver.gecko.driver", drivers[2]);

	}

	public static void CloseWebDriver(WebDriver driver) {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();
		String command = "cmd /c TASKKILL /IM IEDriverServer.exe /F || TASKKILL /IM chromedriver.exe /F || TASKKILL /IM wires.exe /F";
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro de entrada e saída!");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Erro ao encerrar processos!");
			e.printStackTrace();
		}
	}

	public static void Sleep(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void TakeScreenshot(WebDriver driver, File dir, long sleep) {
		Sleep(sleep);
		TakeScreenshot(driver, dir);
	}

	public static void TakeScreenshot(WebDriver driver, File dir) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File fileName;
		try {
			fileName = GetFileName(dir);
			FileUtils.copyFile(scrFile, fileName);
		} catch (Exception e) {
			System.out.println("Erro ao gravar arquivo de evidência!");
			e.printStackTrace();
		}

	}

	public static File GetFileName(File dir) throws Exception {
		Calendar dt = Calendar.getInstance();
		String fileName = FormatDate(dt, "yyyy-MM-dd HH mm ss.SSS") + ".jpg";
		File file = new File(dir.getCanonicalPath() + File.separator + fileName);
		return file;
	}

	public static String FormatDate(Calendar dt, String format) {
		SimpleDateFormat formatStr = new SimpleDateFormat(format);
		return formatStr.format(dt.getTime());
	}

	public static AppInfo GetAppInfo() {
		return PropertiesUtil.GetAppProperties()[0];
	}

	public static AppInfo GetAppUrl() {
		return PropertiesUtil.GetAppUrl();
	}

	public static AppInfo[] GetUsers() {
		return PropertiesUtil.GetAppProperties();
	}

}
