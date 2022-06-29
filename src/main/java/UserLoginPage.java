import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserLoginPage extends BasePage {

    /**************************************************
     * Web elements of the page
     **************************************************/

    // The URL of this site
    private final String url = "http://srkgakezilabda.hu/login";

    // Title of the page
    private final By pageTitle = By.tagName("h1");

    // User name field
    private final By userNameField = By.xpath("//input[@name=\"username\"]");

    // Password field
    private final By userPasswordField = By.xpath("//input[@name=\"password\"]");

    // Remember me checkbox
    private final By rememberMeCheckBox = By.xpath("//input[@name=\"rememberme\"]");

    // Forget password link
    private final By forgetLink = By.xpath("//a[@href=\"/forgot_password\"]");

    // Login button
    private final By loginButton = By.xpath("//button[@type=\"submit\"]");


    // private final By loginStatus = By.xpath("//*[@class=\"login-status\"]");


    /**************************************************
     * Constructors
     **************************************************/

    public UserLoginPage(WebDriver driver) {
        super(driver);
    }
    public UserLoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /**************************************************
     * Methods
     **************************************************/

    @Story("Navigate to the page")
    public void navigate() {
        driver.navigate().to(url);
    }

    @Story("Get the title of the page")
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Story("User login")
    public void loginUser(String username, String password, boolean isRememberMe) {
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(userPasswordField).sendKeys(password);
        if (isRememberMe) {
            driver.findElement(rememberMeCheckBox).click();
        }
        driver.findElement(loginButton).click();
    }

    // -------********* NOT USED YET *********-------
    public void forgottenPasswordLink() {
        driver.findElement(forgetLink).click();
    }

}
