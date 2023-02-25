package data;

import java.util.Arrays;

public enum MonthData {

    JANUARY("января", 1),
    FEBRUARY("февраля", 2),
    MARCH("марта", 3),
    APRIL("апреля", 4),
    MAY("мая", 5),
    JUNE("июня", 6),
    JULY("июля", 7),
    AUGUST("августа", 8),
    SEPTEMBER("сентября", 9),
    OCTOBER("октября", 10),
    NOVEMBER("ноября", 11),
    DECEMBER("декабря", 12);

    private String monthName;
    private int monthNumber;

    MonthData(String monthName, int monthNumber) {
        this.monthName = monthName;
        this.monthNumber = monthNumber;
    }
    public static MonthData getDate(String name) {
        return Arrays.stream(MonthData.values())
                .filter((MonthData monthData) -> monthData.getMonthName().equals(name))
                .findFirst().get();
    }
    public String getMonthName() {
        return monthName;
    }
    public int getMonthNumber() {
        return monthNumber;
    }
}