package ru.job4j.siteparser.utils;

import org.jsoup.nodes.Element;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class DateConverter {

    private final Map<String, String> monthConvert = new HashMap<>();

    public DateConverter() {
        monthConvertInit();
    }

    private void monthConvertInit() {
        monthConvert.put("янв", "01");
        monthConvert.put("фев", "02");
        monthConvert.put("мар", "03");
        monthConvert.put("апр", "04");
        monthConvert.put("май", "05");
        monthConvert.put("июн", "06");
        monthConvert.put("июл", "07");
        monthConvert.put("авг", "08");
        monthConvert.put("сен", "09");
        monthConvert.put("окт", "10");
        monthConvert.put("ноя", "11");
        monthConvert.put("дек", "12");
    }

    public static String getDateFromElement(Element element) {
        String rawDate = element.text();
        String resultDate = "";
        if (rawDate.contains("сегодня")) {
            resultDate = getYesterdayOrTodayDate(rawDate, 0);
        } else if (rawDate.contains("вчера")) {
            resultDate = getYesterdayOrTodayDate(rawDate, 1);
        } else {
            DateConverter dateConverter = new DateConverter();
            resultDate = dateConverter.getDate(rawDate);
        }
        return resultDate;
    }

    public static String getYesterdayOrTodayDate(String date, int diff) {
        LocalDateTime today = LocalDateTime.now().minusDays(diff);
        String todayDate = formatDate(today);
        StringBuilder sb = new StringBuilder();
        sb.append(todayDate).append(" ")
                .append(date.substring(date.length() - 5));
        return sb.toString();
    }

    private static String formatDate(LocalDateTime date) {
        String todayDate = String.valueOf(date.getDayOfMonth());
        todayDate = todayDate.length() > 1 ? todayDate : "0" + todayDate;
        String todayMonth = String.valueOf(date.getMonthValue());
        todayMonth = todayMonth.length() > 1 ? todayMonth : "0" + todayMonth;
        String todayYear = String.valueOf(date.getYear());
        StringBuilder sb = new StringBuilder();
        sb.append(todayYear).append("-").append(todayMonth).append("-").append(todayDate);
        return sb.toString();
    }

    public static boolean checkElementIsInDate(Element element) {
        boolean result = false;
        String currentYear = String.valueOf(LocalDateTime.now().getYear()).substring(2);
        int indexOfComma = element.text().indexOf(",");
        String yearOfElement = element.text().substring(indexOfComma - 2, indexOfComma);
        if (element.text().toLowerCase().contains("вчера") || element.text().toLowerCase().contains("сегодня")) {
            result = true;
        } else if (currentYear.equals(yearOfElement)) {
            result = true;
        }
        return result;
    }

    public String getDate(String date) {
        String day = date.substring(0, date.indexOf(",") - 7);
        day = day.length() > 1 ? day : "0" + day;
        String month = date.substring(date.indexOf(",") - 6, date.indexOf(",") - 3);
        month = monthConvert.get(month);
        String year = date.substring(date.indexOf(",") - 2, date.indexOf(","));
        year = "20" + year;
        StringBuilder sb = new StringBuilder();
        sb.append(year).append("-").append(month).append("-").append(day)
                .append(" ").append(date.substring(date.length() - 5));
        return sb.toString();
    }


}
