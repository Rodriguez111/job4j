package sellcars.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sellcars.models.User;
import sellcars.service.AdvertValidator;
import sellcars.service.CarValidator;
import sellcars.service.ValidateCar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IssueAdvertController {
    private CarValidator carValidator;
    private AdvertValidator advertValidator;
    private static final String UPLOAD_DIRECTORY = "uploaded_photos/";

    private static final int MAX_FILE_SIZE = 1024 * 300; // 300 KB

    private Map<String, String> parameters;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String showCreateAdvertForm() {
        return "create_advert";
    }


    @RequestMapping(value = "/issue_advert", method = RequestMethod.POST)
    public String issueAdvert(HttpServletRequest req, Principal principal) throws ServletException, IOException {
        String res = "main_view";
        parameters = new HashMap<>();
        List<FileItem> listOfFileItems = new ArrayList<>();
        List<String> listOfPhotos = new ArrayList<>();
        String result = "OK";
        String appPath = req.getServletContext().getRealPath("");
        String pathToPhotos = appPath + UPLOAD_DIRECTORY;
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

        ServletFileUpload sfu = new ServletFileUpload(diskFileItemFactory);

        List<FileItem> files = null;
        try {
            files = sfu.parseRequest(req);
            for (FileItem eachItem : files) {
                if (eachItem.isFormField()) {
                    getParameter(eachItem);
                } else {
                    if (eachItem.getSize() > 0) {
                        if (eachItem.getSize() > MAX_FILE_SIZE) {
                            result = "Размер файла не должен превышать " + MAX_FILE_SIZE / 1024 + " КБ";
                            break;
                        } else {
                            listOfFileItems.add(eachItem);
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            result = "Возникла непредвиденная ошибка при обработке данных";
        }
        int carId = -1;

        if (result.equals("OK")) {
            String carAddResult = carValidator.addCar(parameters);

            try {
                carId = Integer.parseInt(carAddResult);
            } catch (NumberFormatException e) {
                result = carAddResult;
            }

            if (result.equals("OK")) {
                for (FileItem eachItem : listOfFileItems) {
                    String fullFilePath = pathToPhotos + carAddResult + "/" + eachItem.getName();
                    listOfPhotos.add(eachItem.getName());
                    File file = new File(fullFilePath);
                    deleteFileIfExists(file);
                    try {
                        eachItem.write(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                        result = "Возникла непредвиденная ошибка при сохранении фото";
                    }
                }
            }
        }

        if (result.equals("OK")) {
            //добавляем объявление
            double price = Double.parseDouble(parameters.get("price"));
            User activeUser = (User) ((Authentication) principal).getPrincipal();
            String userLogin = activeUser.getLogin();
            result = advertValidator.addAdvert(carId, price, listOfPhotos, userLogin);
        }


        if (!result.equals("OK")) {
            req.setAttribute("messageFromServer", result);
            res = "create_advert";
        }
        return res;
    }

    private void getParameter(FileItem item) {
        String paramValue = new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        parameters.put(item.getFieldName(), paramValue);
    }

    private void deleteFileIfExists(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    @Autowired
    public void setCarValidator(CarValidator carValidator) {
        this.carValidator = carValidator;
    }

    @Autowired
    public void setAdvertValidator(AdvertValidator advertValidator) {
        this.advertValidator = advertValidator;
    }
}
