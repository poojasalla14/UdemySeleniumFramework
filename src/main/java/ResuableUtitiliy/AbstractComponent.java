package ResuableUtitiliy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartObjects;
import pageObjects.OrderObjects;

public class AbstractComponent {
	
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;	
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public CartObjects getClickonCart() throws InterruptedException {
		//Thread.sleep(1000);
		cartHeader.click();
		CartObjects cc=new CartObjects(driver);
		return cc;
	}
	
	public OrderObjects gotoOrderPage() {
		orderHeader.click();
		OrderObjects orderObject=new OrderObjects(driver);
		return orderObject;
	}
	public void visiblilityofelement(List<WebElement> ele) {
		
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	
	}	
	public void invisiblilityelement(WebElement ele) {
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void visibilityOflocator(By locator) {
	   // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' India']")));
	public void elementClickable(By findBy) {
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	public void visibilityOfWebElementLocated(WebElement ele) {
	    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
}
