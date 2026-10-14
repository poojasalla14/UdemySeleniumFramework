package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ResuableUtitiliy.AbstractComponent;

public class OrderObjects extends AbstractComponent{
	WebDriver driver;
	public OrderObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderList;
	
	
	public Boolean verifyProductinorderList(String prodname) {
		return orderList.stream().anyMatch(s->s.getText().equalsIgnoreCase(prodname)); 
	}
	
	
	
	
}
