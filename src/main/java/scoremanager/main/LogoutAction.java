package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        session.invalidate();

        // ★ logout.jsp を表示する
        req.getRequestDispatcher("/scoremanager/main/logout.jsp").forward(req, res);
    }
}
