package scoremanager.main;

import bean.School;
import bean.Teacher;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteExecuteAction extends Action {

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

		TestDao dao = new TestDao();
		dao.delete(studentNo, subjectCd, school, no);

		req.getRequestDispatcher("/scoremanager/main/test_delete_done.jsp").forward(req, res);
	}
}