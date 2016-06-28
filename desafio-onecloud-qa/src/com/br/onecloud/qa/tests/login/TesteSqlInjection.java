package com.br.onecloud.qa.tests.login;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.br.onecloud.qa.util.TestsUtil;

public class TesteSqlInjection extends TesteLogin {
	public TesteSqlInjection() {
		super();
	}
	
	@BeforeClass
	public static void beforeClass() {
		navegador = 1;
		TestsUtil.GetWebDriver();
		user = TestsUtil.GetAppUrl();
		user.setUser("admin OR 1=1");
		user.setPass("admin");
	}
	
	@Override
	public void testeLogin(){
		login();
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/p")));
	}
}
