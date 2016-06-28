package com.br.onecloud.qa.tests.login;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.br.onecloud.qa.util.TestsUtil;
import com.br.onecloud.qa.util.AppInfo;

public class TesteLoginInvalido extends TesteLogin {
	public TesteLoginInvalido() {
		super();
	}
	
	@BeforeClass
	public static void beforeClass() {
		navegador = 1;
		TestsUtil.GetWebDriver();
		AppInfo[] users = TestsUtil.GetUsers();
		
		if (users.length > 1)
			user = users[1];
		else
			Assert.fail("Falta preencher mais de usuário no arquivo de propriedades.");
	}
	
	@Override
	public void testeLogin(){
		login();
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/p")));
	}
}
