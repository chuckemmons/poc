/**
 * 
 */
package com.cee.functional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author chuck
 *
 */
public abstract class Page {
	protected static final long WAIT_SECONDS = 15L;
	protected static final String URL_FORMAT = "http://localhost:%d%s";
	protected static final String URL_FORMAT_NO_PORT = "http://localhost%s";
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected int port;
	protected String url;
	protected String title;

	protected Page(final WebDriver driver, final int port, final String pageUrl, final String pageTitle) {
		this.driver = driver;
		this.port = port;
		this.title = pageTitle;
		this.url = getUrl(port, pageUrl);
		this.wait = new WebDriverWait(driver, WAIT_SECONDS);
		init();
	}

	private void init() {
		if (StringUtils.isBlank(driver.getTitle())) {
			driver.get(url);
		}
		wait.until(ExpectedConditions.titleContains(title));

		Validate.validState(driver.getCurrentUrl().equals(url), "Not at the url: %s", url);
		Validate.validState(isDisplayed(), "Page Title is not %s", title);
	}
	
	private String getUrl(final int port, final String pageUrl) {		
		switch(port) {
			case 0 : return String.format(URL_FORMAT_NO_PORT, pageUrl);
			default : return String.format(URL_FORMAT, port, pageUrl);
		}
	}

	public String getUrl() {
		return url;
	}

	public boolean isDisplayed() {
		return title.equals(driver.getTitle());
	}

	public boolean isAtUrl() {
		return driver.getCurrentUrl().equals(url);
	}
}
