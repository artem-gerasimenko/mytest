import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;



public class QiwiTest {

    private Logger LOG = Logger.getLogger(QiwiTest.class);


    protected WebDriver driver;


    //Запускать ли бразуер при выполеннии теста
    protected boolean startBrowser;
    //Запуск браузера без локального профиля
    protected boolean loadLocalProfile = false;

    @BeforeClass(groups = { "Qw"  })
    public void setUp(){

    }


    @BeforeMethod(alwaysRun = true)
    public void beforeEachTest(Method method) throws Exception {
        //Установка адреса стенда
        BasePage.setBaseUrl("https://w.qiwi.com/main.action");
        //printing test name
        String description = "";
        String[] groups = null;
        Test testAnnotation = (Test) method.getAnnotation(Test.class);
        if (testAnnotation != null){
            description = testAnnotation.description();
            // по умолчанию запускаем браузер перед каждым тестом
            startBrowser = true;
            groups = testAnnotation.groups();
            for (String s : groups){
                LOG.info(s);
                if (s.contains("noWebDriver")){
                    startBrowser = false;
                }
            }
        }

        LOG.info("Выполняется тест: " + method.getName() + ". " + description + ". Id потока: " + Thread.currentThread().getId());

    }





    @AfterMethod(alwaysRun = true)
    public void afterEachTest(ITestResult result, Method method) throws Exception {
        try {
            if (driver != null) driver.quit();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        //LOG.debug("Адресс стенда: " + BasePage.getBaseUrl() );
        LOG.info("Результат теста: " + result + "статус=" + result.getStatus());
    }

    @AfterClass(groups = { "Qw", })
    public void close() {

    }


}
