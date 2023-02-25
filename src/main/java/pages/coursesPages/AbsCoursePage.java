package pages.coursesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.AbsPage;

public abstract class AbsCoursePage extends AbsPage {
    @FindBy(css = ".sc-18q05a6-1~button")
    protected WebElement viewAllButton;
    protected String courseSelector = "a[href='/lessons/%s']";
    @FindBy(css = ".course-header2__title")
    protected WebElement courseTitle;
    @FindBy(css = ".course-header2__subtitle")
    protected WebElement courseDescription;
    @FindBy(css = ".container__col_md-4")
    protected WebElement courseDuration;
    @FindBy(css = ".container__col_md-2")
    protected WebElement courseFormat;

    public AbsCoursePage(WebDriver driver, String path) {

        super(driver, "/");
    }

    public void courseTitle(String courseTitleText) {

        assertByText(courseTitle, courseTitleText);
    }

    public void courseDescription(String courseDescriptionText) {

        assertByText(courseDescription, courseDescriptionText);
    }

    public void courseDuration(String courseDurationText) {

        assertByText(courseDuration, courseDurationText);
    }

    public void courseFormat(String courseFormatText) {

        assertByText(courseFormat, courseFormatText);
    }

    public void showMoreItems() {

        click(viewAllButton);
    }

    public void clickCourse(String courseName) {
        click(driver.findElement(By.cssSelector(String.format(courseSelector, courseName))));
    }
}