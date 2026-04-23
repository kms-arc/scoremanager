package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 登録画面（JSP）へフォワードする
    	req.getRequestDispatcher("/scoremanager/main/subject_create.jsp").forward(req, res);
    }
}