package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ResuableUtitiliy.AbstractComponent;

public class CheckOutObject extends AbstractComponent{
	WebDriver driver;
	public CheckOutObject(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountry;
	By bytext=By.xpath("//span[text()=' India']");
	
	@FindBy(xpath="//span[text()=' India']")
	WebElement Country;
	
	@FindBy(css=".actions a")
	WebElement placeorder;
	
	public void selectCountry(String country) {
		selectCountry.sendKeys(country);
		elementClickable(bytext);
		Country.click();
	}
	
	public ConfirmationPageObject gotoConformationPage() {
		placeorder.click();
		ConfirmationPageObject cp=	new ConfirmationPageObject(driver);
		return cp;
	}
	
	
	
}
