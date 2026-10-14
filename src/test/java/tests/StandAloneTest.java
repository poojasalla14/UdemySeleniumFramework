package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String prodname="ADIDAS ORIGINAL";
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.cssSelector("[type='email']")).sendKeys("poojasalla14@gmail.com");
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("Pooja@1234");
		driver.findElement(By.id("login")).click();
		//add the product to list
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List<WebElement> prodlist= driver.findElements(By.cssSelector(".mb-3"));
		wait.until(ExpectedConditions.visibilityOfAllElements(prodlist));
		WebElement prod=prodlist.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(prodname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[class*='ng-animating']"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[routerlink*='cart']"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartList=driver.findElements(By.cssSelector("[class='cart'] ul"));
		int cartcount=Integer.parseInt(driver.findElement(By.cssSelector("[routerlink*='cart'] label")).getText());
		Assert.assertEquals(cartcount, cartList.size());
		Boolean flag=cartList.stream().anyMatch(s->s.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(prodname));
		Assert.assertTrue(flag);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' India']")));
		driver.findElement(By.xpath("//span[text()=' India']")).click();
		driver.findElement(By.cssSelector(".actions a")).click();
		
		String text=driver.findElement(By.cssSelector("[align='center'] h1")).getText();
		System.out.println(text);
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
		
	}
}
