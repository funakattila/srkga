import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;

@Epic("Regression tests")
@Feature("Test edit blog entry")
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

        editBlogEntryPage.navigateAdminLoginPage();
        editBlogEntryPage.adminLogin(TestData.editorUsername, TestData.editorPassword);
        editBlogEntryPage.changeTheTitle(TestData.newTitle);
        Allure.addAttachment("Change title",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        editBlogEntryPage.successChange();

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

        File fileCreated = new File(TestData.saveBlogEntryFilePath);
        Assertions.assertTrue(fileCreated.exists());
    }

    @Test
    @Story("Delete the blog entry")
    @Description("Test Case 20")
    @Severity(SeverityLevel.CRITICAL)
    public void TestDeleteBlogEntry() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver);

        editBlogEntryPage.navigateAdminLoginPage();
        editBlogEntryPage.adminLogin(TestData.editorUsername, TestData.editorPassword);
        editBlogEntryPage.deleteBlogEntry();

        editBlogEntryPage.navigate();
        Allure.addAttachment("Page not found",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        String pageTitle = editBlogEntryPage.getPageTitle();

        Assertions.assertTrue(pageTitle.contains("Page not Found"));
    }

    @Test
    @Story("Log out editor user")
    @Description("Test Case 21")
    @Severity(SeverityLevel.CRITICAL)
    public void TestLogOutEditorUser() {
        EditBlogEntryPage editBlogEntryPage = new EditBlogEntryPage(driver, wait);

        editBlogEntryPage.navigateAdminLoginPage();
        editBlogEntryPage.adminLogin(TestData.editorUsername, TestData.editorPassword);
        Allure.addAttachment("Editor logged in",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        editBlogEntryPage.logOutEditor();
        Allure.addAttachment("Editor logged out",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.logOutMeassageText;
        String actual = driver.findElement(TestData.logOutMessageBox).getText();

        Assertions.assertEquals(expected, actual);
    }

}
