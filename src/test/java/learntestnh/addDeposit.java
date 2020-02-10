package learntestnh;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class addDeposit {

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
		// driver.findElement(By.xpath("//span[text()='Bank & Cash' ]")).click();
		// Thread.sleep(3000);
		String expectedTitle = "Dashboard- TechFios Test Application - Billing";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Dashboard Page did not display!");

		By Bank_Cash_Locator = By.xpath("//span[text()='Bank & Cash']");
		driver.findElement(Bank_Cash_Locator).click();
		By NEW_Accont_LOCATOR = By.linkText("New Account");
		driver.findElement(Bank_Cash_Locator).click();
		// waitForElement(NEW_Accont_LOCATOR, driver, 30);

		driver.findElement(NEW_Accont_LOCATOR).click();
		Thread.sleep(3000);

		String expectedAccount = "armoor" + new Random().nextInt(1000);
		System.out.println("expectedAccount ======= " + expectedAccount);

// Part 1: Begineer tester - home work
		driver.findElement(By.xpath("//input[@id='account']")).sendKeys(expectedAccount);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys("Deposit2");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='balance']")).sendKeys("200");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit' and @class='btn btn-primary']")).click();
		Thread.sleep(6000);
		
		// find the Account table
		WebElement table = driver.findElement(By.xpath("//div[@class='ibox-content']/table"));

		// find the row
		WebElement account = table.findElement(By.xpath("//tr/td[contains(text(), '"+expectedAccount+"')]"));
		
		System.out.println("new added acount name is "+account.getText());

		String delId = driver.findElement(By.xpath("//table[@class='table table-striped table-bordered']/tbody/tr/td[contains(text(), '"+expectedAccount+"' )]/following-sibling::*/a[2]")).getAttribute("id");
		
		System.out.println("my delet id is "+ delId);
		
		driver.findElement(By.xpath("//a[@id = '"+delId +"']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();
		// driver.findElement(By.xpath("//div[@class ='alert alert-success fade
		// in']")).click();
		// Thread.sleep(1000);
		

		/*
		 * By List_Accont_LOCATOR = By.linkText("List Accounts"); Thread.sleep(3000);
		 * driver.findElement(List_Accont_LOCATOR).click(); Thread.sleep(3000);
		 */

//scroll down

		
		/*
		 * List<WebElement> accountElementes = driver .findElements(By.
		 * xpath("//table[@class='table table-striped table-bordered']/tbody/tr"));
		 * System.out.println("desciptionElements.size=" + accountElementes.size());
		 * Thread.sleep(10000);
		 */
	}

	private boolean isDesctiptionMatch(String expectedAccount, List<WebElement> descriptionElements) {
		for (int i = 0; i < descriptionElements.size(); i++) {
			if (expectedAccount.equalsIgnoreCase(descriptionElements.get(i).getText())) {
				return true;
			}

		}

		return false;
	}

	private void waitForElement(By locator, WebDriver driver, int time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	@AfterMethod
	public void close() {
		 driver.close();
		 driver.quit();
	}

}

