package com.br.onecloud.qa.tests;

import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

import com.br.onecloud.qa.util.PropertiesUtil;
import com.br.onecloud.qa.util.AppInfo;

public class TesteAppInfo {
	@Test
	public void testeUserInformation(){
		AppInfo[] sessionsProp;

		sessionsProp = PropertiesUtil.GetAppProperties();

		assertNotEquals(sessionsProp.length, 0);

		for (AppInfo userinfo : sessionsProp) {
			System.out.println(userinfo);
		}
	}
}
