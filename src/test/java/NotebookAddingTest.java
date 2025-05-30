import model.BasePage;
import model.GaragePage;
import model.LoginForm;
import model.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;

public class NotebookAddingTest {
    private WebDriver driver;
    private MainPage mainPage;

    public void setUp(BasePage.BrowserType browserType) {
        driver = BasePage.createDriver(browserType, false);
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
    public void addToNotebookTest(BasePage.BrowserType browserType) {
        setUp(browserType);
        mainPage.open("https://www.exist.ru/");

        if (!mainPage.isUserLoggedIn()) {
            LoginForm loginForm = mainPage.clickLoginLink();
            mainPage = loginForm.login("mariwolf.com@yandex.ru", "k6Q7w7v");
        }

        GaragePage garagePage = mainPage.openGarage();
        garagePage.pickFistCar();
        garagePage.addNote("52997");
    }
}
