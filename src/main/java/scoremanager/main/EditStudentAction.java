package scoremanager.main;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class EditStudentAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = new School();
        school.setCd("tes");
        school.setName("テスト学校");

        String no = req.getParameter("no");

        StudentDao dao = new StudentDao();
        Student s = dao.find(no, school);

        req.setAttribute("student", s);

        req.getRequestDispatcher("/scoremanager/main/student_edit.jsp")
           .forward(req, res);
    }
}
