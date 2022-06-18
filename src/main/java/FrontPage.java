import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrontPage extends BasePage {

    private final String url = "http://srkgakezilabda.hu";
    private final By logoImage = By.id("logo");

    public FrontPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public String findLogo() {
        String imgPath = driver.findElement(logoImage).getAttribute("src");
        return imgPath;
    }


}
