package test;

import com.google.common.base.Verify;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pom.BrowserDriver;
import pom.InventoryPage;
import pom.LogInPage;

import java.util.ArrayList;
import java.util.List;

public class Inventory_Test {

    private WebDriver driver;
    InventoryPage inventory;
    String inventoryUrl = "https://www.saucedemo.com/inventory.html";
    List<String> listItems;

    @BeforeClass
    public void setUP() {
        this.driver = Login_Test.driver;
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        //Gets browser logs if available.
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
    }


    @Test(priority = 1)
    public void homePage(){
        System.out.println("---------------------------- User Story 3 -------------------------------");

        inventory = new InventoryPage(driver);
        Assert.assertEquals(inventory.getURL(),inventoryUrl,"Validate that is the Inventory Page");
        System.out.println("The current url: " + inventory.getURL());
    }

    @Test(priority = 2)
    public void addItemToCart(){

        listItems = new ArrayList<>();
        listItems.add("Sauce Labs Backpack");
        listItems.add("Sauce Labs Bike Light");
        listItems.add("Sauce Labs Bolt T-Shirt");
        listItems.add("Sauce Labs Onesie");

        for (String item: listItems) {
            inventory.addToCart2(transform(item));
        }
    }

    @Test(priority = 3)
    public void goToCart(){
        inventory.iconCart.click();
        Assert.assertEquals(inventory.getURL(),"https://www.saucedemo.com/cart.html","Validate that is the Cart Page");
        System.out.println("Amount of items on the cart: "+inventory.iconCart.getText());
    }

    @Test(priority = 4, dependsOnMethods = {"goToCart","addItemToCart"})
    public void removeItemToCart(){

        SoftAssert sa = new SoftAssert();
        System.out.println("Amount of items on the cart: "+inventory.iconCart.getText());
        sa.assertEquals(inventory.iconCart.getText(), "4","Verify the amount of items on the Cart, it should be 4");


        inventory.removeToCart(transform(listItems.get(1)));
        System.out.println("Amount of items on the cart: "+inventory.iconCart.getText());
        sa.assertEquals(inventory.iconCart.getText(), "3","Verify the amount of items on the Cart, it should be 3");


        inventory.removeToCart(transform(listItems.get(2)));
        System.out.println("Amount of items on the cart: "+inventory.iconCart.getText());
        sa.assertEquals(inventory.iconCart.getText(), "2","Verify the amount of items on the Cart, it should be 2");

        sa.assertAll("Add and Remove items from the Cart, asserts");

    }

    @Test(priority = 5)
    public void goToCheckout(){
        SoftAssert sa = new SoftAssert();

        inventory.btnCheckout.click();
        Assert.assertEquals(inventory.getURL(),"https://www.saucedemo.com/checkout-step-one.html","Validate that is the Checkout Step one Page");

        inventory.fillCheckOutForm("Juan","Perez","506");
        inventory.btnContinue.click();

        Assert.assertEquals(inventory.getURL(),"https://www.saucedemo.com/checkout-step-two.html","Validate that is the Checkout Step two Page");

        System.out.println("Amount of items on the cart: "+inventory.iconCart.getText());
        sa.assertEquals(inventory.iconCart.getText(), "4","Verify the amount of items on the Cart, it should be 4");
        inventory.btnFinish.click();

        sa.assertAll("Verify the item amount before finish checkout, asserts");

        Assert.assertEquals(inventory.getURL(),"https://www.saucedemo.com/checkout-complete.html","Validate that is the Checkout Complete Page");
        Assert.assertEquals(inventory.txtCheckoutComplete.getText(),"CHECKOUT: COMPLETE!","Verify the last page of Checkout process");
    }

    public String transform(String labelName){
        String lowerName = labelName.toLowerCase();
        String newName = lowerName.replace(' ','-');
//        System.out.println(newName);
        return newName;
    }

    @Test(priority = 2)
    public void goToAbout(){

        inventory.btnMenu.click();

        inventory.waitisVisible(inventory.optAbout);
        Assert.assertTrue(inventory.optAbout.isDisplayed(),"Verify the About option is visible");

        inventory.optAbout.click();
        Assert.assertEquals(inventory.getURL(),"https://saucelabs.com/","Validate the About Page");

    }

    @Test(priority = 2)
    public void goToFooter(){
        inventory.scrollToItem(inventory.footer);

    }

    @Test(priority = 3)
    public void socialNetwork(){

        String homePageTab = inventory.getIdWindow();

//      Open Twitter
        System.out.println("The Twitter icon is visible: "+inventory.iconTwitter.isDisplayed());
        Assert.assertTrue(inventory.iconTwitter.isDisplayed(),"Verify the Twitter icon is visible");
        inventory.iconTwitter.click();


//      Open  Facebook
        System.out.println("The Facebook icon is visible: "+inventory.iconTwitter.isDisplayed());
        Assert.assertTrue(inventory.iconFb.isDisplayed(),"Verify the Facebook icon is visible");
        inventory.iconFb.click();

//      Open  Linkedin
        System.out.println("The Linkedin icon is visible: "+inventory.iconTwitter.isDisplayed());
        Assert.assertTrue(inventory.iconLinkedin.isDisplayed(),"Verify the Linkedin icon is visible");
        inventory.iconLinkedin.click();

        SoftAssert sa = new SoftAssert();

        //Loop through tabs
        for (String windowHandle : inventory.getIdWindows()) {
            inventory.switchTo(windowHandle);

            System.out.println("The current url: " + inventory.getURL());

            if(inventory.getURL().contains("twitter")){
                sa.assertEquals(inventory.getURL(),"https://twitter.com/saucelabs","Verify the url of Twitter Page");
            }
            if(inventory.getURL().contains("facebook")){
                sa.assertEquals(inventory.getURL(),"https://www.facebook.com/saucelabs","Verify the url of Facebook Page");
            }
            if(inventory.getURL().contains("linkedin")){
                sa.assertTrue(inventory.getURL().contains("https://www.linkedin.com/"),"Verify the url of Facebook Page");
            }

        }

        sa.assertAll("Verify the social Networks, asserts");

    }


}
