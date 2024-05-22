package selenium.training.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import selenium.training.enums.BrowserType;
import selenium.training.pages.CustomerInfoPageDK;
import selenium.training.pages.LoginPageDK;
import selenium.training.pages.RegisterPageDK;

import java.time.Duration;

public class Driver {

    public static WebDriver getDriver() {
        return driver;
    }

    private static WebDriver initDriver(BrowserType type) {

        switch (type) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\Dell\\Desktop\\geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:\\Program Files\\Mozilla Firefox\\Firefox.exe");
                driver = new FirefoxDriver(options);
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(optionsChrome);
                break;
        }

        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static String removeParanthesis(String text) {
        text = text.replace("(", "");
        text = text.replace(")", "");
        return text;
    }

//    public static void loginOrRegister(int mode) {
//        LoginPageDK loginPageDK = new LoginPageDK();
//        RegisterPageDK registerPageDK;
//        CustomerInfoPageDK customerInfoPage;
//        loginPageDK.navigateToHomePage();
//        loginPageDK.clickLoginButton();
//
//        loginPageDK.login(Credentials.EMAIL, Credentials.PASSWORD);
//
//        loginPageDK.submit();
//
//        //Do te thote qe nuk nuk eshte bere me sukses login me kredenciale
//        //useri nuk ekziston
//        try {
//            if (loginPageDK.errorMessage.isDisplayed() && mode !=2) {
//                registerPageDK = new RegisterPageDK();
//                registerPageDK.navigateToHomePage();
//                registerPageDK.clickRegisterButton();
//
//                registerPageDK.register(
//                        Credentials.FIRSTNAME,
//                        Credentials.LASTNAME,
//                        Credentials.EMAIL,
//                        Credentials.COMPANY,
//                        Credentials.PASSWORD,
//                        Credentials.DAY,
//                        Credentials.MONTH,
//                        Credentials.YEAR
//                );
//                if (mode != 3) registerPageDK.logout();
//            }
//        } catch (NoSuchElementException e) {
//            //Dy modalitetete:
//            // 1 - Nqs duam qe te perdorim account ekzistues
//            // 2 - Nqs duam qe ta shkaterrojme kete account
//            // 3 - Me qe eshte bere log in, te rrije ashtu sic eshte te mos bej logout
//            switch (mode) {
//                case 1: {
//                    loginPageDK.logout();
//                    break;
//                }
//                case 2: {
//                    customerInfoPage = new CustomerInfoPageDK();
//                    customerInfoPage.navigateToCustomerInfoPage();
//                    customerInfoPage.changeEmail();
//                    customerInfoPage.saveChanges();
//                    customerInfoPage.logout();
//                }
//                case 3: {
//                    break;
//                }
//            }
//        }
//}

    public static void registerIfNeeded(String action) {
        LoginPageDK loginPageDK;
        RegisterPageDK registerPageDK = new RegisterPageDK();
        CustomerInfoPageDK customerInfoPage;
        NoSuchElementException noSuchElementException = null;

        registerPageDK.navigateToHomePage();
        registerPageDK.clickRegisterButton();


        registerPageDK.register(
                Credentials.FIRSTNAME,
                Credentials.LASTNAME,
                Credentials.EMAIL,
                Credentials.COMPANY,
                Credentials.PASSWORD,
                Credentials.DAY,
                Credentials.MONTH,
                Credentials.YEAR);
        //Tentojme te rregjistrohemi
        //Nqs del mesazh, pra ekziston account, do te thote qe errorMessage si element incializohet pra nuk do te hedhet exception
        //pra nuk do te futet ne try catch, pra noSuchElementException ---ESHTE--- null
        //Nqs nuk del mesazh, pra nuk ekziston account, do te thote qe errorMessage si element nuk incializohet pra do te hedhet exception
        //pra do te futet ne try catch, pra noSuchElementException ---NUK ESHTE--- null
        try {
            registerPageDK.errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            noSuchElementException = e;
        }

        switch (action) {
            case "register": {

                if (noSuchElementException == null) {
                    executeLogin();
                }
                customerInfoPage = new CustomerInfoPageDK();
                customerInfoPage.navigateToCustomerInfoPage();
                customerInfoPage.changeEmail();
                customerInfoPage.saveChanges();
                customerInfoPage.logout();

                break;
            }
            case "login": {
                if (noSuchElementException != null)  //Nuk ka account aktiv, por u krijua, dhe tani i bejme logout.
                {
                    registerPageDK.logout();
                }
                break;
            }
            case "continue": {
                if (noSuchElementException != null)
                    break; //Nuk ka account aktiv, por u krijua dhe vazhdoje hapat tjere.
                else {     //Ka account ekzsitues, prandaj logoje qe te jete gati per testin.
                    executeLogin();
                }
                break;
            }
        }


    }

    private static void executeLogin() {
        LoginPageDK loginPageDK;
        loginPageDK = new LoginPageDK();
        loginPageDK.navigateToHomePage();
        loginPageDK.clickLoginButton();
        loginPageDK.login(Credentials.EMAIL, Credentials.PASSWORD);
        loginPageDK.submit();
    }

    private static WebDriver driver = initDriver(BrowserType.CHROME);

    public static void manageImplicitTimeout(long seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }


}

