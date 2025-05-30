import model.BasePage;
import model.GaragePage;
import model.LoginForm;
import model.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class NotebookAddingTest {
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
    public void addToNotebookTest() {
        mainPage.open("https://www.exist.ru/");

        if (!mainPage.isUserLoggedIn()) {
            LoginForm loginForm = mainPage.clickLoginLink();
            mainPage = loginForm.login("krotovuha@gmail.com", "krotovuha");
        }

        GaragePage garagePage = mainPage.openGarage();
        garagePage.pickFistCar();
        garagePage.addNote("52997");
    }
}
