package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.Abstractcomponents.Abstractcomponents;

public class ProductCatalogue extends Abstractcomponents {

		
		   WebDriver driver;
		   
		   public ProductCatalogue(WebDriver driver)
		   {
			   super(driver);
			   this.driver = driver;
			   PageFactory.initElements(driver, this);
		   }
		   
		  // List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		   
		   @FindBy(css=".mb-3")
		   List<WebElement> products;
		   
		   @FindBy(css=".ng-animating")
		   WebElement spinner;
		   
		   By productsBy = By.cssSelector(".mb-3");
		   By addTocart = By.cssSelector(".card-body button:last-of-type");
		   By toastMessage=By.cssSelector("#toast-container");
		   
		   public List<WebElement> getproductList()
		   {
			   waitForElementToappear(productsBy);
			   return products;
		   }
		   
		   public WebElement getproductsByname(String productName)
		   {
			   WebElement prod = getproductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
				return prod;
		   }
		   
		   public void addProductTocart(String productName)
		   {
			   WebElement prod = getproductsByname(productName);
			   prod.findElement(addTocart).click();
			   waitForElementToappear(toastMessage);
			   waitForElementToDisappear(spinner);
			   
		   }
		   
		   
	}


