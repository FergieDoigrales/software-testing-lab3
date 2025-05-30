import model.BasePage;
import model.MainPage;
import model.SearchResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalogueTest {
    private MainPage mainPage;
    private WebDriver driver;

//    @BeforeEach
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
    public void searchAnalogueTest(BasePage.BrowserType browserType) {
        setUp(browserType);
        mainPage.open("https://www.exist.ru/");

        SearchResultPage searchResult = mainPage.searchByCode("64210");

        searchResult.clickFirstCodeResult();
        searchResult.checkAnalogue();

        String actualText = searchResult.getAnalogHeaderText();
        assertTrue(actualText.contains("Предложения для  Osram 64210"),
                "Найдено: '" + actualText + "'");
    }

}
