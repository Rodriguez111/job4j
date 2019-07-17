package ru.job4j.crudservlet.presentation;

import ru.job4j.crudservlet.Pages;

public class HTML {

    public static String generateStyle1() {
        StringBuilder sb = new StringBuilder();
        sb.append("<style type='text/css'>")
                .append(".block1 {")
                .append("width: 300px;")
                .append("background: #ccc;")
                .append("padding: 5px;")
                .append("border: solid 1px grey;")
                .append("float: left;")
                .append(" }")
                .append("</style>");
        return sb.toString();
    }

    public static String generateHtml(String header, String body) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("<h2>" + header + "</h2>")
                .append("</head>")
                .append("<body>")
                .append(body)
                .append("</body>")
                .append("</html>");
        return sb.toString();
    }

    public static String generateReturnForm() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form  action='" + Pages.MAIN.page + "'>")
                .append("<button>BACK</button>")
                .append("</form>");
        return sb.toString();
    }

    public static String generateBlock(String form) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='block1'>")
                .append(form)
                .append("</div>");
        return sb.toString();
    }
}
