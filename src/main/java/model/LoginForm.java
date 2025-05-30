package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginForm extends BasePage {
    private final By loginField = By.id("login");
    private final By passwordField = By.id("pass");
    private final By loginButton = By.id("btnLogin");

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public MainPage login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginField));
        driver.findElement(loginField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }
}
