package scoremanager.main;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UpdateStudentAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = new School();
        school.setCd("tes");
        school.setName("テスト学校");

        String no = req.getParameter("no");
        String name = req.getParameter("name");
        int entYear = Integer.parseInt(req.getParameter("entYear"));
        String classNum = req.getParameter("classNum");
        boolean attend = req.getParameter("attend") != null;

        Student s = new Student();
        s.setNo(no);
        s.setName(name);
        s.setEntYear(entYear);
        s.setClassNum(classNum);
        s.setAttend(attend);
        s.setSchool(school);

        StudentDao dao = new StudentDao();
        dao.update(s);

        req.getRequestDispatcher("/scoremanager/main/student_update_done.jsp")
           .forward(req, res);
    }
}
