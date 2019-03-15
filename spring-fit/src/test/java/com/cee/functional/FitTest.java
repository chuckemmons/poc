/**
 * 
 */
package com.cee.functional;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * @author chuck
 *
 */
public class FitTest {
	private static final Logger LOG = LoggerFactory.getLogger(FitTest.class);
	
	private static final long MAX_WAIT_SECONDS = 15L;

	private static WebDriver driver;

	@LocalServerPort
	protected static int port;

	// @BeforeClass // FIXME: beforeClass isn't getting executed for some reason....
	public static void initDriver() {
		LOG.debug("initializing the WebDriver");
		// WebDriverManager.chromedriver().version(VERSION_WEBDRIVER_CHROME).setup();
		driver = new ChromeDriver(new ChromeOptions().addArguments("--no-sandbox"));
		driver.manage().timeouts().implicitlyWait(MAX_WAIT_SECONDS, TimeUnit.SECONDS);
	}

	// @AfterClass// FIXME: afterclass isn't getting executed....
	public static void quitWebDriver() {
		if (null != driver) {
			LOG.debug("closing webDriver");
			driver.close();
			driver.quit();
		}
	}

	protected static WebDriver getDriver() {
		if (null == driver) {
			initDriver();
		}
		return driver;
	}
}
