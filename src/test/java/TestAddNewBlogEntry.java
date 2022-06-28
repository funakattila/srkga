import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;


public class TestAddNewBlogEntry extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/

    // User login as editor
    @Description("User login as editor - TC16")
    @Story("Create blog entry")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void loginAsEditorTest() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.navigateAndLogin();

        String expected = TestData.editorFullName;
        String actual = driver.findElement(TestData.editorFullNameH4).getText();

        Assertions.assertTrue(actual.startsWith(expected));
    }

    // Login and create a new blog entry
    @Description("Login and create a new blog entry - TC17")
    @Story("Create blog entry")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void createBlogEntryTest() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.createBlogEntry(TestData.newBlogEntryTitle);

        driver.navigate().to(TestData.newBlogEntryURL);
        Allure.addAttachment("New blog entry",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.newBlogEntryPageTitle;
        String actual = addNewBlogEntryPage.getNewBlogEntryTitle();

        Assertions.assertEquals(expected, actual);

    }

}
