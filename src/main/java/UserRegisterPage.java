import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserRegisterPage extends PageBase {

    private final String url = "http://srkgakezilabda.hu/user_register/";

    //Variables to register
    private final String fullName = "John Doe";
    private final String userName = "johndoe";
    private final String email = "johndoe@foo.bar";
    private final String password = "JoHnDoE#1";

    //WebElements
    private final By fullNameField = By.xpath("//input[@name=\"data[fullname]\"]");
    private final By userNameField = By.xpath("//input[@name=\"data[username]\"]");
    private final By emailField = By.xpath("//input[@name=\"data[email]\"]");
    private final By password1Field = By.xpath("//input[@name=\"data[password1]\"]");
    private final By password2Field = By.xpath("//input[@name=\"data[password2]\"]");
    private final By submitButton = By.xpath("//button[@type=\"submit\"]");
    private final By resetButton = By.xpath("//button[@type=\"reset\"]");


    public UserRegisterPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(url);
    }

    public void enterUserDatas(String fullName, String username, String email, String password1, String password2) {
        driver.findElement(fullNameField).sendKeys(fullName);
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(password1Field).sendKeys(password1);
        driver.findElement(password2Field).sendKeys(password2);
    }

    public void pressResetButton() {
        driver.findElement(resetButton).click();
    }

    public void pressSubmitButton() {
        driver.findElement(submitButton).click();
    }






}
