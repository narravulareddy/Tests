package rahulshettyacademy.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.Abstractcomponents.Abstractcomponents;

public class CheckOutPage extends Abstractcomponents{
	
	WebDriver driver;
	   
	   public CheckOutPage(WebDriver driver)
	   {
		   super(driver);
		   this.driver = driver;
		   this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		   PageFactory.initElements(driver, this);
	   }
	   
	   WebDriverWait wait;
	   
	   @FindBy(css = "[placeholder='Select Country']")
	   WebElement country;
	   
	   @FindBy(css = ".ta-item:nth-of-type(2)")
	   WebElement SelectCountry;
	   
	   By results = By.cssSelector(".ta-results");
	  
	   
	   public void selectCountry(String countryName)
	   {
		   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		   Actions a = new Actions(driver);
			a.sendKeys(country, countryName).build().perform();
			waitForElementToappear(results);
			WebElement countryOption = wait.until(
			        ExpectedConditions.elementToBeClickable(By.cssSelector(".ta-item:nth-of-type(2)"))
			);
			countryOption.click();
	   }
	   
	   public ConfirmationPage submitOrder()
	   {
		   WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
		   return new ConfirmationPage(driver);
	   }
	   
	   

}
