import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class UserLoginPage extends BasePage {

    private final String url = "http://srkgakezilabda.hu/login";
    private final By pageTitle = By.tagName("h1");
    private final By userNameField = By.xpath("//input[@name=\"username\"]");
    private final By userPasswordField = By.xpath("//input[@name=\"password\"]");
    private final By rememberMeCheckBox = By.xpath("//input[@name=\"rememberme\"]");
    private final By forgetLink = By.xpath("//a[@href=\"/forgot_password\"]");
    private final By loginButton = By.xpath("//button[@type=\"submit\"]");

    private final By loginStatus = By.xpath("//*[@class=\"login-status\"]");


    public UserLoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void loginUser(String username, String password, boolean isRememberMe) {
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(userPasswordField).sendKeys(password);
        if (isRememberMe) {
            driver.findElement(rememberMeCheckBox).click();
        }
        driver.findElement(loginButton).click();
    }

    public void forgottenPasswordLink() {
        driver.findElement(forgetLink).click();
    }

}