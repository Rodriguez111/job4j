package ru.job4j.siteparser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.siteparser.utils.DateConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SiteParser {
    private static final Logger LOG = LoggerFactory.getLogger(SiteParser.class.getName());
    private static final String URL = "https://www.sql.ru/forum/job-offers/";
    private boolean startOfTheYearReached = false;

    private Document document(int pageNumber) {
        Document document = null;
        try {
            document = Jsoup.connect(URL + pageNumber).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private Document document(String link) {
        Document document = null;
        try {
            document = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private int getNumberOfPages() {
        Elements elements = document(0).getElementsByClass("sort_options");
         elements = elements.select("td[style=text-align:left]");
        elements = elements.select("a");
        int max = 0;
        for (Element e : elements) {
             if (Integer.valueOf(e.text()) > max) {
                 max = Integer.valueOf(e.text());
             }
        }
        return max;
    }

    public List<Vacancy> scanSinglePage(int pageNumber) {
        List<Vacancy> vacancies = new ArrayList<>();
        Elements elements = document(pageNumber).select("tr");
       for (Element eachElement : elements) {
           if (startOfTheYearReached) {
               break;
           }
           Element name =  eachElement.selectFirst("td[class=postslisttopic]");
           if (name == null) {
               continue;
           } else if (!elementIsJava(name)) {
               continue;
           }
           Elements dates =  eachElement.select("td[style=text-align:center][class=altCol]");
           for (Element eachDate : dates) {
               if (DateConverter.checkElementIsInDate(eachDate)) {
                   vacancies.add(createVacancy(eachElement));
               } else {
                   startOfTheYearReached = true;
                   break;
               }
           }
       }
       return vacancies;
    }

    private Vacancy createVacancy(Element element) {
        String name = element.selectFirst("td[class=postslisttopic]").text();
        String link = element.select("td[class=postslisttopic] > a").attr("href");
        String text = getTextFromItem(link);
        String author = element.select("td[class=altCol] > a").text();
        Element dateOfElement = element.select("td[style=text-align:center][class=altCol]").first();
        String date = DateConverter.getDateFromElement(dateOfElement);
        return new Vacancy(name, text, link, author, date);
    }

    private String getTextFromItem(String link) {
        Element topic = document(link);
        String text =  topic.select("meta[name=Description]").attr("content");
        return text.substring(text.indexOf("/ Вакансии / ") + 12).trim();
    }

    private boolean elementIsJava(Element element) {
        boolean result = false;
        if (element.text().toLowerCase().contains("java") && !element.text().toLowerCase().contains("script")) {
            result = true;
        }
        return result;
    }

    public List<Vacancy> scanPagesAndGetVacancies() {
        List<Vacancy> vacancies = new ArrayList<>();
        LOG.info("Scanning pages...");
        int numberOfPages = getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            if (startOfTheYearReached) {
                break;
            }
            vacancies.addAll(scanSinglePage(i));
        }
        return vacancies;
    }
}
