package pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbsPage {
    private String courseCategory = ".course-categories__nav-item[title='%s']";

    public MainPage(WebDriver driver) {

        super(driver, "/");
    }
}