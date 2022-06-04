import io.github.bonigarcia.wdm.WebDriverManager;
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

public class SRKGATest {

    WebDriver driver;
    String baseURL = "http://srkgakezilabda.hu";

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
    public void openSite() {
        driver.navigate().to(baseURL);
    }

    @Test
    public void createUser() {
        driver.navigate().to(baseURL + "/user_register");

        //Allow cookies
        WebElement allowButton = driver.findElement(By.xpath("//*[@id=\"tecart-cookie-banner\"]//a"));

        //Wait for popup window
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tecart-cookie-banner\"]")));

        allowButton.click();

        //Enter Full Name
        WebElement inputFullName = driver.findElement(By.xpath("//input[@name=\"data[fullname]\"]"));
        inputFullName.sendKeys("John Doe");

        //Enter username
        WebElement inputUsername = driver.findElement(By.xpath("//input[@name=\"data[username]\"]"));
        inputUsername.sendKeys("johndoe");

        //Enter email
        WebElement inputEmail = driver.findElement(By.xpath("//input[@name=\"data[email]\"]"));
        inputEmail.sendKeys("johndoe@foo.bar");


    }
}
