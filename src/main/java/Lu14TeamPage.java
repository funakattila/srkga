import org.openqa.selenium.WebDriver;

public class Lu14TeamPage extends BasePage {

    private final String url = "http://srkgakezilabda.hu/csapataink/lu-14";
    private final String name = "LU14";
    private final String trainer = "Pánczél-Héjjas Tímea";
    private final String[] teamMembers = {"Asztalos Zoé", "Braun Boróka", "Braun Hanna", "Csorba Anna", "Károlyfalvi " +
            "Angelika Leila", "Kiss Lilien", "Kistóth Lili", "Nagy Lara", "Polgár Noémi", "Rák Dorottya", "Sárkány " +
            "Brigitta Petra", "Siska Zsófia Sára", "Seres Kinga", "Szabó Annamária", "Vágó Liliána", "Varkonda Zille"
            , "Vinkler Violetta Laura"};
    private final String baseImgUrl = "/user/pages/02.csapataink/12.lu-14/";


    public Lu14TeamPage(WebDriver driver) {
        super(driver);
    }
}
