package ru.job4j.crudservlet;

public enum Pages {
    MAIN("/users"),
    MAIN_JSP("/WEB-INF/ru/job4j/crudservlet/viewer/main_page_view.jsp"),
    ADD("/add"),
    ADD_JSP("/WEB-INF/ru/job4j/crudservlet/viewer/add_view.jsp"),
    UPDATE("/update"),
    UPDATE_JSP("/WEB-INF/ru/job4j/crudservlet/viewer/update_view.jsp"),
    DELETE("/delete"),
    DELETE_JSP("/WEB-INF/ru/job4j/crudservlet/viewer/delete_view.jsp"),
    SIGN_IN("/signin"),
    SIGN_IN_JSP("/WEB-INF/ru/job4j/crudservlet/viewer/signin_view.jsp");

    public final String page;

    Pages(String page) {
        this.page = page;
    }
}
