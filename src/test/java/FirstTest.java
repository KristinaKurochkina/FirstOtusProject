import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(FirstTest.class);
    private final String login = "fivod55542@vingood.com";
    private final String pas = "Gipunu13!";

    @Test
    public void duckduckgoTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        driver = new ChromeDriver(options);

//  Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com/");
//  В поисковую строку ввести ОТУС
        driver.findElement(By.cssSelector("#search_form_input_homepage")).sendKeys("ОТУС");
//      Нажать кнопку поиска
        driver.findElement(By.cssSelector("#search_button_homepage")).submit();
//  Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение современным ...
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", driver.findElement(By.xpath("//div[@class='results js-results']/descendant::div[1]/descendant::span[@class='EKtkFWMYpwzMKOYr0GYm LQVY1Jpkk8nyJ6HBWKAk']")).getText());

    }

    @Test
    public void pictureTest() throws InterruptedException {
//    Открыть Chrome в режиме киоска
//    Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver = new ChromeDriver();
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver.manage().window().fullscreen();
        //    Нажать на любую картинку
        WebElement picture = driver.findElement(By.cssSelector(".content-overlay"));
        picture.click();
        Thread.sleep(1000);
//    Проверить что картинка открылась в модальном окне
        Assertions.assertTrue(driver.findElement(By.cssSelector(".pp_pic_holder")).isDisplayed());
    }

    @Test
    public void otusTest() throws InterruptedException {
//        Открыть Chrome в режиме полного экрана
//        Перейти на https://otus.ru
        driver = new ChromeDriver();
        driver.get("https://otus.ru");
        driver.manage().window().maximize();
        Thread.sleep(1000);
//        Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)

        loginInOtus();
//        Вывести в лог все cookie

        log.info(driver.manage().getCookies());
    }

    public void loginInOtus() throws InterruptedException {
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@type='text' and @placeholder = 'Электронная почта']")).sendKeys(login);
        driver.findElement(By.xpath("//input[ @name='password' and @placeholder = 'Введите пароль']")).sendKeys(pas);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();

    }

}