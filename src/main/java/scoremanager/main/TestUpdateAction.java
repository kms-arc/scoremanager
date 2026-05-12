package scoremanager.main;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateAction extends Action {

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
        String noStr = req.getParameter("no");

        if (studentNo == null || subjectCd == null) {
            req.setAttribute("error", "パラメータが不足しています。");
            req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
            return;
        }

        int no = 1;
        if (noStr != null && !noStr.equals("")) {
            no = Integer.parseInt(noStr);
        }

        TestDao dao = new TestDao();
        Test test = dao.get(studentNo, subjectCd, school, no);

        // ★ 指定回数が無ければ別回数を探す（1→2、2→1）
        if (test == null) {
            if (no == 1) {
                test = dao.get(studentNo, subjectCd, school, 2);
            } else {
                test = dao.get(studentNo, subjectCd, school, 1);
            }
        }

        if (test == null) {
            req.setAttribute("error", "該当する成績が存在しません。");
        } else {
            req.setAttribute("test", test);
        }

        req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
    }
}