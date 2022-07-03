import org.openqa.selenium.By;

import java.util.List;

public class TestData {

    // TestFrontPage
    public final static String mainURL = "http://srkgakezilabda.hu/"; //
    public final static String logoPath = "http://srkgakezilabda.hu/home/_jumbotron/logo.png";  // path of the logo
    public final static int numberOfBlogEntries = 6;    // number of latest blog entries on the main page

    public final static String[] latestBlogEntryTitles = {"Lorem Ipsum", "Bajnoki fordulók újabb hétvégéje",
            "Legkisebbjeink a " +
            "pályán", "Újra a pályán", "Hétvégi játékmaraton a Wáberer Sportcsarnokban", "Megkezdtük a tavaszi " +
            "szezont", "Adó 1% felajánlása"}; // titles of the latest blog entries on the main page


    // TestUserRegister
    public final static String registerUserPageTitle = "Register User | SRKGA - Kézilabda";
    public final static String registrationSuccessText = "Thank you for registering.";

    // TestUserLogin
    public final static String loginUserPageTitle = "Login | SRKGA - Kézilabda";
    public final static String userFullName = "John Doe";

    // TestAddNewBlogEntry
    public final static String editorFullName = "Jane Doe";
    public final static By editorFullNameH4 = By.xpath("//*[@id=\"admin-user-details\"]//h4");
    public final static String editorUsername = "janedoe";
    public final static String editorPassword = "JaNeDoE#1";
    public final static String newBlogEntryTitle = "Lorem Ipsum";
    public final static String newBlogEntryURL = "http://srkgakezilabda.hu/blog/lorem-ipsum";

    public final static String newBlogEntryPageTitle = "Lorem Ipsum | SRKGA - Kézilabda";

    public final static String text = """
                                ## Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                            
                                Cras aliquam **ullamcorper** felis non aliquam. Vivamus fermentum porta massa, ultrices vestibulum metus sollicitudin in. Vivamus ultricies porttitor semper. Pellentesque quis orci dolor. Nunc in libero _efficitur_, rutrum nibh in, dictum nibh. Etiam a sollicitudin eros. Nam consectetur pharetra dolor a efficitur. Praesent eu ex vitae _lorem_ egestas eleifend viverra a lectus.
                                            
                                * Pellentesque venenatis
                                Tortor in dolor hendrerit
                                Vel interdum
                                Sapien suscipit
                                """;

    // TestEditBlogEntryPage
    public final static String newTitle = "New Blog Title";
    public final static String saveBlogEntryFilePath = "src/files/saved_blog_entry.txt";

    public final static By logOutMessageBox = By.xpath("//*[@class=\"info alert\"]");
    public final static String logOutMeassageText = "You have been logged out";


    // TestBlogsPage
    public final static int countBlogEntires = 27; //in allure report the dummy blog entry won't clear until this test
    public final static String[] blogTitlesArray = {"LOREM IPSUM", "BAJNOKI FORDULÓK ÚJABB HÉTVÉGÉJE",
            "LEGKISEBBJEINK A PÁLYÁN", "ÚJRA A PÁLYÁN", "HÉTVÉGI JÁTÉKMARATON A WÁBERER SPORTCSARNOKBAN", "MEGKEZDTÜK" +
            " A TAVASZI SZEZONT", "ADÓ 1% FELAJÁNLÁSA", "SZIVACSKÉZILABDA FELKÉSZÜLÉSI TORNA MEZŐKÖVESDEN", "EGY HÉTVÉGE - HAT MÉRKŐZÉS", "KÖSZ" +
            "ÖNJÜK ÖREGDIÁKJAINK TÁMOGATÁSÁT!", "NOVEMBERI VERSENYHÉTVÉGE", "FORDULÓ OKTÓBER 16-17", "LEGYÉL TE IS " +
            "KÉZILABDÁS", "EDZŐTÁBOR GYULAHÁZÁN", "INDUL AZ ÚJ SZEZON", "III. SRKGA KÉZILABDA TÁBOR - BESZÁMOLÓ",
            "III.SRKGA KÉZILABDA TÁBOR", "BRAVÚROS SZEREPLÉS HAJDÚNÁNÁSON", "ORSZÁGOS GYERMEK KUPA MEGMÉRETTETÉS A " +
            "LU11-BEN", "ORSZÁGOS GYERMEK KUPA MÉRKŐZÉSEK A WÁBERER CSARNOKBAN", "UTÁNPÓTLÁS FORDULÓK AZ ORSZÁGOS " +
            "KISISKOLÁS KUPÁN", "FEJLŐDNI ÉS MINDIG SZERETNI A JÁTÉKOT", "AZ ELŐZŐ SZEZONHOZ KÉPEST JELENTŐSEN LÉPTEK" +
            " ELŐRE TANÍTVÁNYAINK", "BALMAZÚJVÁROSI SZEZONZÁRÓ A LU13-BAN", "EDZŐTOVÁBBKÉPZÉSEK RENDJE", "BAJNOKSÁGOK" +
            " BEFEJEZÉSE", "TÁJÉKOZTATÁS AZ EDZŐMÉRKŐZÉSEKRŐL"};


    // TestFU14TeamPage
    public final static String name = "FU14";
    public final static String trainer = "Pánczél Zoltán";
    public final static String[] teamMembers = {"Árokszállási Péter Endre", "Csallóközi Ákos", "Csőre Bence Álmos", "Ferkó Zsombor " +
            "Máté", "Funák Zétény Nemere", "Funák Zolta Kadosa", "Hanuder Dániel", "Hegedüs Bottyán", "Király Csaba " +
            "Milán", "Oláh-Hutka Ábel Bendegúz", "Pisztró Tamás", "Sajószegi Tamás", "Takács Máté"};

}
