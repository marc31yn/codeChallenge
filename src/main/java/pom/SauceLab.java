package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SauceLab {

    public static final String USERNAME = "melopez";
    public static final String ACCESS_KEY = "8987df4e-de0f-4cb4-9a09-bd7a7cf845fa";
    public static List<String> tags = Arrays.asList("automationTrainee", "CodeChallenge", "Selenium","TestNG", "javaTest");


    public static WebDriver chromeSetUp(String testName) throws MalformedURLException {
        ChromeOptions opt = new ChromeOptions();
        opt.setPlatformName("Windows 10");
        opt.setBrowserVersion("latest");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", USERNAME);
        sauceOptions.put("accessKey", ACCESS_KEY);
        sauceOptions.put("name", testName+"_Chrome");
        sauceOptions.put("build","1.0-UI Test");
        sauceOptions.put("screenResolution", "1920x1080");
        sauceOptions.put("tags", tags);
        sauceOptions.put("seleniumVersion", "4.3.0");
        sauceOptions.put("extendedDebugging", "true");
        sauceOptions.put("capturePerformance", "true");
        sauceOptions.put("extendedDebugging", "true");

        opt.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");
        return new RemoteWebDriver(url, opt);
    }

    public static WebDriver firefoxSetUp(String testName) throws MalformedURLException {
        FirefoxOptions opt = new FirefoxOptions();
        opt.setPlatformName("Windows 10");
        opt.setBrowserVersion("latest");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", USERNAME);
        sauceOptions.put("accessKey", ACCESS_KEY);
        sauceOptions.put("name", testName+"_Firefox");
        sauceOptions.put("build","1.0-UI Test");
        sauceOptions.put("screenResolution", "1920x1080");
        sauceOptions.put("tags", tags);
        sauceOptions.put("seleniumVersion", "4.3.0");
        sauceOptions.put("extendedDebugging", "true");
        sauceOptions.put("capturePerformance", "true");
        sauceOptions.put("extendedDebugging", "true");

        opt.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");
        return new RemoteWebDriver(url, opt);
    }

    public static WebDriver edgeSetUp(String testName) throws MalformedURLException {
        EdgeOptions opt = new EdgeOptions();
        opt.setPlatformName("Windows 10");
        opt.setBrowserVersion("latest");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", USERNAME);
        sauceOptions.put("accessKey", ACCESS_KEY);
        sauceOptions.put("name", testName+"_Edge");
        sauceOptions.put("build","1.0-UI Test");
        sauceOptions.put("screenResolution", "1920x1080");
        sauceOptions.put("tags", tags);
        sauceOptions.put("seleniumVersion", "4.3.0");
        sauceOptions.put("extendedDebugging", "true");
        sauceOptions.put("capturePerformance", "true");
        sauceOptions.put("extendedDebugging", "true");

        opt.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");
        return new RemoteWebDriver(url, opt);
    }
}
