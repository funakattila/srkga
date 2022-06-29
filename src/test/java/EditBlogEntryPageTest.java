import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Epic("Regression tests")
@Feature("Test edit blog entry")
@Link("https://docs.google.com/spreadsheets/d/17usWINlHQc322-yzI4dsEL2Y6qsqkedloQqOz0GRvz8/edit?usp=sharing")
public class EditBlogEntryPageTest extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/


    @Test
    @Story("Change the title")
    @Description("Test Case 18")
    @Severity(SeverityLevel.CRITICAL)
    public void TestChangeBlogTitle() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver);

        editBlogEntryPage.navigateAndLogin();
        editBlogEntryPage.changeTheTitle(TestData.newTitle);

        String text1 = editBlogEntryPage.headerText();
        String text2 = TestData.newTitle;

        boolean isStartWith = text1.startsWith(text2);

        Assertions.assertTrue(isStartWith);
    }

    @Test
    @Story("Save the title and the date of the blog entry")
    @Description("Test Case 19")
    @Severity(SeverityLevel.NORMAL)
    public void TestSaveBlogTitleAndDate() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver);

        editBlogEntryPage.navigate();
        editBlogEntryPage.createFile();
        editBlogEntryPage.saveToFile();

        //Assertions
    }

    @Test
    @Story("Delete the blog entry")
    @Description("Test Case 20")
    @Severity(SeverityLevel.CRITICAL)
    public void TestDeleteBlogEntry() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver);

        editBlogEntryPage.navigateAndLogin();
        editBlogEntryPage.deleteBlogEntry();

        //Assertions
    }

    @Test
    @Story("Log out editor user")
    @Description("Test Case 21")
    @Severity(SeverityLevel.CRITICAL)
    public void TestLogOutEditorUser() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver, wait);

        editBlogEntryPage.navigateAndLogin();
        editBlogEntryPage.logOutEditor();

        String expected = TestData.logOutMeassageText;
        String actual = driver.findElement(TestData.logOutMessageBox).getText();

        Assertions.assertEquals(expected, actual);
    }


}
