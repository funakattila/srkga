import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestUserLogin {

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
    @Description("Navigate to the Login page")
    public void navigateToLoginPageTest() {
        UserLoginPage userLoginPage = new UserLoginPage(driver);
        userLoginPage.navigate();

        String expectedUrl = "http://srkgakezilabda.hu/login";
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @Description("User login to site without remember me function")
    public void loginTest(){
        UserLoginPage userLoginPage = new UserLoginPage(driver);
        AllowCookies allowCookies = new AllowCookies(driver);

        userLoginPage.navigate();
        allowCookies.clickToAllowCookies();
        userLoginPage.loginUser("johndoe", "JoHnDoE#1", false);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"grav-login\"]/h4/strong")));
        WebElement welcomeMessageName = driver.findElement(By.xpath("//*[@id=\"grav-login\"]/h4/strong"));

        String expectedName = "John Doe";
        String actualName = welcomeMessageName.getText();
        Assertions.assertEquals(expectedName, actualName);

    }

    @Test
    @Description("User login to site with remember me function")
    public void loginRememberTest(){
        UserLoginPage userLoginPage = new UserLoginPage(driver);
        AllowCookies allowCookies = new AllowCookies(driver);

        userLoginPage.navigate();
        driver.manage().deleteCookieNamed("grav-rememberme");
        allowCookies.clickToAllowCookies();
        userLoginPage.loginUser("johndoe", "JoHnDoE#1", true);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"grav-login\"]/h4/strong")));

        Cookie rememberMe = driver.manage().getCookieNamed("grav-rememberme");
        String cookieValue = rememberMe.getValue();
        boolean isRememberMe = cookieValue.startsWith("johndoe");
        Assertions.assertTrue(isRememberMe);
    }

    @AfterEach
    public void close() {
        driver.close();
    }
}
