import model.MainPage;
import model.SearchResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalogueTest {
    private MainPage mainPage;
    private WebDriver driver;



    @BeforeEach
    public void setUp() {
       this.driver = new FirefoxDriver();
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
    public void searchAnalogueTest() {
        mainPage.open("https://www.exist.ru/");

        SearchResultPage searchResult = mainPage.searchByCode("64210");

        searchResult.clickFirstCodeResult();
        searchResult.checkAnalogue();

        String actualText = searchResult.getAnalogHeaderText();
        assertTrue(actualText.contains("Предложения для  Osram 64210"),
                "Найдено: '" + actualText + "'");
    }

}