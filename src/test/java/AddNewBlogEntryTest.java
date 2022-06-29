import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

@Epic("Regression tests")
@Feature("Test add new blog entry")
public class AddNewBlogEntryTest extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/


    @Test
    @Story("User login as editor")
    @Description("Test Case 16")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginAsEditor() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.navigate();
        Allure.addAttachment("Admin user login page",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        addNewBlogEntryPage.adminLogin(TestData.editorUsername, TestData.editorPassword);
        Allure.addAttachment("After login",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.editorFullName;
        String actual = driver.findElement(TestData.editorFullNameH4).getText();

        Assertions.assertTrue(actual.startsWith(expected));
    }

    @Test
    @Story("Login and create a new blog entry")
    @Description("Test Case 17")
    @Severity(SeverityLevel.CRITICAL)
    public void TestCreateBlogEntry() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.navigate();
        Allure.addAttachment("Admin user login page",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        addNewBlogEntryPage.adminLogin(TestData.editorUsername, TestData.editorPassword);
        Allure.addAttachment("After login",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        addNewBlogEntryPage.createBlogEntryPage(TestData.newBlogEntryTitle);
        Allure.addAttachment("Create page",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        addNewBlogEntryPage.createBlogEntryContent(TestData.text);
        Allure.addAttachment("Add content",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        driver.navigate().to(TestData.newBlogEntryURL);
        Allure.addAttachment("New blog entry",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.newBlogEntryPageTitle;
        String actual = addNewBlogEntryPage.getNewBlogEntryTitle();

        Assertions.assertEquals(expected, actual);
    }
}
