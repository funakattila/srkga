import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;

@Epic("Regression tests")
@Feature("Test the user login")
@Link("https://docs.google.com/spreadsheets/d/17usWINlHQc322-yzI4dsEL2Y6qsqkedloQqOz0GRvz8/edit?usp=sharing")
public class UserLoginTest extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/


    @Test
    @Story("Navigate to user login page")
    @Description("Test Case 13")
    @Severity(SeverityLevel.TRIVIAL)
    public void TestNavigateToLoginPage() {
        UserLoginPage userLoginPage = new UserLoginPage(driver);

        userLoginPage.navigate();
        userLoginPage.clickToAllowCookies();

        Allure.addAttachment("The login page opens",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.loginUserPageTitle;
        String actual = userLoginPage.getPageTitle();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Story("Test user login without remember me function")
    @Description("Test Case TC14")
    @Severity(SeverityLevel.NORMAL)
    public void TestLogin(){
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

    @Test
    @Story("User login to site with remember me function")
    @Description("Test Case 15")
    @Severity(SeverityLevel.NORMAL)
    public void TestLoginRemember(){
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
