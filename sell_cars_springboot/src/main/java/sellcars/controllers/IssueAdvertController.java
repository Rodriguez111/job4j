package sellcars.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import sellcars.models.User;
import sellcars.service.AdvertValidator;
import sellcars.service.CarValidator;


import javax.servlet.ServletException;

import java.io.*;

import java.security.Principal;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

@Controller
public class IssueAdvertController {


    @Value("${upload-dir}")
    private String uploadDirectory;


    private CarValidator carValidator;
    private AdvertValidator advertValidator;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String showCreateAdvertForm() {
        return "create_advert";
    }


    @PostMapping("/issue_advert")
    public String issueAdvert(@RequestParam List<MultipartFile> files,
                              @RequestParam Map<String, String> allRequestParams,
                              ModelMap modelMap,
                              Principal principal) throws ServletException, IOException {

        String res = "redirect:/";
        String result = "OK";
        int carId = -1;
        String carAddResult = carValidator.addCar(allRequestParams);
        try {
            carId = Integer.parseInt(carAddResult);
        } catch (NumberFormatException e) {
            result = carAddResult;
        }
        List<String> listOfPhotosNames;

        if (result.equals("OK")) {
            listOfPhotosNames = writeFilesToDisk(files, String.valueOf(carId));

            //добавляем объявление
            double price = Double.parseDouble(allRequestParams.get("price"));
            User activeUser = (User) ((Authentication) principal).getPrincipal();
            String userLogin = activeUser.getLogin();
            result = advertValidator.addAdvert(carId, price, listOfPhotosNames, userLogin);
        }

        if (!result.equals("OK")) {
            modelMap.addAttribute("messageFromServer", result);
            res = "create_advert";
        }
        return res;
    }


    private List<String> writeFilesToDisk(List<MultipartFile> files, String folderName) {
        List<String> listOfPhotosNames = new ArrayList<>();
        for (MultipartFile eachFile : files) {
            if (!eachFile.getOriginalFilename().equals("")) {
                File file = new File(uploadDirectory + folderName + "/" + eachFile.getOriginalFilename());
                file.mkdirs();
                deleteFileIfExists(file);
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(eachFile.getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                listOfPhotosNames.add(file.getName());
            }
        }
        return listOfPhotosNames;
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
