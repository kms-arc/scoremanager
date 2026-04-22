package scoremanager.main;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = new School();
        school.setCd("tes");
        school.setName("テスト学校");

        // 入力値取得
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        int entYear = Integer.parseInt(req.getParameter("entYear"));
        String classNum = req.getParameter("classNum");
        boolean attend = req.getParameter("attend") != null;

        // Student オブジェクト作成
        Student s = new Student();
        s.setNo(no);
        s.setName(name);
        s.setEntYear(entYear);
        s.setClassNum(classNum);
        s.setAttend(attend);
        s.setSchool(school);

        // DB 登録
        StudentDao dao = new StudentDao();
        dao.insert(s);

        req.getRequestDispatcher("/scoremanager/main/student_create_done.jsp")
           .forward(req, res);
    }
}
