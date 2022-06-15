import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestFU14TeamPage {

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
    public void teamMembersTest() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        String[] expected = fu14TeamPage.getTeamMembers();
        String[] actual = fu14TeamPage.teamMembers();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void numberOfTeamMembersTest() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        int expected = fu14TeamPage.getTeamMembers().length;
        int actual = fu14TeamPage.numberOfTeamMembers(fu14TeamPage.getTeamMembers());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void fu14AboutTeamTest() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        String expected = fu14TeamPage.readFileAboutTeam("src/files/about_fu14.txt");
        String actual = fu14TeamPage.getAboutTeamText();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void saveToFileTest() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();
        fu14TeamPage.createFile();
        fu14TeamPage.writeToFile();
    }


    @Test
    public void close() {
        driver.close();
    }


}
