package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final List<String> PROXIES = List.of(
            "189.240.60.166:9090",
            "209.97.150.167:8080",
            "57.129.81.201:8080"
    );

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebDriver createDriver(BrowserType browserType, boolean useProxy) {
        switch (browserType) {
            case CHROME:
                return createChromeDriver(useProxy);
            case FIREFOX:
                return createFirefoxDriver(useProxy);
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    private static ChromeDriver createChromeDriver(boolean useProxy) {
        ChromeOptions options = new ChromeOptions();
        if (useProxy) {
            options.addArguments("--proxy-server=" + getRandomProxy());
        }
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    private static FirefoxDriver createFirefoxDriver(boolean useProxy) {
        FirefoxOptions options = new FirefoxOptions();
        if (useProxy) {
            options.addArguments("--proxy-server=" + getRandomProxy());
        }
        options.addArguments("--start-maximized");
        return new FirefoxDriver(options);
    }

    private static String getRandomProxy() {
        return PROXIES.get(new Random().nextInt(PROXIES.size()));
    }

    public enum BrowserType {
        CHROME,
        FIREFOX
    }
}