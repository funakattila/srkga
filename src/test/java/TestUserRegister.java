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
import java.util.List;
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
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Description("Navigate to the registration page")
    @Story("Test user registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void openRegisterPageTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Allure.addAttachment("The register site opens",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.registerUserPageTitle;
        String actual = userRegisterPage.getPageTitle();

        Assertions.assertEquals(expected, actual);
    }

    @Description("Reset all the entered data from the fields")
    @Story("Test user registration")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void resetUserDataTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("John Doe", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        Allure.addAttachment("The form filled with data",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        userRegisterPage.pressResetButton();
        Allure.addAttachment("The form after press the reset button",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertTrue(userRegisterPage.isAllFieldEmpty());
    }

    @Description("Register a new user without full name")
    @Story("Test user registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void userRegistrationWithoutFullNameTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        Allure.addAttachment("The Full Name field is empty",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        userRegisterPage.pressSubmitButton();

        Allure.addAttachment("Registration failed",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertTrue(userRegisterPage.isOneFieldEmpty());
    }


    @Description("Register a new user")
    @Story("Test user registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void userRegistrationTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("John Doe", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        userRegisterPage.pressSubmitButton();

        Allure.addAttachment("Screenshot",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"notices success green\"]/p")));
        WebElement succesLabel = driver.findElement(By.xpath("//*[@class=\"notices success green\"]/p"));
        Allure.addAttachment("Registration success",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.registrationSuccessText;
        String actual = succesLabel.getText();

        Assertions.assertEquals(expected, actual);
    }

    @Description("Register a new users from file")
    @Story("Test user registration")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void userRegistrationFromFileTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        List<String[]> list = userRegisterPage.addUsersFromFile("src/files/users.txt");

        for (int i = 0; i < list.size(); i++) {
            String[] user = list.get(i);

            String fullname = user[0];
            String username = user[1];
            String email = user[2];
            String password1 = user[3];
            String password2 = user[4];

            userRegisterPage.enterUserData(fullname, username, email, password1, password2);
            userRegisterPage.pressSubmitButton();
            Allure.addAttachment("Registration success",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

            userRegisterPage.navigate();
        }



    }

    @AfterEach
    public void close() {
        driver.close();
    }


}
