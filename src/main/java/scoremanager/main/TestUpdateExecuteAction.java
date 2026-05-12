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
        int no = Integer.parseInt(req.getParameter("no"));

        String pointStr = req.getParameter("point");

        // 未入力チェック
        if (pointStr == null || pointStr.equals("")) {
            req.setAttribute("error", "点数を入力してください");

            TestDao dao = new TestDao();
            Test test = dao.get(studentNo, subjectCd, school, no);
            req.setAttribute("test", test);

            req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
            return;
        }

        int point = Integer.parseInt(pointStr);

        // 範囲チェック
        if (point < 0 || point > 100) {
            req.setAttribute("error", "点数は0～100で入力してください");

            TestDao dao = new TestDao();
            Test test = dao.get(studentNo, subjectCd, school, no);
            req.setAttribute("test", test);

            req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
            return;
        }

        TestDao dao = new TestDao();

        // class_num を取得するために既存データを探す
        Test base = dao.get(studentNo, subjectCd, school, 1);
        if (base == null) {
            base = dao.get(studentNo, subjectCd, school, 2);
        }

        // どちらも無い場合はエラー
        if (base == null) {
            req.setAttribute("error", "成績データが存在しません。");
            req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
            return;
        }

        // UPDATEできなければINSERT
        dao.upsertPoint(studentNo, subjectCd, school, no, point, base.getClassNum());

        req.getRequestDispatcher("/scoremanager/main/test_update_done.jsp").forward(req, res);
    }
}