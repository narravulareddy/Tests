package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class StandAloneTest extends BaseTest{

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData" , groups = "purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException
	{
		
		
		ProductCatalogue productCatalogue = landingPage.logInApplicatio(input.get("email"), input.get("password"));
		
		List<WebElement> products=productCatalogue.getproductList();
		productCatalogue.addProductTocart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(true);
		CheckOutPage checkOutPage=cartPage.goTocheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
	
		String confirmMessage= confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
	}	
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.logInApplicatio("reddy12@gmail.com", "Reddy@123");
		OrderPage orderPage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{   
		
		List<HashMap<String, String>> data = 
			    getJsonDtaToMap(System.getProperty("user.dir") 
			    + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");


		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	

}
