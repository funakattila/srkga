import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class TestFU14TeamPage extends TestBase{

    @Description("Test FU14 team members")
    @Story("Test team page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void teamMembersTest() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        String[] expected = TestData.teamMembers;
        String[] actual = fu14TeamPage.teamMembers();
        Allure.addAttachment("FU14 team",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        Assertions.assertArrayEquals(expected, actual);
    }

    @Description("Test FU14 team members number")
    @Story("Test team page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void numberOfTeamMembersTest() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        int expected = TestData.teamMembers.length;
        int actual = fu14TeamPage.numberOfTeamMembers(TestData.teamMembers);

        Assertions.assertEquals(expected, actual);
    }

    @Description("Test FU14 team about us text")
    @Story("Test team page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void fu14AboutTeamTest() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();

        String expected = fu14TeamPage.readFileAboutTeam("src/files/about_fu14.txt");
        String actual = fu14TeamPage.getAboutTeamText();

        Assertions.assertEquals(expected, actual);
    }

    @Description("Save FU14 team data to txt")
    @Story("Test team page")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void saveToFileTest() {
        Fu14TeamPage fu14TeamPage = new Fu14TeamPage(driver);

        fu14TeamPage.navigate();
        fu14TeamPage.clickToAllowCookies();
        fu14TeamPage.createFile();
        fu14TeamPage.writeToFile();
    }
}
