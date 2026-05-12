package scoremanager.main;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            res.sendRedirect("Login.action");
            return;
        }

        School school = teacher.getSchool();

        String studentNo = req.getParameter("student_no");
        String subjectCd = req.getParameter("subject_cd");

        if (studentNo == null || subjectCd == null) {
            req.setAttribute("error", "パラメータが不足しています。");
            req.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(req, res);
            return;
        }

        TestDao dao = new TestDao();

        // 1回と2回を調べる
        Test test1 = dao.get(studentNo, subjectCd, school, 1);
        Test test2 = dao.get(studentNo, subjectCd, school, 2);

        // どちらも無い
        if (test1 == null && test2 == null) {
            req.setAttribute("error", "削除対象の成績が存在しません。");
            req.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(req, res);
            return;
        }

        // 片方しか無い → それを削除対象として画面表示
        if (test1 != null && test2 == null) {
            req.setAttribute("test", test1);
            req.setAttribute("both", false);
            req.getRequestDispatcher("/scoremanager/main/test_delete.jsp").forward(req, res);
            return;
        }

        if (test1 == null && test2 != null) {
            req.setAttribute("test", test2);
            req.setAttribute("both", false);
            req.getRequestDispatcher("/scoremanager/main/test_delete.jsp").forward(req, res);
            return;
        }

        // 両方ある → 回数選択できるようにする
        req.setAttribute("test1", test1);
        req.setAttribute("test2", test2);
        req.setAttribute("both", true);

        req.getRequestDispatcher("/scoremanager/main/test_delete.jsp").forward(req, res);
    }
}