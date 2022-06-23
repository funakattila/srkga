import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    //Allow cookies popup window
    private final By allowCookiesWindow = By.xpath("//*[@id=\"tecart-cookie-banner\"]");
    //Allow cookies button
    private final By allowCookiesButton = By.xpath("//*[@id=\"tecart-cookie-banner\"]//a");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void clickToAllowCookies() {
        WebElement button = driver.findElement(allowCookiesButton);

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(allowCookiesWindow));
        button.click();
    }

}
