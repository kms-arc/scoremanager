package scoremanager.main;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String id = req.getParameter("id");
        String password = req.getParameter("password");

        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        if (teacher == null) {
            req.setAttribute("error", "ID またはパスワードが違います");
            req.getRequestDispatcher("/scoremanager/main/login.jsp").forward(req, res);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("teacher", teacher);

        req.getRequestDispatcher("/scoremanager/main/menu.jsp").forward(req, res);
    }
}
