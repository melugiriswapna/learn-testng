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

public class MyFirstTestNG {

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

		String expectedAccount = "armoor" + new Random().nextInt(100);
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

		// 9q. Validate the presence of "Account Created Successfully" message
		String message = driver.findElement(By.xpath("//div[@class='alert alert-success fade in']")).getText();

		String expectedMessage = "Account Created Successfully";

		if (message.contains(expectedMessage)) {
			message = expectedMessage;
		}

		System.out.println("message --------------- " + message);

		Assert.assertEquals(message, expectedMessage);
		/*
		 * new WebDriverWait(driver,
		 * 60).until(ExpectedConditions.invisibilityOfElementLocated(By.
		 * xpath("//*[contains(@class, '')]")));
		 * waitForElement(By.linkText(expectedAccount), driver, 60);
		 */ // Part 2: Intermediate tester - home work

		By List_Accont_LOCATOR = By.linkText("List Accounts");
		Thread.sleep(3000);
		driver.findElement(List_Accont_LOCATOR).click();
		Thread.sleep(3000);

		// 11.Validate new account showed up in the bottom of the table

		// Part 3: Advance tester - home work
		List<WebElement> accountElementes = driver
				.findElements(By.xpath("//table[@class='table table-striped table-bordered']/tbody/tr"));
		System.out.println("desciptionElements.size=" + accountElementes.size());
		// System.out.println(accountElementes.get(0).getText());

		// string variable initialization
		String actualAccount = "";
		String deleteButtonId = "";
		// for(int i = 0; i<accountElementes.size(); i++) {
		boolean outerloopBreak = false;
		for (WebElement trElement : accountElementes) {
			// System.out.println(accountElementes.get(i).getText());
			List<WebElement> tdElements = trElement.findElements(By.xpath("td"));
			for (WebElement tdElement : tdElements) {
				
				if (expectedAccount.equalsIgnoreCase(tdElement.getText())) {
					System.out.println("&&&&&&&&&&&&&&&&&&&&  found the account");
					actualAccount = tdElement.getText();
					outerloopBreak = true;
					// break;
				}

				if (outerloopBreak && tdElement.getText().contains("Delete") ||tdElement.getText().contains("delete")) {
					//System.out.println("Inside the delete condition................");
					List<WebElement> ahrefList = tdElement.findElements(By.xpath("a"));

					//System.out.println("ahrefList.size() ... "+ahrefList.size());
					
					for (WebElement ahrefElement : ahrefList) {
						//System.out.println("ahrefElement.getText()====================="+ahrefElement.getText());
						if (ahrefElement.getText().contains("Delete") || ahrefElement.getText().contains("delete")) {
							System.out.println("inside.... %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
							deleteButtonId = ahrefElement.getAttribute("id");
							break;
						}
						//System.out.println("..................... deleteButtonId==" + deleteButtonId);
						
					
					}
					break;
				}

			}

			if (outerloopBreak) {
				break;
			}
		}
		System.out.println("..................... deleteButtonId==" + deleteButtonId);
		driver.findElement(By.xpath("//a[@id = '"+deleteButtonId +"']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();
		  // driver.switchTo().alert().accept();
		// deiete added account
System.out.println("expectedAccount********="+expectedAccount);
System.out.println(" actualAccount########="+ actualAccount);
		Assert.assertEquals(expectedAccount, actualAccount);

		Thread.sleep(3000);
	}

	private boolean isDescriptionMatch(String expectedDescription, List<WebElement> descriptionElements) {
		for (int i = 0; i < descriptionElements.size(); i++) {
			if (expectedDescription.equalsIgnoreCase(descriptionElements.get(i).getText())) {
				return true;
			}
		}
		return false;

	}

	@AfterMethod
	public void close() {
		// driver.close();
		// driver.quit();
	}

}
