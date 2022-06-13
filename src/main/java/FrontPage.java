import org.openqa.selenium.WebDriver;

public class FrontPage extends BasePage {

    private final String url = "http://srkgakezilabda.hu";

    public FrontPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(url);
    }
}
