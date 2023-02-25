package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuComponents extends AbsComponent {

    private String menuSelector = ".header3__nav-item[data-name='%s']";
    private String learningSelector = ".header3__nav-column-item[href='%s']";

    public MenuComponents(WebDriver driver) {

        super(driver);
    }

    public void clickLearningMenu(String menuName, String item) {
        actions.moveToElement(driver.findElement(By.cssSelector(String.format(menuSelector, menuName)))).perform();
        waiter.waitForCondition(ExpectedConditions.visibilityOf((driver.findElement(By.cssSelector(String.format(learningSelector, item))))));
        click(driver.findElement(By.cssSelector(String.format(learningSelector, item))));


    }
}