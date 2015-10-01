import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;


public class QiwiTest {

    private Logger LOG = Logger.getLogger(QiwiTest.class);


    protected WebDriver driver;


    //Запускать ли бразуер при выполеннии теста
    //protected boolean startBrowser;
    //Запуск браузера без локального профиля
    protected boolean loadLocalProfile = false;


    @BeforeClass(groups = {"Qw"})
    public void setUp() {
        driver = new FirefoxDriver();
       // System.setProperty("webdriver.firefox.profile", "anonymous2798472807171484480webdriver-profile");
    }



    @AfterMethod(alwaysRun = true)
    public void afterEachTest(ITestResult result, Method method) throws Exception {

        //LOG.debug("Адресс стенда: " + BasePage.getBaseUrl() );
        LOG.info("Результат теста: " + result + "статус=" + result.getStatus());
    }

    @AfterClass(groups = { "Qw", })
    public void close() {
        try {
            if (driver != null) driver.quit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }


}
