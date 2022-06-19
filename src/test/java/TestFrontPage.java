import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestFrontPage {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Description("Check the url of the front page")
    @Story("Test the front page")
    @Test
    public void checkSiteURL() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();

        String expected = TestData.mainURL;
        String actual = driver.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
    }

    @Description("Allow cookies")
    @Story("Test the front page")
    @Disabled
    @Test
    public void allowCookies() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        Assertions.assertFalse(frontPage.isCookieBannerVisible());

    }

    @Description("Find the logo on the main page")
    @Story("Test the front page")
    @Test
    public void findLogo() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        String expected = TestData.logoPath;
        String actual = frontPage.findLogo();

        Assertions.assertEquals(expected, actual);
    }

    @Description("Check the number of the latest blog entries")
    @Story("Test the front page")
    @Test
    public void numberOfBlogEntries() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        int expected = TestData.numberOfBlogEntries;
        int actual = frontPage.countBlogEntries();

        Assertions.assertEquals(expected, actual);
    }

    @Description("Check the titles of the blog entries")
    @Story("Test the front page")
    @Disabled
    @Test
    public void checkTitles() {
        FrontPage frontPage = new FrontPage(driver);
        frontPage.navigate();
        frontPage.clickToAllowCookies();

        String[] expected = TestData.latestBlogEntryTitles;
        String[] actual = frontPage.collectBlogEntryTitles();

        Assertions.assertArrayEquals(expected, actual);
    }


    @Description("Check all the team photos are present in the carousel")
    @Story("Test the front page")
    @Test
    public void isAllImagePresent() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        Assertions.assertTrue(frontPage.isAllImagePresent());
    }


    @AfterEach
    public void close() {
        driver.close();
    }

}


