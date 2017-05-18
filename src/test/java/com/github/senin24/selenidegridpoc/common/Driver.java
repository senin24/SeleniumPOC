package com.github.senin24.selenidegridpoc.common;

import java.net.URL;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class Driver {
	private static final String FIREFOX = "firefox";
	private static final String CHROME = "chrome";
	private static final String IE = "ie";

	private static String seleniumGridHubHostname = Config.getInstance().getGridHubHostName();
	private static final boolean IS_USE_SELENIUM_GRID = Config.getInstance().isGridUsed();
	private static final String WINDOWS_CHROME_DRIVER_PATH = "src/test/resources/drivers/chromedriver.exe";
	private static final String WINDOWS_IE_DRIVER_PATH = "src/test/resources/drivers/IEDriverServer.exe";

	static final Logger LOG = LogManager.getLogger(Driver.class.getName());

	static ThreadLocal<WebDriver> CURRENT_DRIVER = new ThreadLocal<>();

	public static void setDriver(String browser) {
		Configuration.timeout = 8000;
		Configuration.startMaximized = true;
		if (IS_USE_SELENIUM_GRID) {
			LOG.info("Selenium grid is ON");
			setUpGrid(browser);
		} else {
			LOG.info("Selenium grid is OFF");
			setUp(browser);
		}
		WebDriverRunner.getWebDriver().manage().window().maximize();
	}

	private static void setUp(String browser) {
		DesiredCapabilities dc = new DesiredCapabilities();
		switch (browser) {
		case FIREFOX:
			FirefoxProfile fp = new FirefoxProfile();
			dc.setCapability(FirefoxDriver.PROFILE, fp);
			dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			WebDriverRunner.setWebDriver(new RemoteWebDriver(dc));
			break;

		case IE:
			System.setProperty("webdriver.ie.driver", WINDOWS_IE_DRIVER_PATH);
			dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
			WebDriverRunner.setWebDriver(new InternetExplorerDriver(dc));

		case CHROME:
		default:
			System.setProperty("webdriver.chrome.driver", WINDOWS_CHROME_DRIVER_PATH);
			WebDriverRunner.setWebDriver(new ChromeDriver());
		}
	}

	public static void setUpGrid(String browser) {
		DesiredCapabilities dc = new DesiredCapabilities();
		switch (browser) {
		case FIREFOX:
			FirefoxProfile fp = new FirefoxProfile();
			dc.setCapability(FirefoxDriver.PROFILE, fp);
			dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
			// FOR VNC - now work in only FF 46.0
			dc.setVersion("46.0");
			dc.setCapability("enableVNC", true);
			break;

		case IE:
			dc = DesiredCapabilities.internetExplorer();
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);

		case CHROME:
		default:
			dc = DesiredCapabilities.chrome();
			dc.setBrowserName("chrome");
			dc.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--disable-save-password-bubble");
			dc.setCapability(ChromeOptions.CAPABILITY, options);
		}
		dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		dc.setJavascriptEnabled(true);
		dc.setPlatform(Platform.LINUX);
		try {
			String url = "http://" + seleniumGridHubHostname + ":4444/wd/hub";
			LOG.debug("URL = " + url);
			WebDriverRunner.setWebDriver(new RemoteWebDriver(new URL(url), dc));
		} catch (Exception e) {
			throw new RuntimeException("HUB is not running on server '" + seleniumGridHubHostname + "'");
		}
	}

}
