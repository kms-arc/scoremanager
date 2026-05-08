package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        School school = teacher.getSchool();

        String studentNo = req.getParameter("f4");

        // 再表示用ドロップダウンデータ（フォームを再描画するために必要）
        req.setAttribute("class_num_set", new ClassNumDao().filter(school));
        req.setAttribute("subjects",      new SubjectDao().filter(school));
        req.setAttribute("ent_year_set",  generateEntYearList());
        req.setAttribute("f4", studentNo); // 入力値を保持して再表示

        // バリデーション：学生番号が入力されているか
        if (studentNo == null || studentNo.trim().isEmpty()) {
            req.setAttribute("error", "学生番号を入力してください");
            req.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(req, res);
            return;
        }

        Student student = new Student();
        student.setNo(studentNo.trim());
        student.setSchool(school);

        List<?> result = new TestListStudentDao().filter(student);
        req.setAttribute("testListStudents", result);
        req.setAttribute("f", "st");

        req.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(req, res);
    }

    private List<Integer> generateEntYearList() {
        int currentYear = LocalDate.now().getYear();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(currentYear - i);
        }
        return list;
    }
}
