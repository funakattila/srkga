import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;

public class TestUserLogin extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/


    // Navigate to user login page
    @Description("Navigate to user login page - TC13")
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


    // Test user login without remember me function
    @Description("Test user login without remember me function - TC14")
    @Story("Test user login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void loginTest(){
        UserLoginPage userLoginPage = new UserLoginPage(driver, wait);

        userLoginPage.navigate();
        userLoginPage.clickToAllowCookies();
        userLoginPage.loginUser("johndoe", "JoHnDoE#1", false);

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"grav-login\"]/h4/strong")));
        WebElement welcomeMessageName = driver.findElement(By.xpath("//*[@id=\"grav-login\"]/h4/strong"));

        String expected = TestData.userFullName;
        String actual = welcomeMessageName.getText();

        Assertions.assertEquals(expected, actual);
    }


    // User login to site with remember me function
    @Description("User login to site with remember me function - TC15")
    @Story("Test user login")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void loginRememberTest(){
        UserLoginPage userLoginPage = new UserLoginPage(driver);

        userLoginPage.navigate();
        driver.manage().deleteCookieNamed("grav-rememberme");
        userLoginPage.clickToAllowCookies();
        userLoginPage.loginUser("johndoe", "JoHnDoE#1", true);

        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"grav-login\"]/h4/strong")));

        Cookie rememberMe = driver.manage().getCookieNamed("grav-rememberme");
        String cookieValue = rememberMe.getValue();
        boolean isRememberMe = cookieValue.startsWith("johndoe");

        Assertions.assertTrue(isRememberMe);
    }

}
