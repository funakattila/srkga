import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;


public class TestAddNewBlogEntry extends TestBase{

    @Description("User login as editor")
    @Story("Create blog entry")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void loginAsEditorTest() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.navigateAndLogin();

        String expectedUserName = "Jane Doe";
        String actualUserName = driver.findElement(By.xpath("//*[@id=\"admin-user-details\"]//h4")).getText();

        Assertions.assertTrue(actualUserName.startsWith(expectedUserName));
    }

    @Description("Login and create a new blog entry")
    @Story("Create blog entry")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createBlogEntryTest() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.createBlogEntry("Lorem Ipsum");
        driver.navigate().to("http://srkgakezilabda.hu/blog/lorem-ipsum");
        Allure.addAttachment("New blog entry",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.newBlogEntryTitle.toUpperCase();
        String actual = addNewBlogEntryPage.getNewBlogEntryTitle();

        Assertions.assertEquals(expected, actual);
    }

}
