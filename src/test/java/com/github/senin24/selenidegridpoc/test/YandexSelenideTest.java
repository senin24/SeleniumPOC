package com.github.senin24.selenidegridpoc.test;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Condition.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codeborne.selenide.SelenideElement;
import com.github.senin24.selenidegridpoc.common.BaseTest;
import com.github.senin24.selenidegridpoc.common.TestData;
import com.github.senin24.selenidegridpoc.common.Utils;

public class YandexSelenideTest extends BaseTest {

	static final Logger LOG = LogManager.getLogger(YandexSelenideTest.class.getName());

	String input = "//*[@id='text']",
			next = "//div[@role='navigation']//a[text()='дальше']",
			allLink = "//li[@data-cid]//h2/a";
	
	@DataProvider (parallel=true)
	public Object [] [] getText () {
		return TestData.getData();
	}

	@Test (dataProvider="getText")
	public void checkSearchYandexTest(String text) throws IOException, InterruptedException {
		List<String> www = new ArrayList<>();

		$(By.xpath(input)).setValue(text).pressEnter();
		//TODO add check if next btn not exist in first page
		$(By.xpath(next)).should(visible);

		for (int i = 1; i < 3; i++) {
			List<SelenideElement> allwww = $$(By.xpath(allLink));
			LOG.info(String.format("Page number %s, find %s elements for '%s'", i, allwww.size(), text));
			screenshot("text_page_" + i);
			for (SelenideElement e : allwww) {
				e.shouldBe(visible);
				www.add(e.getAttribute("href"));
				LOG.debug(String.format("Page number %s, attribute 'href' of element - %s", i, e.getAttribute("href")));
			}
			try {
				$(By.xpath(next)).should(visible);
			} catch (Exception e1) {
				if (!$(By.xpath(next)).isDisplayed()) {
					LOG.info("Search results finished in page number " + i);
					break;
				}
				e1.printStackTrace();
			}
			$(By.xpath(next)).click();
		}

		new Utils().saveToFile(text, www);
	}

}
