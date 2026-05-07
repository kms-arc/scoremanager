package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 科目コードの取得
        String cd = req.getParameter("cd");

        // 科目コードをオブジェクトにセット
        Subject subject = new Subject();
        subject.setCd(cd);
        
        School school = new School();
        school.setCd(req.getParameter("school_cd"));
        subject.setSchool(school);

        // 削除実行
        SubjectDao dao = new SubjectDao();
        boolean result = dao.delete(subject);

        //完了画面にフォワード
        req.setAttribute("result", result);
        req.setAttribute("subject", subject);
        req.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp")
           .forward(req, res);
    }
}