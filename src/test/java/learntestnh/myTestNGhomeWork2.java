package learntestnh;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class myTestNGhomeWork2 {

	WebDriver driver;

	@BeforeMethod
	public void usershouldusetransiaction() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://techfios.com/test/billing/?ng=admin/");
	}

	@Test
	public void userShouldBeAbleToAddDeposite() throws InterruptedException {
		driver.findElement(By.xpath("//*[@type='text']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//*[contains(@placeholder, 'assword') and contains(@class, 'form-')]"))
				.sendKeys("abc123");
		driver.findElement(By.xpath("//*[contains(text(), 'Sign') and @name='login']")).click();
		Thread.sleep(3000);

		String expectedTitle = "Dashboard- TechFios Test Application - Billing";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Dashboard Page did not display!");

		By Bank_Cash_Locator = By.xpath("//span[text()='Bank & Cash']");
		// driver.findElement(Bank_Cash_Locator).click();
		By New_Account_Locator = By.linkText("New Account");

		driver.findElement(Bank_Cash_Locator).click();
		waitForElement(New_Account_Locator, driver, 20);

		By List_Accont_LOCATOR = By.linkText("List Accounts");
		Thread.sleep(3000);
		driver.findElement(List_Accont_LOCATOR).click();
		Thread.sleep(3000);

		// List<WebElement> = driver.findElement(By.xpath("//table/descendant::a"));
		// System.out.println(descriptionElements.get(0).getText());

		String expectesAccount = "saving123"; // "AutomationTest" + new Random().nextInt(999);
		System.out.println("Expected:" + expectesAccount);

		List<WebElement> accountElementes = driver
				.findElements(By.xpath("//table[@class='table table-striped table-bordered']/tbody/tr"));
		System.out.println("desciptionElements.size=" + accountElementes.size());
		// System.out.println(accountElementes.get(0).getText());
		String actualAccount = "";
		// for(int i = 0; i<accountElementes.size(); i++) {
		boolean outerloopBreak = false;
		for (WebElement trElement : accountElementes) {
			// System.out.println(accountElementes.get(i).getText());
			List<WebElement> tdElements = trElement.findElements(By.xpath("td"));

			// System.out.println("*********** tdElements === "+tdElements);
			// System.out.println("*********** tdElements.size() === "+tdElements.size());
			// actualAccount = (tdElements.get(1)).getText();
			for (WebElement tdElement : tdElements) {
				actualAccount = tdElement.getText();
				if (expectesAccount.equalsIgnoreCase(actualAccount)) {
					System.out.println("found the account");
					outerloopBreak = true;
					break;
				}
			}

			if (outerloopBreak) {
				break;
			}
		}
	}

	private void waitForElement(By locator, WebDriver driver, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	@AfterMethod
	public void close() {
		// driver.close();
		// driver.quit();
	}
	

}
