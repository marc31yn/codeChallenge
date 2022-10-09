package pom;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Base {

    private WebDriver driver;

    public Base(WebDriver driver){
        this.driver = driver;
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    public void visit(String url){
        driver.navigate().to(url);
    }

    public void goBack(){
        driver.navigate().back();
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }

    public void maxiBrowser(){
        driver.manage().window().maximize();
    }

    public void closeBrowser(){
        driver.quit();
    }



    public void closeTab(){
        driver.close();
    }

    public int countOpenTab(){
        return driver.getWindowHandles().size();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void switchTo(String idWindow){
        driver.switchTo().window(idWindow);
    }

    public void newTab(){
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public Set<String> getIdWindows(){
        return driver.getWindowHandles();
    }

    public void waitisVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitisVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }



    public void scrollToItem(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void numberOfWindowsToBe(int windowCount){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.numberOfWindowsToBe(windowCount));
    }

    public void waitalertIsPresent(int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public Alert waitalerVerify(int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void hoverClick(By locator){
        Actions builder = new Actions(driver);
        builder.moveToElement(findElement(locator))
                .click()
                .build()
                .perform();
    }

    public void hover(WebElement element){
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
    }

    public WebElement getItemList(List<WebElement> list, int position){
        return list.get(position);
    }

    public String getText(By locator){
        return findElement(locator).getText();
    }

    public String getHref(By locator){
        return findElement(locator).getAttribute("href");
    }

    public void submitAction(By locator){
        findElement(locator).submit();
    }

    public void click(WebElement item){
        item.click();
    }

    public String getIdWindow(){
        return driver.getWindowHandle();
    }

    public void typeText(String inputText, WebElement item){
        item.sendKeys(inputText);
    }

    public void scrollIntoViewJS(By locator){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", findElement(locator));
    }


    public String getPlaceholder(By locator){
        return findElement(locator).getAttribute("placeholder");
    }

    public String getValue(WebElement element){
        return element.getAttribute("value");
    }


    public List<WebElement> getDropdownList(By locator){

        Select drpOption = new Select(findElement(locator));
        return drpOption.getOptions();
    }

    public void selectByText(By locator, String text){
        Select drpOption = new Select(findElement(locator));
        drpOption.selectByVisibleText(text);
    }

}
