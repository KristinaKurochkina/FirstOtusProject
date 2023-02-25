package components;

import org.openqa.selenium.WebDriver;
import pageObject.AbsPageObject;

public abstract class AbsComponent extends AbsPageObject {

    public AbsComponent(WebDriver driver) {
        super(driver);
    }
}
