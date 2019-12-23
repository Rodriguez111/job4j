package sellcars.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ForwardServlet extends HttpServlet {
    private final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> dispatcher = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("action");
        dispatcher.get(param).accept(req, resp);
    }

    @Override
    public void init() throws ServletException {
        dispatcher.put("register", (req, resp) -> {
            try {
                req.getRequestDispatcher("/WEB-INF/sellcars/view/register.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        });
        dispatcher.put("issue", (req, resp) -> {
            try {
                req.getRequestDispatcher("/WEB-INF/sellcars/view/create_advert.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        });
        dispatcher.put("show_advert", (req, resp) -> {
            try {
                req.getRequestDispatcher("/WEB-INF/sellcars/view/show_advert.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        });
        dispatcher.put("edit_advert", (req, resp) -> {
            try {
                req.getRequestDispatcher("/WEB-INF/sellcars/view/edit_advert.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        });
    }
}
