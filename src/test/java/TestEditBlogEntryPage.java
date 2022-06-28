import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEditBlogEntryPage extends TestBase {

    /**************************************************
     * Tests of this page
     **************************************************/


    // Change the title of the blog entry
    @Description("Change the title - TC18")
    @Story("Edit blog entry")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void changeBlogTitleTest() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver);

        editBlogEntryPage.navigateAndLogin();
        editBlogEntryPage.changeTheTitle(TestData.newTitle);

        String text1 = editBlogEntryPage.headerText();
        String text2 = TestData.newTitle;

        boolean isStartWith = text1.startsWith(text2);

        Assertions.assertTrue(isStartWith);
    }


    // Save the title and the date of the blog entry
    @Description("Save the title and the date of the blog entry - TC19")
    @Story("Edit blog entry")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void saveBlogTitleAndDateTest() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver);

        editBlogEntryPage.navigate();
        editBlogEntryPage.createFile();
        editBlogEntryPage.saveToFile();

        //Assertions
    }

    // Delete the blog entry
    @Description("Delete the blog entry - TC20")
    @Story("Edit blog entry")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void deleteBlogEntryTest() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver);

        editBlogEntryPage.navigateAndLogin();
        editBlogEntryPage.deleteBlogEntry();

        //Assertions
    }


    // Log out editor user
    @Description("Log out editor user - TC21")
    @Story("Edit blog entry")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void logOutEditorUserTest() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver, wait);

        editBlogEntryPage.navigateAndLogin();
        editBlogEntryPage.logOutEditor();

        String expected = TestData.logOutMeassageText;
        String actual = driver.findElement(TestData.logOutMessageBox).getText();

        Assertions.assertEquals(expected, actual);
    }


}
