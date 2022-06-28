import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    /**************************************************
     * Web Elements of the page
     **************************************************/

    // Allow cookies popup window
    private final By allowCookiesWindow = By.xpath("//*[@id=\"tecart-cookie-banner\"]");

    // Allow cookies button
    private final By allowCookiesButton = By.xpath("//*[@id=\"tecart-cookie-banner\"]//a");


    /**************************************************
     * Constructors
     **************************************************/

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    /**************************************************
     * Methods
     **************************************************/

    // Click the button to allow the cookies method
    @Step("Click the button to allow the cookies method")
    public void clickToAllowCookies() {
        WebElement button = driver.findElement(allowCookiesButton);

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(allowCookiesWindow));
        button.click();
    }

}
