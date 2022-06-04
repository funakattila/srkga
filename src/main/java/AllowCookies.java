import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllowCookies extends PageBase{

    //Allow cookies popup window
    private final By allowCookiesWindow = By.xpath("//*[@id=\"tecart-cookie-banner\"]");
    //Allow cookies button
    private final By allowCookiesButton = By.xpath("//*[@id=\"tecart-cookie-banner\"]//a");

    public AllowCookies(WebDriver driver) {
        super(driver);
    }

    public void clickToAllowCookies() {
        WebElement button = driver.findElement(allowCookiesButton);

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(allowCookiesWindow));
        button.click();
    }


}
