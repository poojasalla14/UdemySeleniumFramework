package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartObjects;
import pageObjects.CheckOutObject;
import pageObjects.ConfirmationPageObject;
import pageObjects.ConfirmationPageObject;
import pageObjects.LandingPage;
import pageObjects.OrderObjects;
import pageObjects.ProductPage;
import testComponent.BaseTest;

public class AddProducttocartTest extends BaseTest{
	public String prodname="ADIDAS ORIGINAL";
	
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitproduct(HashMap<String, String> value) throws IOException, InterruptedException
	{
		
		ProductPage pp=landingpage.login(value.get("email"),value.get("password"));
		pp.getProdList();
		pp.getProdName(value.get("prodname"));
		pp.addTocart(value.get("prodname"));
		CartObjects cartPage=pp.getClickonCart();
		//Boolean flag=cc.getCartDetails(prodname);
		Assert.assertTrue(cartPage.getCartDetails(value.get("prodname")));
		CheckOutObject checkoutPage=cartPage.checkout();
		checkoutPage.selectCountry("India");
		ConfirmationPageObject confirmationPage=checkoutPage.gotoConformationPage();
		String confirmMessage=confirmationPage.confirmationMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));	
	}
	
	@Test(dependsOnMethods ={"submitproduct"})
	public void verifyProductinOrdersList() {
		ProductPage pp=landingpage.login("poojasalla14@gmail.com", "Pooja@1234");
		OrderObjects orderObject=pp.gotoOrderPage();
		Boolean match=orderObject.verifyProductinorderList(prodname);
		Assert.assertTrue(match);	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = readDataFromJson(System.getProperty("user.dir")+"//src//test//java//data//PurchaseJson.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}


//@DataProvider
//public Object[][] getData() {
//	return new Object[][] {{"poojasalla14@gmail.com", "Pooja@1234","ADIDAS ORIGINAL"},{"poojagujiri@gmail.com", "Pooja@1234","iphone 13 pro"}};
//}

//@DataProvider
//public Object[][] getData() {
//	HashMap<String, String> map=new HashMap<String, String>();
//	map.put("email","poojasalla14@gmail.com" );
//	map.put("password", "Pooja@1234");
//	map.put("prodname", "ADIDAS ORIGINAL");
//	
//	HashMap<String, String> map1=new HashMap<String, String>();
//	map1.put("email","poojagujiri@gmail.com" );
//	map1.put("password", "Pooja@1234");
//	map1.put("prodname", "iphone 13 pro");
//	return new Object[][] {{map},{map1}};
//}
