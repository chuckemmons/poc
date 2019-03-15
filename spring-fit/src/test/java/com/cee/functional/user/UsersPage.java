/**
 * 
 */
package com.cee.functional.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cee.functional.Page;

/**
 * @author chuck
 *
 */
public class UsersPage extends Page {
	private static final String URL = "/";
	private static final String PAGE_TITLE = "Users";
	
	public UsersPage(final WebDriver driver, final int port) {
		super(driver, port, URL, PAGE_TITLE);
	}
	
	public int getNumberOfDisplayedUsers() {
		return driver.findElements(By.className("user-row")).size();
	}
}
