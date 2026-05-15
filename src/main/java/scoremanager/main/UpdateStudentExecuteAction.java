package scoremanager.main;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UpdateStudentExecuteAction extends Action {
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        School school = new School();
        school.setCd("tes");

        // JSPのname属性に合わせる
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        int entYear = Integer.parseInt(req.getParameter("ent_year")); 
        String classNum = req.getParameter("class_num"); 
        boolean attend = "true".equals(req.getParameter("is_attend")); 

        Student s = new Student();
        s.setNo(no);
        s.setName(name);
        s.setEntYear(entYear);
        s.setClassNum(classNum);
        s.setAttend(attend);
        s.setSchool(school);

        StudentDao dao = new StudentDao();
        dao.update(s);

        req.getRequestDispatcher("/scoremanager/main/student_update_done.jsp").forward(req, res);
    }
}