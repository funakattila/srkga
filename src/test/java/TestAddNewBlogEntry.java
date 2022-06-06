import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestAddNewBlogEntry {
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
    @Description("Login as editor")
    public void loginAsEditorTest() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.navigateAndLogin();

        String expectedUserName = "Jane Doe";
        String actualUserName = driver.findElement(By.xpath("//*[@id=\"admin-user-details\"]//h4")).getText();

        Assertions.assertTrue(actualUserName.startsWith(expectedUserName));
    }

    @Test
    @Description("Login and create a new blog entry")
    public void createBlogEntryTest() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.createBlogEntry("Lorem Ipsum");
        driver.navigate().to("http://srkgakezilabda.hu/blog/lorem-ipsum");

        String expectedUrl = "http://srkgakezilabda.hu/blog/lorem-ipsum";
        String actualUrl = driver.getCurrentUrl();

        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @AfterEach
    public void close() {
        driver.close();
    }

}
