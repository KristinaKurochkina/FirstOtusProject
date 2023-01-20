import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class FirstTest {

    private static final Logger log = LogManager.getLogger(FirstTest.class);
    private final String login = "fivod55542@vingood.com";
    private final String pas = "Gipunu13!";
    WebDriver driver;

    @BeforeAll
    static void setUp() {

        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    public void close() {
        if (driver != null)
            driver.quit();
    }

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
    public void pictureTest() {
//    Открыть Chrome в режиме киоска
//    Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);

        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        //    Нажать на любую картинку
        WebElement picture = driver.findElement(By.cssSelector(".content-overlay"));
        picture.click();
//    Проверить что картинка открылась в модальном окне
        Assertions.assertTrue(driver.findElement(By.cssSelector(".pp_pic_holder")).isDisplayed());
    }

    @Test
    public void otusTest() {
//        Открыть Chrome в режиме полного экрана
//        Перейти на https://otus.ru
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://otus.ru");
//        Авторизоваться под каким-нибудь тестовым пользователем(можно создать нового)
        loginInOtus();
//        Вывести в лог все cookie

        for (Cookie cookies : driver.manage().getCookies())
            log.info(String.format("<%s>:<%s>", cookies.getName(), cookies.getValue()));
    }

    public void loginInOtus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header3__button-sign-in")));
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @name='email' and @placeholder = 'Электронная почта']")));
        driver.findElement(By.xpath("//input[@type='text' and @name='email' and @placeholder = 'Электронная почта']")).sendKeys(login);
        driver.findElement(By.xpath("//input[ @name='password' and @placeholder = 'Введите пароль']")).sendKeys(pas);
        driver.findElement(By.cssSelector("form.new-log-reg__form>div>button[type='submit']")).submit();

    }

}