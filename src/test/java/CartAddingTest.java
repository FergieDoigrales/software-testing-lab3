import model.BasePage;
import model.MainPage;
import model.ProductPage;
import model.SearchResultPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartAddingTest {
    private WebDriver driver;
    private MainPage mainPage;

    public void setUp(BasePage.BrowserType browserType) {
        driver = BasePage.createDriver(browserType, false);
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }

    @ParameterizedTest
    @EnumSource(BasePage.BrowserType.class)
    public void testAddFirstFoundItemToCart(BasePage.BrowserType browserType) {
        try {
            setUp(browserType);
            mainPage.open("https://www.exist.ru/");

            SearchResultPage searchResultsPage = mainPage.searchByPartNumber("AT-05");
            searchResultsPage.waitForResults();
            ProductPage productPage = searchResultsPage.clickFirstResult();

            productPage.waitForPageLoad();

            productPage.addToCart();
            String cartCount = productPage.getCartCount();

            assertEquals("1", cartCount, "Товар не был добавлен в корзину");

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            if (driver != null) {
                System.out.println(driver.getPageSource());
            }
            throw e;
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}