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

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebDriver createDriverWithProxy() {
        Random random = new Random();
        List<String> proxies = List.of(
                "189.240.60.166:9090",
                "209.97.150.167:8080",
                "57.129.81.201:8080"
        );
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--proxy-server=" + proxies.get(random.nextInt(proxies.size())));
        return new FirefoxDriver(options);
    }
}