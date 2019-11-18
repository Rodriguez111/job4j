package sellcars.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
@Controller
public class PictureController {
    private static final String UPLOAD_DIRECTORY = "uploaded_photos/";


    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    protected void showPictures(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String appPath = req.getServletContext().getRealPath("");
        String pathToPhotos = appPath + UPLOAD_DIRECTORY;
        String folder = req.getParameter("folder");
        String fileName = req.getParameter("fileName");
        File photoFile = new File(pathToPhotos + folder + "/" + fileName);
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
