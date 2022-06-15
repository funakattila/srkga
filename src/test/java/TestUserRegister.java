import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @Test
    @Description("Navigate to the registration page")
    public void openPageTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();

        String expectedUrl = "http://srkgakezilabda.hu/user_register";
        String actualUrl = driver.getCurrentUrl();

        Assertions.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @Description("Reset all the entered data from the fields")
    public void resetUserDataTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("John Doe", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        userRegisterPage.pressResetButton();

        String fullName = driver.findElement(userRegisterPage.getFullNameField()).getText();
        String userName = driver.findElement(userRegisterPage.getUserNameField()).getText();
        String email = driver.findElement(userRegisterPage.getEmailField()).getText();
        String password1 = driver.findElement(userRegisterPage.getPassword1Field()).getText();
        String password2 = driver.findElement(userRegisterPage.getPassword2Field()).getText();
        boolean isAllFieldEmpty = false;
        if(fullName.equals("") && userName.equals("") && email.equals("") && password1.equals("") && password2.equals(
                "")){
            isAllFieldEmpty = true;
        }

        Assertions.assertTrue(isAllFieldEmpty);
    }

    @Test
    @Description("Register a new user without full name")
    public void userRegistrationWithoutFullNameTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        userRegisterPage.pressSubmitButton();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        String actual =
                wait.until(ExpectedConditions.elementToBeClickable(userRegisterPage.getFullNameField())).getAttribute(
                "validationMessage");
        String expected = "Kérjük, töltse ki ezt a mezőt.";

        Assertions.assertEquals(expected, actual);
    }



    @Test
    @Description("Register a new user")
    public void userRegistrationTest() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("John Doe", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        userRegisterPage.pressSubmitButton();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"notices success green\"]/p")));
        WebElement succesLabel = driver.findElement(By.xpath("//*[@class=\"notices success green\"]/p"));

        String expected = "Thank you for registering.";
        String actual = succesLabel.getText();

        Assertions.assertEquals(expected, actual);
    }

    @AfterEach
    public void close() {
        driver.close();
    }


}
