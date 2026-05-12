package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        try {
            String path = req.getServletPath(); // /StudentList.action
            String name = path.substring(1, path.lastIndexOf(".")); // StudentList
            String fqcn = "scoremanager.main." + name + "Action";

            Class<?> c = Class.forName(fqcn);
            Action action = (Action) c.getDeclaredConstructor().newInstance();
            action.execute(req, res);

        } catch (Exception e) {

            // その他のエラー
            req.setAttribute("errorMessage", "システムエラーが発生しました");

            req.getRequestDispatcher("/common/error.jsp")
               .forward(req, res);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}
