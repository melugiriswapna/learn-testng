package learntestnh;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalHtmlTest {

	WebDriver driver;

	@BeforeMethod
	public void usershouldusetransiaction() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("file:///C:/Users/melug/OneDrive/Documents/techfios/html/SelectSample.html");
	}

	@Test
	public void userShouldBeAbleToAddDeposite() throws InterruptedException {
		System.out.println("inside the test case......");
	}
}

