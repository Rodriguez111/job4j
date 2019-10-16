package sellcars.servlets;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sellcars.controller.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonServlet extends HttpServlet {
    private final static Logger LOG = LoggerFactory.getLogger(JsonServlet.class);
    private final static ModelValidator CAR_BRAND = ValidateCarBrand.getINSTANCE();
    private final static ModelValidator CAR_BODY = ValidateCarBody.getINSTANCE();
    private final static ModelValidator CAR_TRANSMISSION = ValidateTransmission.getINSTANCE();
    private final static ModelValidator CAR_ENGINE = ValidateEngine.getINSTANCE();
    private final static AdvertValidator ADVERT_VALIDATOR = ValidateAdvert.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Enter method");
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String read = "";
        while ((read = br.readLine()) != null) {
            sb.append(read);
        }
        String requestFromClient = sb.toString();
        JSONObject jsonObject = new JSONObject();

        if (requestFromClient.equals("getListOfCarBrands")) {
            jsonObject = CAR_BRAND.getModels();
        } else if (requestFromClient.equals("getListOfCarBodies")) {
            jsonObject = CAR_BODY.getModels();
        } else if (requestFromClient.equals("getListOfTransmissions")) {
            jsonObject = CAR_TRANSMISSION.getModels();
        } else if (requestFromClient.equals("getListOfEngines")) {
            jsonObject = CAR_ENGINE.getModels();
        } else if (requestFromClient.equals("getAllAdverts")) {
            jsonObject = ADVERT_VALIDATOR.getAllAdverts();

        } else if (requestFromClient.contains("getAdvert")) {
            JSONObject jsonFromClient = new JSONObject(requestFromClient);
            int id = jsonFromClient.getInt("getAdvert");
            jsonObject = ADVERT_VALIDATOR.getAdvertById(id);
            req.setAttribute("userLogin", req.getSession().getAttribute("userLogin"));
        } else if (requestFromClient.contains("setSoldStatus")) {
            JSONObject jsonFromClient = new JSONObject(requestFromClient);
            int id = jsonFromClient.getInt("setSoldStatus");
            String result = ADVERT_VALIDATOR.setSoldStatus(id);
            jsonObject.put("result", result);

        }

        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(jsonObject);
        printWriter.flush();
        LOG.info("Exit method");

    }
}
