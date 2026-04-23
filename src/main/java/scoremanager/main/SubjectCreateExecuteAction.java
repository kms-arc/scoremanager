package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // 画面からの入力値を受け取る
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        
        // Subjectオブジェクトにセット
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());
        
        // DBに保存
        SubjectDao sDao = new SubjectDao();
        sDao.save(subject);
        
        // 保存が終わったら一覧画面へ戻る
        res.sendRedirect("SubjectMenu.action");
    }
}