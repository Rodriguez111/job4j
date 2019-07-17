package ru.job4j.crudservlet;

public enum Pages {
    MAIN("/chapter_011/users"),
    ADD("/chapter_011/add"),
    UPDATE("/chapter_011/update"),
    DELETE("/chapter_011/delete");

    public final String page;

    Pages(String page) {
        this.page = page;
    }
}
