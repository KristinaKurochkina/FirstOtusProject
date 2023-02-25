package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CoursesCategoryMenu extends AbsComponent {
    public String navMenuItemSelector = "#categories_id a[title='%s']";

    public CoursesCategoryMenu(WebDriver driver) {

        super(driver);
    }

    public void clickTestingCategory(String coursesCategory) {

        String categoryButton = String.format(navMenuItemSelector, coursesCategory);
        moveToElementAndClick(driver.findElement(By.cssSelector(categoryButton)));

    }


}
