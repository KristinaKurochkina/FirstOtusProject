package components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CoursesComponents extends AbsComponent {

    @FindBy(css = ".sc-18q05a6-1 > a")
    private List<WebElement> coursesList;

    public CoursesComponents(WebDriver driver) {
        super(driver);
    }

    public void CoursesVisibility() {
        checkVisibility(driver.findElement(By.cssSelector(".sc-18q05a6-1 > a")));

    }

    public void coursesCount(int count) {
        Assertions.assertEquals(count, coursesList.size());
    }
}