package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	
	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException
	{
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.logInApplicatio("reddy1256@gmail.com", "Reddy1@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		//uytreasdtjfyjguh
	
	
	}
	
	@Test
	public void ProductErrorValidation() throws IOException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.logInApplicatio("reddy78@gmail.com", "Reddy@123");
		List<WebElement> products=productCatalogue.getproductList();
		productCatalogue.addProductTocart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(false);
	
	
	}

}
