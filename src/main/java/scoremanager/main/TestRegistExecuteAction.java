package scoremanager.main;

import java.util.Enumeration;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = new School();
        school.setCd("tes");

        String subjectCd = req.getParameter("subjectCd");
        String classNum = req.getParameter("classNum");
        int no = Integer.parseInt(req.getParameter("count"));

        TestDao dao = new TestDao();

        // ★ score_XXXX の形式で送られてくる点数をすべて処理
        Enumeration<String> names = req.getParameterNames();

        while (names.hasMoreElements()) {

            String name = names.nextElement();

            if (name.startsWith("score_")) {

                String studentNo = name.substring(6);
                String pointStr = req.getParameter(name);

                Integer point = null;
                if (pointStr != null && !pointStr.isEmpty()) {
                    point = Integer.parseInt(pointStr);
                }

                // ★ 既存チェック
                Test existing = dao.get(studentNo, subjectCd, school, no);

                if (existing == null) {

                    // INSERT
                    Test t = new Test();

                    Student s = new Student();
                    s.setNo(studentNo);
                    s.setSchool(school);

                    Subject sub = new Subject();
                    sub.setCd(subjectCd);
                    sub.setSchool(school);

                    t.setStudent(s);
                    t.setSubject(sub);
                    t.setSchool(school);
                    t.setNo(no);
                    t.setPoint(point);
                    t.setClassNum(classNum);

                    dao.insert(t);

                } else {

                    // UPDATE
                    dao.updatePoint(studentNo, subjectCd, school, no, point);
                }
            }
        }

        req.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp").forward(req, res);
    }
}
