import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;

public class TestFrontPage extends TestBase{

    /**************************************************
     * Tests of this page
     **************************************************/

    // Check the url of the front page
    @Description("Check the url of the front page")
    @Story("Test the front page")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    public void checkSiteURL() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();

        String expected = TestData.mainURL;
        String actual = driver.getCurrentUrl();
        Assertions.assertEquals(expected, actual);
    }

    // Test allow cookies
    @Description("Allow cookies")
    @Story("Test the front page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void allowCookies() {
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

    // Find the logo on the main page
    @Description("Find the logo on the main page")
    @Story("Test the front page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void findLogo() {
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

    // Check the number of the latest blog entries
    @Description("Check the number of the latest blog entries")
    @Story("Test the front page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void numberOfBlogEntries() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        int expected = TestData.numberOfBlogEntries;
        int actual = frontPage.countBlogEntries();

        Assertions.assertEquals(expected, actual);
    }

    // Check the titles of the blog entries
    @Description("Check the titles of the blog entries")
    @Story("Test the front page")
    @Severity(SeverityLevel.CRITICAL)
    @Disabled
    @Test
    public void checkTitles() {
        FrontPage frontPage = new FrontPage(driver);
        frontPage.navigate();
        frontPage.clickToAllowCookies();

        String[] expected = TestData.latestBlogEntryTitles;
        String[] actual = frontPage.collectBlogEntryTitles();

        Assertions.assertArrayEquals(expected, actual);
    }

    // Check all the team photos are present in the carousel
    @Description("Check all the team photos are present in the carousel")
    @Story("Test the front page")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void isAllImagePresent() {
        FrontPage frontPage = new FrontPage(driver);

        frontPage.navigate();
        frontPage.clickToAllowCookies();

        Assertions.assertTrue(frontPage.isAllImagePresent());
    }

}


