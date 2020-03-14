package browser;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLauncher {
	public WebDriver driver;

	@SuppressWarnings("deprecation")
	public WebDriver getDriver() {
		String driverName = "CHROME";
		if (driver == null) {
			switch (driverName) {
			case "CHROME":
				DesiredCapabilities caps = DesiredCapabilities.chrome();
		        LoggingPreferences logPrefs = new LoggingPreferences();
		        logPrefs.enable(LogType.BROWSER, Level.ALL);
		        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(caps);
				driver.manage().window().maximize();
				break;
			case "FIREFOX":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				break;
			default:
				throw new IllegalArgumentException(
						String.format("%s browser is not valid please provide valid browser name", driverName));
			}
		}
		return driver;
	}
}
