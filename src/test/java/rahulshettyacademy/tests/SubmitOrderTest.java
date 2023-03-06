package rahulshettyacademy.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.$missing$;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productname = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
	
		ProductCatalogue productCatalogue =landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement>products=productCatalogue.getProductlist();
		productCatalogue.addproductToCart(input.get("product"));
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match =cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		
		String ConfMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(ConfMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
		// orders 	
	}
	//To verify ZARA COAT 3  is displaying in the orders page 
	@Test(dependsOnGroups= {"submitOrder"})
	public void OrderHistoryTest()
	{
		// "ZARA COAT 3";
	ProductCatalogue productCatalogue =landingpage.loginApplication("navneetsingh1708@gmail.com","Nfsvcx@1789");
	OrderPage orderpage=productCatalogue.goToOrderPage();
	Assert.assertTrue(orderpage.VerifyOrderDisplay(productname));	
	}
		// Extent Reports---	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String>map = new HashMap<String,String>();
//		map.put("email", "navneetsingh1708@gmail.com");
//		map.put("password", "Nfsvcx@17890");
//		map.put("product", "ZARA COAT 3");
//		HashMap<String,String>map1 = new HashMap<String,String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");	
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};	
	}	 
}