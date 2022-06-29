import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;

@Epic("Regression tests")
@Feature("Test the front page of the site")
@Link("https://docs.google.com/spreadsheets/d/17usWINlHQc322-yzI4dsEL2Y6qsqkedloQqOz0GRvz8/edit?usp=sharing")
public class FrontPageTest extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/

    @Test
    @Story("Check the url of the front page")
    @Description("Test Case 01")
    @Severity(SeverityLevel.TRIVIAL)
    public void TestSiteURL() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();

        String expected = TestData.mainURL;
        String actual = driver.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Story("Find the logo on the main page")
    @Description("Test Case 02")
    @Severity(SeverityLevel.NORMAL)
    public void TestFindLogo() {
        FrontPage frontPage = new FrontPage(driver, wait);
        wait = new WebDriverWait(driver, 30);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        String expected = TestData.logoPath;
        String actual = frontPage.findLogo();

        wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("logo"))));
        Allure.addAttachment("The logo on the main page",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Story("Check the number of the latest blog entries")
    @Description("Test Case 03")
    @Severity(SeverityLevel.CRITICAL)
    public void TestNumberOfBlogEntries() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        int expected = TestData.numberOfBlogEntries;
        int actual = frontPage.countBlogEntries();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Story("Check the titles of the blog entries")
    @Description("Test Case 04")
    @Severity(SeverityLevel.CRITICAL)
    public void TestTitles() {
        FrontPage frontPage = new FrontPage(driver);
        frontPage.navigate();
        frontPage.clickToAllowCookies();

        String[] expected = TestData.latestBlogEntryTitles;
        String[] actual = frontPage.collectBlogEntryTitles();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Disabled
    @Test
    @Story("Check the read more button")
    @Description("Test Case 05")
    @Severity(SeverityLevel.CRITICAL)
    public void TestIsReadMoreWorks() {

    }

    @Test
    @Story("Check all the team photos are present in the carousel")
    @Description("Test Case 06")
    @Severity(SeverityLevel.CRITICAL)
    public void TestIsAllImagePresent() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        Assertions.assertTrue(frontPage.isAllImagePresent());
    }

    @Test
    @Story("Allow cookies")
    @Description("Test Case 07")
    @Severity(SeverityLevel.NORMAL)
    public void TestAllowCookies() {
        FrontPage frontPage = new FrontPage(driver, wait);
        wait = new WebDriverWait(driver, 30);

        frontPage.navigate();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label=\"cookieconsent\"]")));
        Allure.addAttachment("Before accept cookies",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        frontPage.clickToAllowCookies();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@aria-label=\"cookieconsent\"]")));
        Allure.addAttachment("After accept cookies",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertFalse(frontPage.isCookieBannerVisible());

    }

}


