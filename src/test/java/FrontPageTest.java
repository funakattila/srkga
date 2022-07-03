import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;

@Epic("Regression tests")
@Feature("Test the front page of the site")
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
        Allure.addAttachment("Open the front page",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

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

        wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("logo"))));
        Allure.addAttachment("The logo on the main page",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String expected = TestData.logoPath;
        String actual = frontPage.findLogo();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Story("Check the number of the latest blog entries")
    @Description("Test Case 03")
    @Severity(SeverityLevel.CRITICAL)
    public void TestNumberOfBlogEntries() {
        FrontPage frontPage = new FrontPage(driver);
        Actions action = new Actions(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();
        action.moveToElement(driver.findElement(By.xpath("//*[@class=\"card\"]"))).build().perform();
        Allure.addAttachment("Latest blog entries",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

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
        Actions action = new Actions(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();
        action.moveToElement(driver.findElement(By.xpath("//*[@class=\"card\"]//h3"))).build().perform();
        Allure.addAttachment("Latest blog entries",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        String[] expected = TestData.latestBlogEntryTitles;
        String[] actual = frontPage.collectBlogEntryTitles();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @Disabled
    @Story("Check the read more button")
    @Description("Test Case 05")
    @Severity(SeverityLevel.CRITICAL)
    public void TestIsReadMoreWorks() {
        // Not created yet
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

        frontPage.navigate();
        frontPage.clickToAllowCookies();
        Allure.addAttachment("After accept cookies",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertFalse(frontPage.isCookieBannerVisible());
    }
}


