package components;

import data.MonthData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class EventsComponents extends AbsComponent {

    @FindBy(css = ".dod_new-event__time")
    List<WebElement> eventsList;
    @FindBy(css = ".dod_new-event__type")
    List<WebElement> dodList;

    public EventsComponents(WebDriver driver) {

        super(driver);
    }

    public void checkEventType(String eventType) {
        for (WebElement element : dodList) {
            Assertions.assertTrue(element.getText().contains(eventType));
        }
    }
    public void checkEventsDate() {
        Assertions.assertTrue((driver.findElement(By.cssSelector(".dod_new-event__time"))).isDisplayed());
        Assertions.assertTrue(eventsList.stream()
                .map(WebElement::getText)
                .map((String dateStr) -> {
                    String monthStr = dateStr.split(" ")[1];

                    dateStr = dateStr.replaceAll("[а-я]+", String.format("%d", MonthData.getDate(monthStr).getMonthNumber()));
                    dateStr += " " + LocalDate.now().getYear();
                    dateStr = dateStr.charAt(0) + dateStr.substring(1).toLowerCase();

                    if (dateStr.equals("Сейчас в эфире")) {
                        return LocalDateTime.now();
                    }

                    return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("d M HH:mm yyyy", Locale.ROOT));
                })
                .allMatch((LocalDateTime localDateTime) -> localDateTime.isAfter(localDateTime.now()) || localDateTime.isEqual(localDateTime.now())));
    }
}

