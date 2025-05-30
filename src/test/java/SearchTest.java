import model.BasePage;
import model.MainPage;
import model.SearchResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = BasePage.createDriverWithProxy();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @Test
    public void testSearchByPartNumber() {
        mainPage.open("https://www.exist.ru/");

        String partNumber = "55297";
        String expectedDescription = "Канистра profi 5л для топлива в комплекте с крышкой и лейкой";

        var searchResultPage = mainPage.searchByPartNumber(partNumber);

        assertTrue(searchResultPage.isPartNumberDisplayed(partNumber),
                "Артикул " + partNumber + " не найден на странице результатов");

        assertTrue(searchResultPage.isDescriptionDisplayed(expectedDescription),
                "Описание товара не соответствует ожидаемому");

    }

    @Test
    public void testSearchByCode(){
        mainPage.open("https://www.exist.ru/");

        String code = "64210";
        String expectedDescription = "Предложения для  Osram 64210";

        SearchResultPage searchResultPage = mainPage.searchByCode(code);
        searchResultPage.clickFirstCodeResult();

        String actualText = searchResultPage.getAnalogHeaderText();
        assertTrue(actualText.contains(expectedDescription));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}