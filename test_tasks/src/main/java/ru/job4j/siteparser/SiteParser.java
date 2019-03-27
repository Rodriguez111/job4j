package ru.job4j.siteparser;

<<<<<<< HEAD
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class SiteParser {
    private static final String URL = "https://www.sql.ru";
    private Document document;

    private void connect() {
        try {
            this.document = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
=======
public class SiteParser {
>>>>>>> origin/master
}
