package rahulshettyacademy.pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		// initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(className=".ng-tns")
	WebElement spinner;
	// .ng-tns is pop up=Product added to Cart     -- css ""
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	public void addproductToCart(String productname) throws InterruptedException
	{
	WebElement prod=getProductByName(productname);
	prod.findElement(addToCart).click();
	waitForElementToAppear(toastMessage);
	waitForElementToDisappear(spinner);
	}
	public List<WebElement> getProductlist()
	{
		waitForElementToAppear(productsBy);
	  return products;	
	}
	public WebElement getProductByName(String productname)
	{
		WebElement prod=getProductlist().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);	
		return prod;
	}
	
	
}