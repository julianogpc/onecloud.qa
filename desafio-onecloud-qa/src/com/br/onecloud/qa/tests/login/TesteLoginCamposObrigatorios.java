package com.br.onecloud.qa.tests.login;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.br.onecloud.qa.util.TestsUtil;

public class TesteLoginCamposObrigatorios extends TesteLogin {
	public TesteLoginCamposObrigatorios() {
		super();
	}
	
	@BeforeClass
	public static void beforeClass() {
		navegador = 1;
		TestsUtil.GetWebDriver();
		user = TestsUtil.GetAppUrl();
	}
	
	@Override
	public void testeLogin(){
		login();
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='login-form']/div[1]/ul/li")));
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='login-form']/div[2]/ul/li")));
	}
}
