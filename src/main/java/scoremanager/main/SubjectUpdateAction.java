package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // パラメータ取得
        String cd = req.getParameter("cd");
        String schoolCd = req.getParameter("school_cd");        
        School school = new School();
        school.setCd(schoolCd);

        // データ取得
        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd, school);

        // エラー
        if (schoolCd == null || schoolCd.isEmpty()) {
            throw new Exception("school_cdが取得できていません");
        }

        // フォワード
        req.setAttribute("subject", subject);
        req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
           .forward(req, res);
    }
}
