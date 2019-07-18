package ru.job4j.crudservlet;

public enum Pages {
    MAIN("/chapter_011/users"),
    ADD("/chapter_011/add"),
    ADD_JSP("/WEB-INF/ru/job4j/crudservlet/add.jsp"),
    UPDATE("/chapter_011/update"),
    UPDATE_JSP("/WEB-INF/ru/job4j/crudservlet/update.jsp"),
    DELETE("/chapter_011/delete"),
    DELETE_JSP("/WEB-INF/ru/job4j/crudservlet/delete.jsp");

    public final String page;

    Pages(String page) {
        this.page = page;
    }
}
