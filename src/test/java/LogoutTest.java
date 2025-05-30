import model.BasePage;
import model.LoginForm;
import model.LogoutPage;
import model.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LogoutTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = BasePage.createDriverWithProxy();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void logoutTest() {
        mainPage.open("https://www.exist.ru/");

        if (!mainPage.isUserLoggedIn()) {
            LoginForm loginForm = mainPage.clickLoginLink();
            mainPage = loginForm.login("krotovuha@gmail.com", "krotovuha");
        }

        mainPage.openPersonalCabinetMenu();
        mainPage.clickLogout();

        LogoutPage logoutPage = new LogoutPage(driver);
        String actualText = logoutPage.getLogoutText();
        assertTrue(actualText.contains("ВЫ УСПЕШНО ВЫШЛИ ИЗ СИСТЕМЫ"),
                "Страница логаута: ожидаемый текст 'Вы успешно вышли из системы', найден: '" + actualText + "'");
    }

    @Test
    public void authTest() {
        mainPage.open("https://www.exist.ru/");

        if (!mainPage.isUserLoggedIn()) {
            LoginForm loginForm = mainPage.clickLoginLink();
            mainPage = loginForm.login("krotovuha@gmail.com", "krotovuha");

        }
    }
}

