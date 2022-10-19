package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends Base {

//    private String username = "standard_user";
//    private String password = "secret_sauce";

    @FindBy(xpath = "//input[@id='user-name']")
    public WebElement txtUser;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement txtPss;

    @FindBy(xpath = "//input[@id='login-button']")
    public WebElement btnLogin;

    @FindBy(xpath = "//h3[contains(text(),'Epic sadface:')]")
    public WebElement txtAlertError;

    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void userLogin(String username, String password) {
        txtUser.clear();
        typeText(username, txtUser);
        txtPss.clear();
        typeText(password, txtPss);
        click(btnLogin);
    }


}
