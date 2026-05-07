package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 該当するデータの取得
    	String cd = req.getParameter("cd");
    	String schoolCd = req.getParameter("school_cd");

    	School school = new School();
    	school.setCd(schoolCd);

    	SubjectDao dao = new SubjectDao();
    	Subject subject = dao.get(cd, school);

        //確認画面に取得したデータを渡してフォワード
        req.setAttribute("subject", subject);
        req.getRequestDispatcher("/scoremanager/main/subject_delete.jsp")
           .forward(req, res);
    }
}
