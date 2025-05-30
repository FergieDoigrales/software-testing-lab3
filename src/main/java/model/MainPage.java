package model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    private final By loginLink = By.xpath("//div[@id='pnlLogin']/div");
    private final By personalCabinetLink = By.xpath("//span[contains(text(), 'Личный кабинет')]");
    private final By userProfileLink = By.cssSelector("div#pnlLogin a[href='/Profile/']");
    private final By logoutButton = By.xpath("//a[@id='logout']");
    private final By searchField = By.xpath("//input[@id='pcode']");
    private final By GarageLink = By.xpath("//a[contains(text(),'Гараж')]");
    private final By searchCode = By.xpath("//v[contains(.,'— Поиск по штрихкоду')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open(String link) {
        driver.get(link);
    }

    public LoginForm clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new LoginForm(driver);
    }

    public boolean isUserLoggedIn() {
        try {
            return driver.findElement(userProfileLink).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openPersonalCabinetMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(personalCabinetLink)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public SearchResultPage searchByPartNumber(String partNumber) {
        WebElement searchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        searchElement.click();
        searchElement.sendKeys(partNumber);
        searchElement.sendKeys(Keys.ENTER);
        return new SearchResultPage(driver);
    }

    public SearchResultPage searchByCode(String code) {
        WebElement searchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        searchElement.click();
        searchElement.sendKeys(code);
        wait.until(ExpectedConditions.elementToBeClickable(searchCode)).click();
        return new SearchResultPage(driver);
    }

    private final By garageHeader = By.xpath("//div[@id='garageFloatHead']/span");

    public GaragePage openGarage() {
        wait.until(ExpectedConditions.elementToBeClickable(personalCabinetLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(GarageLink)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(garageHeader)).click();
        return new GaragePage(driver);
    }
}