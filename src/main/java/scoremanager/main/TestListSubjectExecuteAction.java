package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        School school = teacher.getSchool();

        int entYear = Integer.parseInt(req.getParameter("f1"));
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");

        // 再表示用ドロップダウンデータ（フォームを再描画するために必要）
        req.setAttribute("class_num_set", new ClassNumDao().filter(school));
        req.setAttribute("subjects",      new SubjectDao().filter(school));
        req.setAttribute("ent_year_set",  generateEntYearList());

        // バリデーション：入学年度・クラス・科目がすべて選択されているか
        if (entYear == 0 || "0".equals(classNum) || subjectCd == null || subjectCd.isEmpty()) {
            req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            req.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(req, res);
            return;
        }

        Subject subject = new Subject();
        subject.setCd(subjectCd);
        subject.setSchool(school);

        List<?> result = new TestListSubjectDao().filter(entYear, classNum, subject, school);
        req.setAttribute("testListSubjects", result);
        req.setAttribute("f", "sj");

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
