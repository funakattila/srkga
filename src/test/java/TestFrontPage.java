import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @Test
    @Description("Open the front page of srkgakezilabda.hu")
    public void openSiteTest() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "http://srkgakezilabda.hu/";
        Assertions.assertEquals(expectedUrl, actualUrl);
    }


    @Test
    @Description("Find the logo on the main page")
    @Attachment(value = "imageCaptured")
    public void findLogo() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        String expected = TestData.logoPath;
        String actual = frontPage.findLogo();

        Assertions.assertEquals(expected, actual);


    }

    @AfterEach
    public void close() {
        driver.close();
    }

}


