package sellcars.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sellcars.service.*;

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
    private ModelValidator carBrand;
    private ModelValidator carBody;
    private ModelValidator carTransmission;
    private ModelValidator carEngine;
    private AdvertValidator advertValidator;

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
            jsonStringToClient = carBrand.getModels();
        } else if (requestFromClient.equals("getListOfCarBodies")) {
            jsonStringToClient = carBody.getModels();
        } else if (requestFromClient.equals("getListOfTransmissions")) {
            jsonStringToClient = carTransmission.getModels();
        } else if (requestFromClient.equals("getListOfEngines")) {
            jsonStringToClient = carEngine.getModels();
        } else if (requestFromClient.equals("getAllAdverts")) {
            jsonStringToClient = advertValidator.getAllAdverts();
        } else if (requestFromClient.contains("getAdvert")) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<>() { };
            Map<String, Integer> mapFromJson = mapper.readValue(requestFromClient, typeRef);
            int id = mapFromJson.get("getAdvert");
            jsonStringToClient = advertValidator.getAdvertById(id);
            req.setAttribute("userLogin", req.getSession().getAttribute("userLogin"));
        } else if (requestFromClient.contains("setSoldStatus")) {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<>() { };
            Map<String, Integer> mapFromJson = mapper.readValue(requestFromClient, typeRef);
            int id = mapFromJson.get("setSoldStatus");
            String result = advertValidator.setSoldStatus(id);
            Map<String, String> map = Map.of("result", result);
            try {
                jsonStringToClient = new ObjectMapper().writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (requestFromClient.contains("filterSelect")) {
            jsonStringToClient = advertValidator.getAdvertByFilters(requestFromClient);
        }

        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(jsonStringToClient);
        printWriter.flush();
        LOG.info("Exit method");

    }

    @Autowired
    public void setCarBrand(ValidateCarBrand carBrand) {
        this.carBrand = carBrand;
    }
    @Autowired
    public void setCarBody(ValidateCarBody carBody) {
        this.carBody = carBody;
    }
    @Autowired
    public void setCarTransmission(ValidateTransmission carTransmission) {
        this.carTransmission = carTransmission;
    }
    @Autowired
    public void setCarEngine(ValidateEngine carEngine) {
        this.carEngine = carEngine;
    }
    @Autowired
    public void setAdvertValidator(AdvertValidator advertValidator) {
        this.advertValidator = advertValidator;
    }
}
