import model.GaragePage;
import model.LoginForm;
import model.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GarageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @Test
    public void testAddVehicleToGarage() {

        mainPage.open("https://www.exist.ru/");

        if (!mainPage.isUserLoggedIn()) {
            LoginForm loginForm = mainPage.clickLoginLink();
            mainPage = loginForm.login("krotovuha@gmail.com", "krotovuha");
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