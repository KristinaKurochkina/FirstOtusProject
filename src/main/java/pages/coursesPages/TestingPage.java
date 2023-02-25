package pages.coursesPages;

import org.openqa.selenium.WebDriver;

public class TestingPage extends AbsCoursePage {
    public TestingPage(WebDriver driver) {

        super(driver, "/catalog/courses?categories=testing");
    }
}