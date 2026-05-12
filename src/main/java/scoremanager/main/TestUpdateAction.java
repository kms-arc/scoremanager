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

        TestDao dao = new TestDao();

        Test test1 = dao.get(studentNo, subjectCd, school, 1);
        Test test2 = dao.get(studentNo, subjectCd, school, 2);

        req.setAttribute("test1", test1);
        req.setAttribute("test2", test2);

        // student_no subject_cd をjspで使うため渡す
        req.setAttribute("studentNo", studentNo);
        req.setAttribute("subjectCd", subjectCd);

        req.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(req, res);
    }
}