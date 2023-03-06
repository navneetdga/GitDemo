package rahulshettyacademy.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException ,InterruptedException
	{

		landingpage.loginApplication("navneetsingh1708@gmail.com","Nfsvcx@17890");
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());
	} 
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{	
		String productname = "ZARA COAT 3";
		ProductCatalogue productCatalogue =landingpage.loginApplication("navneetsingh1708@gmail.com","Nfsvcx@17890");
	
		List<WebElement>products=productCatalogue.getProductlist();
		productCatalogue.addproductToCart(productname);
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match =cartpage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);		
	}  	
}