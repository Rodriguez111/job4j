package sellcars.controllers;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.ui.ModelMapExtensionsKt;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, HttpServletRequest request) {

        String exceptionMessage = e.getCause().getMessage();
        String result = exceptionMessage.substring(exceptionMessage.indexOf("size of ") + 8, exceptionMessage.indexOf(" bytes"));
        request.setAttribute("messageFromServer", "Допустимый размер одного файла не более " + result + " байт");
        return "create_advert";

    }


}
