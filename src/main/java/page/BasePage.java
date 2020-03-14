package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import browser.BrowserLauncher;


public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	public BasePage(BrowserLauncher launcher) {
		this.driver = launcher.getDriver();
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}
}
