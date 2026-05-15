package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        School school = teacher.getSchool();

        // フォーム再表示のため常にドロップダウンデータをセット
        req.setAttribute("class_num_set", new ClassNumDao().filter(school));
        req.setAttribute("subjects",      new SubjectDao().filter(school));
        req.setAttribute("ent_year_set",  generateEntYearList());

        String f = req.getParameter("f");

        if ("sj".equals(f)) {
            // 科目情報検索
            req.setAttribute("f", "sj");

            int entYear = Integer.parseInt(req.getParameter("f1"));
            String classNum = req.getParameter("f2");
            String subjectCd = req.getParameter("f3");

            if (entYear == 0 || "0".equals(classNum) || subjectCd == null || subjectCd.isEmpty()) {
                req.setAttribute("error", "入学年度とクラスと科目を選択してください");
            } else {
                Subject subject = new Subject();
                subject.setCd(subjectCd);
                subject.setSchool(school);
                req.setAttribute("testListSubjects", new TestListSubjectDao().filter(entYear, classNum, subject, school));
            }

        } else if ("st".equals(f)) {
            // 学生情報検索
            req.setAttribute("f", "st");

            String studentNo = req.getParameter("f4");
            req.setAttribute("f4", studentNo);

            if (studentNo == null || studentNo.trim().isEmpty()) {
                req.setAttribute("error", "学生番号を入力してください");
            } else {
                studentNo = studentNo.trim();

                StudentDao studentDao = new StudentDao();
                Student target = studentDao.find(studentNo, school);

                if (target == null) {
                    req.setAttribute("error", "該当する学生情報が存在しませんでした。");
                } else {
                    req.setAttribute("student", target);

                    Student student = new Student();
                    student.setNo(studentNo);
                    student.setSchool(school);

                    List<?> list = new TestListStudentDao().filter(student);

                    if (list == null || list.isEmpty()) {
                        req.setAttribute("message", "成績情報が存在しませんでした。");
                    } else {
                        req.setAttribute("testListStudents", list);
                    }
                }
            }
        }

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