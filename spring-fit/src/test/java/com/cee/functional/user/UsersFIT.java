/**
 * 
 */
package com.cee.functional.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cee.functional.FitTest;

/**
 * @author chuck
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersFIT extends FitTest {

	private static UsersPage page;
	
	@BeforeClass
	public static void beforeClass() {
		page = new UsersPage(getDriver(), port);
	}
	
	@Test
	public void testNumberOfUsers() {
		assertThat(page.getNumberOfDisplayedUsers())
			.isEqualTo(UserData.getAllEntities());
	}
	
	@AfterClass
	public static void afterClass() {
		quitWebDriver();
	}
}
