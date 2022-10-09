package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends Base {

    By itemsInventory = By.xpath("//div[@class='inventory_item']");
    By btnAddCartItem = By.xpath("//button[contains(text(),'Add to cart')]");
    By labelAddCartItem = By.xpath("//div[@class='inventory_item_name']");
    By priceItems = By.xpath("//div[@class='inventory_item_price']");

    @FindBy(xpath = "//div[@id='shopping_cart_container']")
    public WebElement iconCart;

    @FindBy(xpath = "//button[@id='checkout']")
    public WebElement btnCheckout;

    @FindBy(xpath = "//input[@id='first-name']")
    public WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='last-name']")
    public WebElement txtLastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    public WebElement txtPostalCode;

    @FindBy(xpath = "//input[@id='continue']")
    public WebElement btnContinue;

    @FindBy(xpath = "//button[@id='finish']")
    public WebElement btnFinish;

    @FindBy(xpath = "//span[contains(text(),'Checkout: Complete!')]")
    public WebElement txtCheckoutComplete;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    public WebElement btnMenu;

    @FindBy(xpath = "//a[@id='about_sidebar_link']")
    public WebElement optAbout;

    @FindBy(tagName = "footer")
    public WebElement footer;

    @FindBy(xpath = "//a[contains(text(),'Twitter')]")
    public WebElement iconTwitter;

    @FindBy(xpath = "//a[contains(text(),'Facebook')]")
    public WebElement iconFb;

    @FindBy(xpath = "//a[contains(text(),'LinkedIn')]")
    public WebElement iconLinkedin;

    public InventoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addToCart(Integer amountItem){
        List<WebElement> itemList = findElements(btnAddCartItem);
        List<WebElement> itemListNames = findElements(labelAddCartItem);
        List<WebElement> itemListPrice = findElements(priceItems);

        List<String> itemsSelected = new ArrayList<String>();
        List<String> pricesSelected = new ArrayList<String>();


        for (int i = 0; i < amountItem; i++) {
            itemList.get(i).click();
            System.out.println(itemListNames.get(i).getText());
            itemsSelected.add(itemListNames.get(i).getText());
            System.out.println(itemListPrice.get(i).getText());
            pricesSelected.add(itemListPrice.get(i).getText());
        }

//        iconCart.click();
    }

    public void addToCart2(String nameItem){
        WebElement element = findElement(By.xpath("//button[@id='add-to-cart-"+nameItem+"']"));
        element.click();
    }

    public void removeToCart(String nameItem){
        WebElement element = findElement(By.xpath("//button[@id='remove-"+nameItem+"']"));
        waitisVisible(element);
        element.click();
    }

    public void fillCheckOutForm(String firstName, String lastName, String postalCode){

        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);

        txtLastName.clear();
        txtLastName.sendKeys(lastName);

        txtPostalCode.clear();
        txtPostalCode.sendKeys(postalCode);
    }
}
