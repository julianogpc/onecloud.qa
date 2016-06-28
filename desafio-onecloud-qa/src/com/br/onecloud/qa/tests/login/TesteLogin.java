package com.br.onecloud.qa.tests.login;

import java.io.File;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.br.onecloud.qa.util.TestsUtil;
import com.br.onecloud.qa.util.AppInfo;

public class TesteLogin {
	protected WebDriver driver;
	protected File dir;
	protected static int navegador;
	protected static AppInfo user;

	@BeforeClass
	public static void beforeClass() {
		navegador = 1;
		TestsUtil.GetWebDriver();
		user = TestsUtil.GetAppInfo();
	}

	@Before
	public void setUp() {
		driver = TestsUtil.EscolherNavegador(navegador++);
		driver.manage().window().maximize();
		Calendar dt = Calendar.getInstance();
		String format = "yyyy-MM-dd HH mm ss";
		dir = new File("." + File.separator + "evidence" + File.separator + TestsUtil.FormatDate(dt, format));
	}

	@Test
	public void testeChrome() {
		testeLogin();
	}

	@Test
	public void testeFirefox() {
		testeLogin();
	}

	@Test
	public void testeInternetExplorer() {
		testeLogin();
	}

	public void testeLogin() {
		login();
	}

	@After
	public void tearDown() {
		TestsUtil.CloseWebDriver(driver);
	}

	public void login() {
		driver.get(user.getUrl());
		WebElement btnLogin = (new WebDriverWait(driver, 10)).until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#login-form > div.submit-row > input[type='submit']")));
		driver.findElement(By.cssSelector("#id_username")).sendKeys(user.getUser());
		driver.findElement(By.cssSelector("#id_password")).sendKeys(user.getPass());

		TestsUtil.TakeScreenshot(driver, dir, 500);
		btnLogin.click();
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
		TestsUtil.TakeScreenshot(driver, dir, 1000);
	}
}
