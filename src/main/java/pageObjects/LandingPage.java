package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ResuableUtitiliy.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("[type='email']")).sendKeys("poojasalla14@gmail.com");
	@FindBy(css="[type='email']")
	WebElement userEmail;
	
	@FindBy(css="[type='password']")
	WebElement password;
	
	@FindBy(id="login")
	WebElement signin;
	
	@FindBy(css="[class*='ngx-toastr']")
	WebElement invalidlogin;
	
	public ProductPage login(String email,String pwd) {
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		signin.click();
		ProductPage pp=new ProductPage(driver);
		return pp;
	}
	public void gotoPage() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public String invalidlogin() {
		visibilityOfWebElementLocated(invalidlogin);
		return invalidlogin.getText();
	}
}

