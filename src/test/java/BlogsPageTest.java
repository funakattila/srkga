import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BlogsPageTest extends TestBase {

    /**************************************************
     * Tests of this page
     **************************************************/

    // Check the number of the blog entries
    @Description("Check the number of the blog entries - TC22")
    @Story("Test the blog entries")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void countBlogEntriesTest() {
        BlogsPage blogsPage = new BlogsPage(driver);

        blogsPage.navigate();
        blogsPage.clickToAllowCookies();

        int expected = TestData.countBlogEntires;
        int actual = blogsPage.countAllBlogTitles();

        Assertions.assertEquals(expected, actual);
    }


    // Check the title of the blog entries
    @Description("Check the title of the blog entries - TC23")
    @Story("Test the blog entries")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void titleBlogEntriesTest() {
        BlogsPage blogsPage = new BlogsPage(driver);

        blogsPage.navigate();
        blogsPage.clickToAllowCookies();

        String[] actual = blogsPage.getAllBlogTitlesArray();
        String[] expected = TestData.blogTitlesArray;

        Assertions.assertArrayEquals(expected, actual);
    }



}
