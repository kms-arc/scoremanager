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

        // 1. 学校情報の取得（実際はセッションから取得するのが望ましいですが、一旦固定でOK）
        School school = new School();
        school.setCd("tes");
        school.setName("テスト学校");

        StudentDao sDao = new StudentDao();
        ClassNumDao cDao = new ClassNumDao();

        // --- ここから検索条件の受け取り処理を追加 ---
        
        // JSPのname属性(f1, f2, f3)に合わせてパラメータを取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String isAttendStr = req.getParameter("f3");

        int entYear = 0;
        boolean isAttend = false;

        // 入学年度(f1)が選択されていたら数値に変換
        if (entYearStr != null && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }
        // 在学中チェック(f3)がついていたら true
        if (isAttendStr != null) {
            isAttend = true;
        }

        // データの取得 
        
        // 年度一覧とクラス一覧（これは常に全件必要）
        List<Integer> entYearList = sDao.getEntYearList(school);
        List<String> classNumList = cDao.filter(school);

        // ★ 学生一覧を「条件付き」で取得するように修正
        List<Student> students = sDao.search(school, entYear, classNum, isAttend);

        //  JSP に渡す
        req.setAttribute("f1", entYear);      // 選択状態を保持するために追加
        req.setAttribute("f2", classNum);     // 選択状態を保持するために追加
        req.setAttribute("f3", isAttendStr);  // 選択状態を保持するために追加
        
        req.setAttribute("ent_year_set", entYearList);
        req.setAttribute("class_num_set", classNumList);
        req.setAttribute("students", students);

        req.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(req, res);
    }
}