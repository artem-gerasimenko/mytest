import org.openqa.selenium.By;

public class BasePage {
    private static org.apache.log4j.Logger LOG;
    protected org.openqa.selenium.WebDriver driver;
    protected org.openqa.selenium.support.ui.WebDriverWait wait10Seconds;
    protected org.openqa.selenium.support.ui.WebDriverWait wait30Seconds;
    protected org.openqa.selenium.support.ui.WebDriverWait wait5Seconds;
    protected static java.lang.String baseUrl;
    org.openqa.selenium.By iFrameLoaderLocator;

    public BasePage(org.openqa.selenium.WebDriver driver) { /* compiled code */ }

    public void isElementPresent(By by) { /* compiled code */ }

    public void switchToFrame(java.lang.String frame) { /* compiled code */ }

    public void switchToFrame(org.openqa.selenium.WebElement frameElement) { /* compiled code */ }

    public void switchToFrame(int frame) { /* compiled code */ }

    public static void setBaseUrl(java.lang.String url) { /* compiled code */ }

    public static void getBaseUrl() throws java.lang.Exception { /* compiled code */ }

    public void waitFrameLoaderToDisappear(int seconds) { /* compiled code */ }

    public void switchToDefaultContent() { /* compiled code */ }

    public void closeDriver() { /* compiled code */ }

    public void getCurrentUrl() { /* compiled code */ }
}