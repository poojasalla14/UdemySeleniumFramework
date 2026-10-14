package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ResuableUtitiliy.AbstractComponent;

public class CartObjects extends AbstractComponent{
	WebDriver driver;
	public CartObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='cart'] ul")
	List<WebElement> cartList;
	@FindBy(css="[routerlink*='cart'] label")
	WebElement cartlabel;
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public Boolean getCartDetails(String prodname) {
		return cartList.stream().anyMatch(s->s.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(prodname)); 
	}
	
	public int cartCount() {
		int cartCount=Integer.parseInt(cartlabel.getText());
		return cartCount;
	}
	
	public CheckOutObject checkout() {
		checkout.click();
		CheckOutObject checkout=new CheckOutObject(driver);
		return checkout;
	}

}
