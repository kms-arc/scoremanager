package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentSearchResultAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        School school = new School();
        school.setCd("tes");
        school.setName("テスト学校");

        // パラメータ取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String attendStr = req.getParameter("f3");

        int entYear = 0;
        boolean isAttend = false;

        if (entYearStr != null && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (attendStr != null) {
            isAttend = true;
        }

        StudentDao sDao = new StudentDao();

        // ★ 絞り込み検索
        List<Student> students = sDao.search(school, entYear, classNum, isAttend);

        req.setAttribute("students", students);

        req.getRequestDispatcher("/scoremanager/main/student_search_result.jsp")
           .forward(req, res);
    }
}
