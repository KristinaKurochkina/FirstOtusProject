package pageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import waiters.Waiter;

public abstract class AbsPageObject {

    protected WebDriver driver;
    protected Actions actions;
    protected Waiter waiter;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiter = new Waiter(driver);

        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    protected void moveToElementAndClick(WebElement element) {
        waiter.waitForCondition(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).click().perform();
    }

    protected void assertByText(WebElement element, String text) {
        Assertions.assertTrue(element.getText().contains(text));
    }

    public void checkVisibility(WebElement element) {
        Assertions.assertTrue(element.isDisplayed());
    }
}