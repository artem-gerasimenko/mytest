import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by a.gerasimenko on 29.09.2015.
 */
public class SearchPage extends BasePage {

    private Logger LOG = Logger.getLogger(SearchPage.class);

    public SearchPage searchPage;


    public SearchPage (WebDriver driver) {
        super(driver);
        searchPage = PageFactory.initElements(this.driver, SearchPage.class);
    }

    public SearchPage open() throws Exception{
        driver.get("https://w.qiwi.com/main.action");
        return this;
    }

    public SearchPage openUrl(String url){
        driver.get(url);
        return this;
    }


    @FindBy( how = How.XPATH, using = "//*[@id='header']/div/div[3]/form/input")
    public WebElement SearchLine;
    public void setSearchLine(String SearchPhrase){
        wait10Seconds.until(ExpectedConditions.visibilityOf(SearchLine));
        SearchLine.sendKeys(SearchPhrase);
    }

    public void clickSearchLine(){
        LOG.info("Нажимаем на строку поиска");
        wait10Seconds.until(ExpectedConditions.visibilityOf(SearchLine));
        SearchLine.click();
    }



    @FindBy(how = How.CLASS_NAME, using = "search_form_sbm")
    private WebElement SearchClick;
    public void clickSearch(){
        LOG.info("Нажимаем на кнопку поиска");
        wait10Seconds.until(ExpectedConditions.visibilityOf(SearchClick));
        SearchClick.click();
    }




    @FindBy( how = How.CLASS_NAME, using = "providersPage")
    private WebElement searchResult;
    private By searchResultLocator = By.className("providersPage");

    public void waitForSearchResult(int timeout){
        LOG.info("Ожидаем появления контейнера с результатом поиска");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchResultLocator));
        wait.until(ExpectedConditions.visibilityOf(searchResult));
    }


    @FindBy(how = How.XPATH, using = "//*[@id='content']/div/div[1]/div[1]/ul/li[3]")
    private WebElement AllResult;
    public void clickAllResult(){
        LOG.info("Нажимаем на кнопку 'Все'");
        wait10Seconds.until(ExpectedConditions.visibilityOf(AllResult));
        AllResult.click();
    }

    public static String httpGet(String urlToRead) throws IOException {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        url = new URL(urlToRead);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = rd.readLine()) != null) {
            result += line;
            rd.close();
        }
        return result;
    }



    @FindBy (how = How.CLASS_NAME, using = "providersPage")
    private WebElement NotResultText;
    public String getNotResultText(){
        wait10Seconds.until(ExpectedConditions.visibilityOf(NotResultText));
        String TextInfo = NotResultText.getText();
        return TextInfo;
    }

    @FindBy(how = How.XPATH, using = "//*[@id='content']/div/div[2]/ul/li[1]/a/div")
    private WebElement Result;
    public void clickResult(){
        LOG.info("Выбираем первую услугу из поиска");
        wait10Seconds.until(ExpectedConditions.visibilityOf(Result));
        Result.click();
    }

    @FindBy (how = How.XPATH, using = "//*[@id='content']/div/div/form/div[1]/div[1]")
    private WebElement TitleServicePage;
    public String getTitleServicePageText(){
        wait10Seconds.until(ExpectedConditions.visibilityOf(TitleServicePage));
        String TextInfo = TitleServicePage.getText();
        return TextInfo;
    }






}

