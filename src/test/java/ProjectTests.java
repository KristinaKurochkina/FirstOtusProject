import components.CoursesCategoryMenu;
import components.CoursesComponents;
import components.EventsComponents;
import components.MenuComponents;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.EventsPage;
import pages.MainPage;
import pages.coursesPages.JavaQAEngineerBasicPage;
import pages.coursesPages.TestingPage;

public class ProjectTests {

    private WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void countСoursesTest() {
        MainPage mainPage = new MainPage(driver);
        TestingPage testingPage = new TestingPage(driver);
        CoursesComponents coursesComponents = new CoursesComponents(driver);
        CoursesCategoryMenu coursesCategoryMenu = new CoursesCategoryMenu(driver);

        mainPage.open();
        coursesCategoryMenu.clickTestingCategory("Тестирование");
        testingPage.showMoreItems();
        coursesComponents.CoursesVisibility();
        coursesComponents.coursesCount(12);
    }

    @Test
    public void checkCourseDataTest() {
        MainPage mainPage = new MainPage(driver);
        TestingPage testingPage = new TestingPage(driver);
        CoursesCategoryMenu coursesCategoryMenu = new CoursesCategoryMenu(driver);
        JavaQAEngineerBasicPage javaQAEngineerBasicPage = new JavaQAEngineerBasicPage(driver);

        mainPage.open();
        coursesCategoryMenu.clickTestingCategory("Тестирование");
        testingPage.showMoreItems();
        testingPage.clickCourse("java-qa-basic");
        javaQAEngineerBasicPage.courseTitle(javaQAEngineerBasicPage.getCourseTitleText());
        javaQAEngineerBasicPage.courseDescription(javaQAEngineerBasicPage.getCourseDescriptionText());
        javaQAEngineerBasicPage.courseDuration(javaQAEngineerBasicPage.getCourseDurationText());
        javaQAEngineerBasicPage.courseFormat(javaQAEngineerBasicPage.getCourseFormatText());
    }

    @Test
    public void EventsDateTest() {
        MenuComponents menuComponents = new MenuComponents(driver);
        EventsPage eventsPage = new EventsPage(driver);
        EventsComponents eventsComponents = new EventsComponents(driver);

        menuComponents.clickLearningMenu("learning", "/events/near/");
        eventsPage.scrollPage();
        eventsComponents.checkEventsDate();
    }

    @Test
    public void EventsTypeTest() {
        MenuComponents menuComponents = new MenuComponents(driver);
        EventsPage eventsPage = new EventsPage(driver);
        EventsComponents eventsComponents = new EventsComponents(driver);

        menuComponents.clickLearningMenu("learning", "/events/near/");
        eventsPage.eventsSortingMenu();
        eventsPage.eventSortingType("ДОД");
        eventsComponents.checkEventType("День открытых дверей");
    }
}
