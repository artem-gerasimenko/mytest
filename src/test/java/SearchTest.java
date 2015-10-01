import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;


public class SearchTest extends QiwiTest {

    private Logger LOG = Logger.getLogger(SearchTest.class);



    @Test(description = "Test1. Проверка поиска по заданному слову"
            , suiteName = "Qw")
    public void Test1() throws Exception {
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchPage.open();
        searchPage.clickSearchLine();
        searchPage.setSearchLine("Билайн");
        searchPage.clickSearch();
        searchPage.waitForSearchResult(50);  // ожидаем пока появится блок с результатами поиска.
        searchPage.clickAllResult();
        List<WebElement> elements = new ArrayList<WebElement>();
        Thread.sleep(6000);
        elements = driver.findElements(By.xpath("//a[descendant::div[contains(@class,'frame')]]")); // Получаем все объекты из результатов поиска
        List<String> texts = new ArrayList<String>();
        for (WebElement elem : elements) {
            texts.add(elem.getText());
        }

        assertThat(texts.contains("Билайн")||texts.contains("Beeline"), is(true)); // Проверяем что заголовки содержать заданный текст

    }

    @Test(description = "Test2. Проверка на обработку xss" // В данном тесте проверяется текущая реализация
            , suiteName = "Qw")
    public void Test2() throws Exception {
        String exception = "";
        try {
            System.out.println(SearchPage.httpGet("https://qiwi.com/search/results.action?searchPhrase=%3Cscript%3Ealert%21%221%22%3C%2Fscript%3E"));

        } catch (Exception e) {
            exception = e.getMessage();
        }
        System.out.println("Eq = " + exception);
        assertThat("HTTP STATUS is 403", exception, containsString("Server returned HTTP response code: 403 for URL: https://qiwi.com/search/"));
        System.out.println("Необходимо завести дефект!");
    }



   @Test(description = "Test4. Проверка поиска при вводе спец. символов"
            , suiteName = "Qw")
    public void Test4() throws Exception {
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchPage.open();
        searchPage.clickSearchLine();
        searchPage.setSearchLine("'&&&&&&&&^^'");
        searchPage.clickSearch();
        searchPage.waitForSearchResult(50);
        String NotResult = searchPage.getNotResultText();
        assertThat("Проверяем пустой поиск",NotResult, equalTo("Ничего не найдено"));

    }

    @Test(description = "Test5. Проверка поиска по заданному слову транслитом"
            , suiteName = "Qw")
    public void Test5() throws Exception {
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchPage.open();
        searchPage.clickSearchLine();
        searchPage.setSearchLine("akado");
        searchPage.clickSearch();
        searchPage.waitForSearchResult(50);  // ожидаем пока появится блок с результатами поиска.
        searchPage.clickAllResult();
        List<WebElement> elements = new ArrayList();
        List<String> texts = new ArrayList();
        Thread.sleep(6000);
        elements = driver.findElements(By.xpath("//a[descendant::div[contains(@class,'frame')]]")); // Получаем все объекты из результатов поиска
        for (WebElement elem : elements) {
            texts.add(elem.getText());
        }

        assertThat(texts.contains("АКАДО"), is(true)); // Проверяем что заголовки содержать заданный текст

    }

    @Test(description = "Test6. Проверка перехода на страницу услуги + поиск ссылки"
            , suiteName = "Qw")
    public void Test6() throws Exception {
        SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);
        searchPage.open();
        searchPage.clickSearchLine();
        searchPage.setSearchLine("http://www.megafon.ru/");
        searchPage.clickSearch();
        searchPage.waitForSearchResult(50);
        searchPage.clickResult();
        String TitleServicePageText = searchPage.getTitleServicePageText();
        assertThat("Проверяем что присутствует ссылка", TitleServicePageText, containsString("http://www.megafon.ru/"));


    }



}
