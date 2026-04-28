package scoremanager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = new School();
        school.setCd("tes");

        StudentDao sDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();
        TestDao tDao = new TestDao();

        // ★ プルダウン用データ
        req.setAttribute("entYearList", sDao.getEntYearList(school));
        req.setAttribute("classList", cDao.filter(school));
        req.setAttribute("subjectList", subDao.filter(school));
        req.setAttribute("countList", getCountList(tDao, school));

        // ★ パラメータ取得
        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subjectCd");
        String count = req.getParameter("count");

        // ★ 初期表示
        if (entYear == null || classNum == null || subjectCd == null || count == null) {
            req.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(req, res);
            return;
        }

        // ★ 科目情報
        Subject subject = subDao.get(subjectCd, school);
        req.setAttribute("subject", subject);

        // ★ 学生一覧（入学年度＋クラスで絞り込み）
        List<Student> students = sDao.filter(entYear, classNum, school);
        List<Student> resultList = new ArrayList<>();

        for (Student st : students) {

            Test test = tDao.get(st.getNo(), subjectCd, school, Integer.parseInt(count));

            if (test != null) {
                st.setPoint(test.getPoint());
            } else {
                st.setPoint(null);
            }

            resultList.add(st);
        }

        req.setAttribute("studentList", resultList);

        req.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(req, res);
    }

    // ★ TestDao に getCountList が無いのでここで作る
    private List<Integer> getCountList(TestDao dao, School school) throws Exception {

        List<Integer> list = new ArrayList<>();

        try (Connection con = dao.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT DISTINCT NO FROM TEST WHERE SCHOOL_CD = ? ORDER BY NO")) {

            ps.setString(1, school.getCd());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("NO"));
            }
        }

        return list;
    }
}
