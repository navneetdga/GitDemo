package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(css="[routerlink*='cart']")
	WebElement CartHeader;
	//----------------------------	[routerlink*='myorders']---------------------
	@FindBy(css="[routerlink*='myorders']")
	WebElement OrderHeader;

	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public CartPage goToCartPage()
	{
		CartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	public OrderPage goToOrderPage()
	{
		OrderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException 
	{
	
		
				Thread.sleep(1000);
			
		//4 seconds - Application--------------- REMOVED--------------------
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}

} 
