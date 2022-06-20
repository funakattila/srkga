import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
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

    @Description("Navigate to user login page")
    @Story("Test user login")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void navigateToLoginPageTest() {
        UserLoginPage userLoginPage = new UserLoginPage(driver);

        userLoginPage.navigate();
        userLoginPage.clickToAllowCookies();

        Allure.addAttachment("The login page opens",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.loginUserPageTitle;
        String actual = userLoginPage.getPageTitle();

        Assertions.assertEquals(expected, actual);
    }

    @Description("Test user login without remember me function")
    @Story("Test user login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void loginTest(){
        UserLoginPage userLoginPage = new UserLoginPage(driver);

        userLoginPage.navigate();
        userLoginPage.clickToAllowCookies();
        userLoginPage.loginUser("johndoe", "JoHnDoE#1", false);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"grav-login\"]/h4/strong")));
        WebElement welcomeMessageName = driver.findElement(By.xpath("//*[@id=\"grav-login\"]/h4/strong"));

        String expectedName = "John Doe";
        String actualName = welcomeMessageName.getText();

        Assertions.assertEquals(expectedName, actualName);
    }

    @Description("User login to site with remember me function")
    @Story("Test user login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void loginRememberTest(){
        UserLoginPage userLoginPage = new UserLoginPage(driver);

        userLoginPage.navigate();
        driver.manage().deleteCookieNamed("grav-rememberme");
        userLoginPage.clickToAllowCookies();
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
