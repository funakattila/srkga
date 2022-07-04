import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

@Epic("Regression tests")
@Feature("Test team page")
public class FU14TeamPageTest extends BaseTest {

    /**************************************************
     * Tests of this page
     **************************************************/


    @Test
    @Story("Test FU14 team members")
    @Description("Test Case 24")
    @Severity(SeverityLevel.NORMAL)
    public void TestTeamMembers() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        String[] expected = TestData.teamMembers;
        String[] actual = fu14TeamPage.teamMembers();
        Allure.addAttachment("FU14 team",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    @Story("Test FU14 team members number")
    @Description("Test Case 25")
    @Severity(SeverityLevel.NORMAL)
    public void TestNumberOfTeamMembers() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        int expected = TestData.teamMembers.length;
        int actual = fu14TeamPage.numberOfTeamMembers(TestData.teamMembers);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Story("Test FU14 team about us text")
    @Description("Test Case 26")
    @Severity(SeverityLevel.NORMAL)
    public void TestFu14AboutTeam() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        String expected = fu14TeamPage.readFileAboutTeam("src/files/about_fu14.txt");
        String actual = fu14TeamPage.getAboutTeamText();

        Assertions.assertEquals(expected, actual);
    }
}
