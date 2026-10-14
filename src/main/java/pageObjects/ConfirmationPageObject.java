package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ResuableUtitiliy.AbstractComponent;

public class ConfirmationPageObject extends AbstractComponent{

WebDriver driver;
	
	public ConfirmationPageObject(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	String text=driver.findElement(By.cssSelector("[align='center'] h1")).getText();
//	System.out.println(text);
	
	@FindBy(css="[align='center'] h1")
	WebElement message;
	
	public String confirmationMsg() {
		return message.getText();
	}
}
