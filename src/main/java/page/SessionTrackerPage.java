package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import browser.BrowserLauncher;

public class SessionTrackerPage extends BasePage {
	
	@FindBy(id = "username")
	protected WebElement userNameInput;
	
	@FindBy(id = "add-entry")
	WebElement addEntryButton;
	
	@FindBy(id = "fetch-entry")
	WebElement fetchEntryButton;
	
	@FindBy(css = ".name-added")
	WebElement addEntryMessage;
	
	@FindBy(css = ".name-query")
	WebElement queryMessage;

	public SessionTrackerPage(BrowserLauncher launcher) {
		super(launcher);
	}
	
	public void launchBrowser() {
		driver.get("https://session-tracker-ad13f.firebaseapp.com/");
		driver.manage().window().maximize();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public SessionTrackerPage enterUserName(String name) {
		userNameInput.clear();
		userNameInput.sendKeys(name);
		return this;
	}
	
	public SessionTrackerPage clickAddEntryButton() {
		addEntryButton.click();
		/*LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println("****** Log Messages : "+new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }*/
		return this;
	}
	
	public SessionTrackerPage clickFetchEntryButton() {
		fetchEntryButton.click();
		return this;
	}
	
	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		String alertText =  alert.getText();
		alert.accept();
		return alertText;
	}
	
	public String getAddEntryMessage() {
		return addEntryMessage.getText();
	}
	
	public String getSessionMessage() {
		return queryMessage.getText();
	}
	
	public String getUserInputValue() {
		return userNameInput.getAttribute("value");
	}

}
