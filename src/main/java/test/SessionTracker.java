package test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import browser.BrowserLauncher;
import page.SessionTrackerPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class SessionTracker {
	
	WebDriver driver;
	protected BrowserLauncher launcher = new BrowserLauncher();
	SessionTrackerPage sessionTrackerPage = new SessionTrackerPage(launcher);
	
	@BeforeClass
	public void setUp() {
		sessionTrackerPage.launchBrowser();
	}
	
	@Test(description = "Verify Page title")
	public void test_01() {
		String title = sessionTrackerPage.getPageTitle();
		assertEquals(title, "Session Tracker");
	}
	
	@Test(description = "Verify alert message on click of Save when user does not enters username.")
	public void test_02() {
		String alertText = sessionTrackerPage.clickAddEntryButton().getAlertText();
		assertEquals(alertText, "Username must not be empty");
	}
	
	@Test(description = "Verify alert message on click of See Last Session when user does not enters username.")
	public void test_03() {
		String alertText = sessionTrackerPage.clickFetchEntryButton().getAlertText();
		assertEquals(alertText, "Username must not be empty");
	}
	
	@Test(description = "Verify the message when user enters username and clicks Save.")
	public void test_04() {
		String name = "Ankita";
		String expectedMessage = String.format("Last Active Timestamp For %s Added Successfully", name);
		String message = sessionTrackerPage.enterUserName(name).clickAddEntryButton().getAddEntryMessage();
		String inputTextValue = sessionTrackerPage.getUserInputValue();
		assertEquals(message, expectedMessage);
		assertEquals(inputTextValue, name);
		
	}
	
	@Test(description = "Verify the message on click of See Last Session.")
	public void test_05() {
		String name = "Ankita";
		String sessionMessage = sessionTrackerPage.clickFetchEntryButton().getSessionMessage();
		assertThat(sessionMessage, containsString(name));
	}
	
	
	@AfterClass
	public void tearDown() {
		sessionTrackerPage.closeBrowser();
	}
}
