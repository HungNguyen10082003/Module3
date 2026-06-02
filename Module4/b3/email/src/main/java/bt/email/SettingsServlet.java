package bt.email;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "settingsServlet", value = "/settings")
public class SettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmailSettings settings = (EmailSettings) getServletContext().getAttribute("emailSettings");
        if (settings == null) {
            settings = new EmailSettings();
            getServletContext().setAttribute("emailSettings", settings);
        }
        req.setAttribute("settings", settings);
        req.getRequestDispatcher("/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String language = req.getParameter("language");
        String pageSizeParam = req.getParameter("pageSize");
        String spamParam = req.getParameter("spamFilter");
        String signature = req.getParameter("signature");

        EmailSettings settings = (EmailSettings) getServletContext().getAttribute("emailSettings");
        if (settings == null) {
            settings = new EmailSettings();
        }

        if (language != null) settings.setLanguage(language);
        try {
            if (pageSizeParam != null) settings.setPageSize(Integer.parseInt(pageSizeParam));
        } catch (NumberFormatException ignored) {}
        settings.setSpamFilter(spamParam != null && (spamParam.equals("on") || spamParam.equals("true")));
        if (signature != null) settings.setSignature(signature);

        getServletContext().setAttribute("emailSettings", settings);

        resp.sendRedirect(req.getContextPath() + "/settings");
    }
}
