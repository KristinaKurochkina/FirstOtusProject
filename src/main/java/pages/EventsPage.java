package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EventsPage extends AbsPage {

    @FindBy(css = ".dod_new-events-dropdown")
    private WebElement eventsSorting;
    @FindBy(css = ".footer2__container")
    private WebElement footer;
    private String eventSelector = ".dod_new-events-dropdown__list-item[title='%s']";

    public EventsPage(WebDriver driver) {

        super(driver, "/events/near/");
    }

    public void eventsSortingMenu() {

        moveToElementAndClick(eventsSorting);
    }

    public void eventSortingType(String eventTypeName) {
        waiter.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dod_new-events-dropdown_opened")));
        click(driver.findElement(By.cssSelector(String.format(eventSelector, eventTypeName))));
    }

    public void scrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i <= 100; i++) {
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth'})", footer);
            js.executeScript("arguments[0].scrollIntoView(false);", footer);
            waiter.waitForCondition(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".dod_new-event-content")));
        }
    }
}