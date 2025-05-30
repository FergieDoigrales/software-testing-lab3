import model.BasePage;
import model.LoginForm;
import model.LogoutPage;
import model.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AuthTest {
    private WebDriver driver;
    private MainPage mainPage;


    public void setUp(BasePage.BrowserType browserType) {
        driver = BasePage.createDriver(browserType,  false);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @EnumSource(BasePage.BrowserType.class)
    public void logoutTest(BasePage.BrowserType browserType) {
        setUp(BasePage.BrowserType.FIREFOX);
        mainPage.open("https://www.exist.ru/");

        if (!mainPage.isUserLoggedIn()) {
            LoginForm loginForm = mainPage.clickLoginLink();
            mainPage = loginForm.login("mariwolf.com@yandex.ru", "k6Q7w7v");
        }

        mainPage.openPersonalCabinetMenu();
        mainPage.clickLogout();

        LogoutPage logoutPage = new LogoutPage(driver);
        String actualText = logoutPage.getLogoutText();
        assertTrue(actualText.contains("ВЫ УСПЕШНО ВЫШЛИ ИЗ СИСТЕМЫ"),
                "Страница логаута: ожидаемый текст 'Вы успешно вышли из системы', найден: '" + actualText + "'");
    }

    @ParameterizedTest
    @EnumSource(BasePage.BrowserType.class)
    public void authTest(BasePage.BrowserType browserType) {
        setUp(browserType);
        mainPage.open("https://www.exist.ru/");

        if (!mainPage.isUserLoggedIn()) {
            LoginForm loginForm = mainPage.clickLoginLink();
            mainPage = loginForm.login("mariwolf.com@yandex.ru", "k6Q7w7v");

        }
    }
}

