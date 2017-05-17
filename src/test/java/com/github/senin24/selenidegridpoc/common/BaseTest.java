package com.github.senin24.selenidegridpoc.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

	static final Logger LOG = LogManager.getLogger(BaseTest.class.getName());

	@BeforeMethod()
	@Parameters({ "browser", "url" })
	public void setDriver(@Optional("chrome") String browser, @Optional("https://yandex.ru") String url) {
		Driver.setDriver(browser);
		LOG.info("Open url - " + url);
		open(url);
	}	

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		WebDriverRunner.getWebDriver().quit();
	}
}
