package pages;

import org.openqa.selenium.WebDriver;
import pageObject.AbsPageObject;

public abstract class AbsPage extends AbsPageObject {

    private String baseUrl = System.getProperty("base.url", "https://otus.ru");
    private String path;

    public AbsPage(WebDriver driver, String path) {
        super(driver);
        this.path = path;
    }

    public void open() {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        driver.get(baseUrl + path);
    }
}