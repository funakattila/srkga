import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.List;


@Epic("Regression tests")
@Feature("Test the user registration")
@Link("https://docs.google.com/spreadsheets/d/17usWINlHQc322-yzI4dsEL2Y6qsqkedloQqOz0GRvz8/edit?usp=sharing")
public class UserRegisterTest extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/


    @Test
    @Story("Navigate to the registration page")
    @Description("Test Case 08")
    @Severity(SeverityLevel.TRIVIAL)
    public void TestOpenRegisterPage() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver, wait);
        wait = new WebDriverWait(driver, 30);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Allure.addAttachment("The register page opens",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.registerUserPageTitle;
        String actual = userRegisterPage.getPageTitle();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Story("Register a new user without full name")
    @Description("Test Case 09")
    @Severity(SeverityLevel.CRITICAL)
    public void TestUserRegistrationWithoutFullName() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        Allure.addAttachment("The Full Name field is empty",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        userRegisterPage.clickSubmitButton();

        Allure.addAttachment("Registration failed",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertTrue(userRegisterPage.isOneFieldEmpty());
    }

    @Test
    @Story("Reset all the entered data from the fields")
    @Description("Test Case 10")
    @Severity(SeverityLevel.MINOR)
    public void TestResetUserData() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("John Doe", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        Allure.addAttachment("The form filled with data",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        userRegisterPage.clickResetButton();
        Allure.addAttachment("The form after press the reset button",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertTrue(userRegisterPage.areAllFieldsEmpty());
    }

    @Test
    @Story("Register a new user")
    @Description("Test Case 11")
    @Severity(SeverityLevel.CRITICAL)
    public void TestUserRegistration() {
        UserRegisterPage userRegisterPage = new UserRegisterPage(driver, wait);
        wait = new WebDriverWait(driver, 20);

        userRegisterPage.navigate();
        userRegisterPage.clickToAllowCookies();
        userRegisterPage.enterUserData("John Doe", "johndoe", "johndoe@foo.bar", "JoHnDoE#1", "JoHnDoE#1");
        userRegisterPage.clickSubmitButton();

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

    @Test
    @Story("Register a new users from file")
    @Description("Test Case 12")
    @Severity(SeverityLevel.NORMAL)
    public void TestUserRegistrationFromFile() {
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
            userRegisterPage.clickSubmitButton();
            Allure.addAttachment("Registration success",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

            userRegisterPage.navigate();
        }
    }

}
