package scoremanager.main;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestUpdateExecuteAction extends Action {

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

        String point1Str = req.getParameter("point1");
        String point2Str = req.getParameter("point2");

        TestDao dao = new TestDao();

        // class_num を取るために既存データから拾う
        Test base = dao.get(studentNo, subjectCd, school, 1);
        if (base == null) {
            base = dao.get(studentNo, subjectCd, school, 2);
        }

        if (base == null) {
            req.setAttribute("error", "成績データが存在しません。");
            req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
            return;
        }

        String classNum = base.getClassNum();

        // --- 1回目 ---
        if (point1Str == null || point1Str.equals("")) {
            dao.delete(studentNo, subjectCd, school, 1);
        } else {
            int p1 = Integer.parseInt(point1Str);
            if (p1 < 0 || p1 > 100) {
                req.setAttribute("error", "点数は0～100で入力してください");
                req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
                return;
            }
            dao.upsertPoint(studentNo, subjectCd, school, 1, p1, classNum);
        }

        // --- 2回目 ---
        if (point2Str == null || point2Str.equals("")) {
            dao.delete(studentNo, subjectCd, school, 2);
        } else {
            int p2 = Integer.parseInt(point2Str);
            if (p2 < 0 || p2 > 100) {
                req.setAttribute("error", "点数は0～100で入力してください");
                req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
                return;
            }
            dao.upsertPoint(studentNo, subjectCd, school, 2, p2, classNum);
        }

        req.getRequestDispatcher("/scoremanager/main/test_update_done.jsp").forward(req, res);
    }
}