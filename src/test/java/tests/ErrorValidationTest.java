package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartObjects;
import pageObjects.ProductPage;
import testComponent.BaseTest;
import testComponent.Retry;

public class ErrorValidationTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() {
		landingpage.login("poojasalla14@gmail.com", "Pooj234");
		String errormsg=landingpage.invalidlogin();
		Assert.assertEquals("Incorrect email password.",errormsg);	
	}
	
	@Test
	public void verifyaddedProduct() throws InterruptedException
	{
		String prodname="ADIDAS ORIGINAL";
		ProductPage pp=landingpage.login("poojasalla14@gmail.com", "Pooja@1234");
		pp.getProdList();
		pp.getProdName(prodname);
		pp.addTocart(prodname);
		CartObjects cartPage=pp.getClickonCart();
		//Boolean flag=cc.getCartDetails(prodname);
		Assert.assertFalse(cartPage.getCartDetails("Adidas og"));
	}
}
