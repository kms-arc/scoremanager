package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ★ 学校情報（ログイン無しのため固定）
        School school = new School();
        school.setCd("tes");   // DB の SCHOOL_CD
        school.setName("テスト学校");

        StudentDao sDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();

        // ★ 年度一覧を DB から取得
        List<Integer> entYearList = sDao.getEntYearList(school);

        // ★ クラス一覧を DB から取得
        List<String> classNumList = cDao.filter(school);

        // ★ 学生一覧（全件）
        List<Student> students = sDao.filter(school);

        // ★ JSP に渡す
        req.setAttribute("ent_year_set", entYearList);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("students", students);

        req.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(req, res);
    }
}
