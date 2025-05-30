package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogoutPage extends BasePage {
//    private By logoutHeader = By.xpath("//div[contains(@class, 'logged-out-page')]//h1");
    private final By logoutText = By.xpath("//div[contains(@class, 'logged-out-page')]//h1/small");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public String getLogoutText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutText));
        return driver.findElement(logoutText).getText().trim();
    }
}
