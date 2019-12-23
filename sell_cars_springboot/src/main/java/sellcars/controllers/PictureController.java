package sellcars.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class PictureController {
    @Value("${upload-dir}")
    private String uploadDirectory;

    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    protected void showPictures(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String appPath = "c:/carsell/";
        String folder = req.getParameter("folder");
        String fileName = req.getParameter("fileName");
        if (!fileName.equals("")) {
            File photoFile = new File(uploadDirectory + folder + "/" + fileName);
            FileInputStream fileInputStream = new FileInputStream(photoFile);
            int bufferSize = 1024 * 200;
            byte[] buffer = new byte[bufferSize];
            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            OutputStream outputStream = resp.getOutputStream();
            resp.setContentType("image/gif");
            while (bytesRead > 0) {
                outputStream.write(buffer);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            fileInputStream.close();
            outputStream.flush();
            outputStream.close();
        }
    }
}
