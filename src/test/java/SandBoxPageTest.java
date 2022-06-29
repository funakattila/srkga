import org.junit.jupiter.api.Test;

public class SandBoxPageTest extends BaseTest {

    @Test
    public void titleTest () {
        SandBoxPage sandBoxPage = new SandBoxPage(driver);

        sandBoxPage.navigate();
        sandBoxPage.clickToAllowCookies();

        sandBoxPage.countTitles();


    }


}
