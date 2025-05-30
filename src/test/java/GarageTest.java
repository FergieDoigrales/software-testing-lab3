import model.BasePage;
import model.GaragePage;
import model.LoginForm;
import model.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GarageTest {
    private WebDriver driver;
    private MainPage mainPage;

    public void setUp(BasePage.BrowserType browserType) {
        driver = BasePage.createDriver(browserType, false);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @ParameterizedTest
    @EnumSource(BasePage.BrowserType.class)
    public void testAddVehicleToGarage(BasePage.BrowserType browserType) {
        setUp(browserType);
        mainPage.open("https://www.exist.ru/");

        if (!mainPage.isUserLoggedIn()) {
            LoginForm loginForm = mainPage.clickLoginLink();
            mainPage = loginForm.login("coshkodevochka@yandex.ru", "yR5jf2Y");
//            assertTrue(mainPage.isUserLoggedIn(), "Пользователь не авторизован");
        }

        GaragePage garagePage = mainPage.openGarage();
        garagePage.hoverOverGarageHeader();

        garagePage.clickAddNewVehicle();
        String testVin = "WAUAUGFF9J1005011";
        garagePage.enterVinNumber(testVin);
        garagePage.clickContinue();
        garagePage.selectSpecificVehicle();
        garagePage.saveVehicle();
        assertTrue(garagePage.isVehicleAdded(testVin),
                "ТС с VIN " + testVin + " не было добавлено в гараж");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}