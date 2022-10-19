package test;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pom.BrowserDriver;
import pom.LogInPage;
import pom.SauceLab;

import java.net.MalformedURLException;


public class Login_Test {

    public static WebDriver driver;
    String url = "https://www.saucedemo.com/";
    LogInPage login;
    String inventoryUrl = "https://www.saucedemo.com/inventory.html";

    @Parameters({"testName","browser"})
    @BeforeTest
    public void openBrowser(String testName, String browser) throws MalformedURLException {
        System.out.println("BeforeTest :: Open Browser: " + browser);
        switch (browser) {
            case "chrome":
                // Chrome WebDriver
//                driver = BrowserDriver.chromeDriverConnection();
                // SauceLab SetUp
                driver = SauceLab.chromeSetUp(testName);
                break;
            case "firefox":
                // Firefox WebDriver
//                driver = BrowserDriver.firefoxDriverConnection();
                // SauceLab SetUp
                driver = SauceLab.firefoxSetUp(testName);
                break;
            case "edge":
                // Edge WebDriver
//                driver = BrowserDriver.edgeDriverConnection();
                // SauceLab SetUp
                driver = SauceLab.edgeSetUp(testName);
                break;
            default:
                System.out.println("The: " + browser + " is not a valid action");
                throw new AssertionError();
        }

    }

    @AfterTest(enabled = true)
    public void closeBrowser() {
        login.closeBrowser();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        //Gets browser logs if available.
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
    }

    @BeforeClass
    public void loginPage() {
        System.out.println("---------------------------- Login_Test Test -------------------------------");
        login = new LogInPage(driver);
        login.maxiBrowser();
        System.out.println("Go to: "+url);
        login.visit(url);
    }

    @Test(priority = 1)
    public void login(){
        System.out.println("---------------------------- User Story 1 -------------------------------");

        login.userLogin("standard_user","secret_sauce");
        Assert.assertEquals(login.getURL(),inventoryUrl);
        System.out.println("The current url: " + login.getURL());
    }

    @Test(priority = 1)
    public void wrongCredencials(){
        System.out.println("---------------------------- User Story 2 -------------------------------");
        login.userLogin("user1","123");
        login.waitisVisible(login.txtAlertError);
        Assert.assertTrue(login.txtAlertError.isDisplayed());

        if(login.txtAlertError.isDisplayed()){
            System.out.println("Error message alert: " + login.txtAlertError.getText());
        }
    }


}
