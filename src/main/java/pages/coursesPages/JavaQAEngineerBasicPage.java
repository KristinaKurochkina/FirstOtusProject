package pages.coursesPages;

import org.openqa.selenium.WebDriver;

public class JavaQAEngineerBasicPage extends AbsCoursePage {

    private String courseTitleText = "Java QA Engineer. Basic";
    private String courseDescriptionText = "Автоматизация тестирования на Java с нуля";
    private String courseDurationText = "4 месяца";
    private String courseFormatText = "Online";

    public JavaQAEngineerBasicPage(WebDriver driver) {

        super(driver, "/lessons/java-qa-basic/");
    }

    public String getCourseTitleText() {

        return courseTitleText;
    }

    public String getCourseDescriptionText() {

        return courseDescriptionText;
    }

    public String getCourseDurationText() {

        return courseDurationText;
    }

    public String getCourseFormatText() {

        return courseFormatText;
    }
}