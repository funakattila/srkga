import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

@Epic("Regression tests")
@Feature("Test add new blog entry")
@Link("https://docs.google.com/spreadsheets/d/17usWINlHQc322-yzI4dsEL2Y6qsqkedloQqOz0GRvz8/edit?usp=sharing")
public class AddNewBlogEntryTest extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/


    @Test
    @Story("User login as editor")
    @Description("Test Case 16")
    @Severity(SeverityLevel.CRITICAL)
    public void TestLoginAsEditor() {
        AddNewBlogEntryPage addNewBlogEntryPage = new AddNewBlogEntryPage(driver);

        addNewBlogEntryPage.navigateAndLogin();

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

        addNewBlogEntryPage.createBlogEntry(TestData.newBlogEntryTitle);

        driver.navigate().to(TestData.newBlogEntryURL);
        Allure.addAttachment("New blog entry",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.newBlogEntryPageTitle;
        String actual = addNewBlogEntryPage.getNewBlogEntryTitle();

        Assertions.assertEquals(expected, actual);
    }
}
