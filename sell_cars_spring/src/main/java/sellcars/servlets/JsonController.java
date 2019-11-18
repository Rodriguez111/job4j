package sellcars.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sellcars.controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JsonController {
    private final static Logger LOG = LoggerFactory.getLogger(JsonController.class);
    private final static ModelValidator CAR_BRAND = ValidateCarBrand.getINSTANCE();
    private final static ModelValidator CAR_BODY = ValidateCarBody.getINSTANCE();
    private final static ModelValidator CAR_TRANSMISSION = ValidateTransmission.getINSTANCE();
    private final static ModelValidator CAR_ENGINE = ValidateEngine.getINSTANCE();
    private final static AdvertValidator ADVERT_VALIDATOR = ValidateAdvert.getINSTANCE();

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    protected void handleJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("Enter method");
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String read = "";
        while ((read = br.readLine()) != null) {
            sb.append(read);
        }
        String requestFromClient = sb.toString();
        String jsonStringToClient = "";

        if (requestFromClient.equals("getListOfCarBrands")) {
            jsonStringToClient = CAR_BRAND.getModels();
        } else if (requestFromClient.equals("getListOfCarBodies")) {
            jsonStringToClient = CAR_BODY.getModels();
        } else if (requestFromClient.equals("getListOfTransmissions")) {
            jsonStringToClient = CAR_TRANSMISSION.getModels();
        } else if (requestFromClient.equals("getListOfEngines")) {
            jsonStringToClient = CAR_ENGINE.getModels();
        } else if (requestFromClient.equals("getAllAdverts")) {
            jsonStringToClient = ADVERT_VALIDATOR.getAllAdverts();
        } else if (requestFromClient.contains("getAdvert")) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<>() { };
            Map<String, Integer> mapFromJson = mapper.readValue(requestFromClient, typeRef);
            int id = mapFromJson.get("getAdvert");
            jsonStringToClient = ADVERT_VALIDATOR.getAdvertById(id);
            req.setAttribute("userLogin", req.getSession().getAttribute("userLogin"));
        } else if (requestFromClient.contains("setSoldStatus")) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<>() { };
            Map<String, Integer> mapFromJson = mapper.readValue(requestFromClient, typeRef);
            int id = mapFromJson.get("setSoldStatus");
            String result = ADVERT_VALIDATOR.setSoldStatus(id);
            Map<String, String> map = Map.of("result", result);
            try {
                jsonStringToClient = new ObjectMapper().writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (requestFromClient.contains("filterSelect")) {
            jsonStringToClient = ADVERT_VALIDATOR.getAdvertByFilters(requestFromClient);
        }

        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(jsonStringToClient);
        printWriter.flush();
        LOG.info("Exit method");

    }
}
