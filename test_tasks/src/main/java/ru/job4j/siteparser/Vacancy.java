package ru.job4j.siteparser;

import java.util.Objects;

public class Vacancy {
    private int id;
    private String name;
    private String text;
    private String link;
    private String author;
    private String date;

    public Vacancy(String name, String text, String link, String author, String date) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.author = author;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", link='" + link + '\''
                + ", author='" + author + '\''
                + ", date='" + date + '\''
                + '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id
              &&  Objects.equals(name, vacancy.name)
              &&  Objects.equals(text, vacancy.text)
              &&  Objects.equals(link, vacancy.link)
              &&  Objects.equals(author, vacancy.author)
              &&  Objects.equals(date, vacancy.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, link, author, date);
    }
}
