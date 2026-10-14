package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ResuableUtitiliy.AbstractComponent;

public class ProductPage extends AbstractComponent{
	WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> prodlist= driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> prodlist;
	
	@FindBy(css="[class*='ng-animating']")
	WebElement snipper;
	
	By successmsg = By.id("toast-container");
	
	By prods=By.cssSelector("b");
	By addTocart=By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProdList() {
		visiblilityofelement(prodlist);
		return prodlist;
	}
	
	public WebElement getProdName(String prodname) {
		WebElement prod=prodlist.stream().filter(s->s.findElement(prods).getText().
				equalsIgnoreCase(prodname)).findFirst().orElse(null);
		return prod;
	}
	
	public void addTocart(String prodname) throws InterruptedException {
		WebElement prod=getProdName(prodname);
		prod.findElement(addTocart).click();
		//Thread.sleep(5000);
		visibilityOflocator(successmsg);
		invisiblilityelement(snipper);	
	}
	
}

