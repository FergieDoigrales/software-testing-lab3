package model;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchResultPage extends BasePage {
    private final By resultsList = By.xpath("//div[@id='cat-wrapper']//ul[@class='catalogs']");
    private final By firstResultLink = By.xpath("//div[@id='cat-wrapper']//ul[@class='catalogs']/li[1]/a");
    private final By partNumberElements = By.xpath("//div[@id='cat-wrapper']//ul[@class='catalogs']//li//a//span[contains(text(), '55297')]");
    private final By descriptionElement = By.xpath("//div[@id='cat-wrapper']//ul[@class='catalogs']//li[1]//dd");

    private final By firstCodeResult = By.xpath("//a[contains(text(),'Лампа галоген\" Original Line H7\" 12В 55Вт, 1шт')]");
    private final By osramElement = By.xpath("//a[contains(text(),'Osram 64210')]");
    private final By analogueElement = By.xpath("//a[contains(text(),'Аналоги и другие предложения')]");
    private final By headerText = By.xpath("//div[@id='priceBody']/h1");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public void waitForResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(resultsList));
    }

    public ProductPage clickFirstResult() {
        WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(firstResultLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstResult);
        return new ProductPage(driver);
    }

    public void clickFirstCodeResult() {
        wait.until(ExpectedConditions.elementToBeClickable(firstCodeResult)).click();
    }

    public boolean isPartNumberDisplayed(String partNumber) {
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(partNumberElements));
            return !elements.isEmpty() && elements.stream().allMatch(element -> element.getText().contains(partNumber));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDescriptionDisplayed(String description) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionElement));
            return element.getText().contains(description);
        } catch (Exception e) {
            return false;
        }
    }

    public void checkAnalogue(){
        driver.switchTo().frame(1);
        wait.until(ExpectedConditions.elementToBeClickable(osramElement)).click();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(analogueElement)).click();
    }

    public String getAnalogHeaderText(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerText));
        return driver.findElement(headerText).getText().trim();

    }
}
