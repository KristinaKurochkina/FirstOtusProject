import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstTest {
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
    public void otusTest() throws InterruptedException {
//      Перейти на https://otus.ru
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://otus.ru");
//      Авторизация
        loginInOtus();
//      В личный кабинет
        lk();
//      Персональные данные
        enterNewValue(By.id("id_fname"), "Кристина");
        enterNewValue(By.id("id_fname_latin"), "Kristina");
        enterNewValue(By.id("id_lname"), "Иванова");
        enterNewValue(By.id("id_lname_latin"), "Ivanova");
        enterNewValue(By.id("id_blog_name"), "Кристина");
        enterNewValue(By.cssSelector("input[name=\"date_of_birth\"]"), "21.04.1990");

//      Основная информация
        driver.findElement(By.cssSelector("input[name=\"country\"]~div")).click();
        driver.findElement(By.cssSelector("button[title=\"Россия\"]")).click();

        driver.findElement(By.cssSelector("input[name=\"english_level\"]~div")).click();
        driver.findElement(By.cssSelector("button[title=\"Начальный уровень (Beginner)\"]")).click();

        driver.findElement(By.cssSelector("input[name=\"city\"]~div")).click();
        driver.findElement(By.cssSelector("button[title=\"Пенза\"]")).click();

//      Контактная информация
        driver.findElement(By.cssSelector("input[name=\"contact-0-service\"]~div")).click();
        driver.findElement(By.cssSelector("button[title=\"Viber\"]")).click();
        enterNewValue(By.id("id_contact-0-value"), "89677019887");

        driver.findElement(By.cssSelector("div[data-prefix='contact']> button")).click();
        driver.findElement(By.cssSelector("input[name=\"contact-1-service\"]~div")).click();
        driver.findElement(By.xpath("//div[@data-num='1']//button[@title=\"Тelegram\"]")).click();
        enterNewValue(By.id("id_contact-1-value"), "KrisK");

        driver.findElement(By.cssSelector("button[title='Сохранить и заполнить позже']")).click();

        close();
        driver = new ChromeDriver(options);
        driver.get("https://otus.ru");
//      Авторизация
        loginInOtus();
//      В личный кабинет
        lk();

        Assertions.assertEquals("Кристина", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assertions.assertEquals("Kristina", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assertions.assertEquals("Иванова", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assertions.assertEquals("Ivanova", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assertions.assertEquals("Кристина", driver.findElement(By.id("id_blog_name")).getAttribute("value"));
        Assertions.assertEquals("21.04.1990", driver.findElement(By.cssSelector("input[name='date_of_birth']")).getAttribute("value"));
        Assertions.assertEquals("Россия", driver.findElement(By.cssSelector("input[name=\"country\"]~div")).getText());
        Assertions.assertEquals("Пенза", driver.findElement(By.cssSelector("input[name=\"city\"]~div")).getText());
        Assertions.assertEquals("Начальный уровень (Beginner)", driver.findElement(By.cssSelector("input[name=\"english_level\"]~div")).getText());
        Assertions.assertEquals("Viber", driver.findElement(By.cssSelector("input[name=\"contact-1-service\"]~div")).getText());
        Assertions.assertEquals("89677019887", driver.findElement(By.id("id_contact-1-value")).getAttribute("value"));
        Assertions.assertEquals("Тelegram", driver.findElement(By.cssSelector("input[name=\"contact-0-service\"]~div")).getText());
        Assertions.assertEquals("KrisK", driver.findElement(By.id("id_contact-0-value")).getAttribute("value"));
    }

    public void loginInOtus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header3__button-sign-in")));
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @name='email' and @placeholder = 'Электронная почта']")));
        driver.findElement(By.xpath("//input[@type='text' and @name='email' and @placeholder = 'Электронная почта']")).sendKeys(login);
        driver.findElement(By.xpath("//input[ @name='password' and @placeholder = 'Введите пароль']")).sendKeys(pas);
        driver.findElement(By.cssSelector("form.new-log-reg__form>div>button[type='submit']")).submit();
    }

    public void lk() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header3__user-info-name")));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(".header3__user-info-name"))).perform();
        driver.findElement(By.cssSelector("a[href='/learning/']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title=\"О себе\"]")));
        driver.findElement(By.cssSelector("a[title=\"О себе\"]")).click();
    }

    private void enterNewValue(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

}
