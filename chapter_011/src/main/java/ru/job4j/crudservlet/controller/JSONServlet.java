package ru.job4j.crudservlet.controller;

import org.json.JSONObject;
import ru.job4j.crudservlet.controller.logic.CountriesValidator;
import ru.job4j.crudservlet.controller.logic.RolesValidator;
import ru.job4j.crudservlet.controller.logic.ValidateCountries;
import ru.job4j.crudservlet.controller.logic.ValidateRoles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONServlet extends HttpServlet {

    private static final CountriesValidator COUNTRIES_VALIDATOR = ValidateCountries.getInstance();
    private static final RolesValidator ROLES_VALIDATOR = ValidateRoles.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String read = "";
        while ((read = reader.readLine()) != null) {
            builder.append(read);
        }
        String requestFromClient = builder.toString();
        JSONObject jsonObject = new JSONObject();

        if (requestFromClient.equals("getListOfRoles")) {
            jsonObject = ROLES_VALIDATOR.getAllRoles();
        } else if (requestFromClient.equals("getListOfCountries")) {
            jsonObject = COUNTRIES_VALIDATOR.getAllCountries();
        } else if (requestFromClient.contains("getListOfCities")) {
            JSONObject jsonObjectFromClient = new JSONObject(requestFromClient);
            jsonObject = COUNTRIES_VALIDATOR.getAllCitiesByCountry(jsonObjectFromClient.getString("getListOfCities"));
        }

        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(jsonObject);
        writer.flush();
    }
}
