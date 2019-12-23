package sellcars.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PictureServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "uploaded_photos/";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
