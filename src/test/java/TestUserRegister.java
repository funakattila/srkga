import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestUserRegister {

    WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void navigateToRegistrationPage() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);
        userRegisterPage.navigate();

        String expectedUrl = "http://srkgakezilabda.hu/user_register";
        String actualUrl = driver.getCurrentUrl();

        Assertions.assertEquals(expectedUrl, actualUrl);

    }

    @Test
    public void userRegistrationResetDataTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);
        AllowCookies allowCookies = new AllowCookies(driver);

        userRegisterPage.navigate();
        allowCookies.clickToAllowCookies();

        userRegisterPage.enterUserDatas("John Doe", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");

        userRegisterPage.pressResetButton();

    }
}
