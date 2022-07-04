import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Epic("Regression tests")
@Feature("Test the blog entries")
public class BlogsPageTest extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/


    @Test
    @Story("Check the number of the blog entries")
    @Description("Test Case 22")
    @Severity(SeverityLevel.CRITICAL)
    public void TestCountBlogEntries() {
        BlogsPage blogsPage = new BlogsPage(driver);

        blogsPage.navigate();
        blogsPage.clickToAllowCookies();

        int expected = TestData.countBlogEntires;
        int actual = blogsPage.countAllBlogTitles();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Story("Check the titles of the blog entries")
    @Description("Test Case 23")
    @Severity(SeverityLevel.CRITICAL)
    public void TestTitlesOfTheBlogEntries() {
        BlogsPage blogsPage = new BlogsPage(driver);

        blogsPage.navigate();
        blogsPage.clickToAllowCookies();

        String[] actual = blogsPage.getAllBlogTitlesArray();
        String[] expected = TestData.blogTitlesArray;

        Assertions.assertArrayEquals(expected, actual);
    }
}
