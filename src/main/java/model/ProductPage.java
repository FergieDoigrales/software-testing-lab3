package model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {
    private final By productTitle = By.xpath("//form[@id='form1']/div[4]/div/h1");
    private final By addToCartButton = By.xpath("//a[@class='basket'][1]");
    private final By cartCounter = By.xpath("//span[@id='basketcount']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
    }

    public void addToCart() {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
    }

    public String getCartCount() {
        WebElement counter = wait.until(ExpectedConditions.visibilityOfElementLocated(cartCounter));
        wait.until(ExpectedConditions.textToBe(cartCounter, "1"));
        return counter.getText();
    }
}