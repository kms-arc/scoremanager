package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // JSPのname属性に合わせて取得
        String entYearStr = request.getParameter("ent_year");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");

        StudentDao sDao = new StudentDao();
        Map<String, String> errors = new HashMap<>();

        // 入学年度チェック
        int entYear = 0;
        if (entYearStr == null || entYearStr.isEmpty()) {
            errors.put("ent_year", "入学年度を選択してください");
        } else {
            entYear = Integer.parseInt(entYearStr);
        }

        // 学生番号重複チェック
        // StudentDao.java の find(String no, School school) に合わせる
        if (errors.isEmpty()) {
            Student existing = sDao.find(no, teacher.getSchool()); 
            if (existing != null) {
                errors.put("no", "学生番号が重複しています");
            }
        }

        if (!errors.isEmpty()) {
            // エラーがある場合は入力値を保持して入力画面に戻る
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("ent_year", entYearStr);
            request.setAttribute("class_num", classNum);
            
            // リストの再取得が必要なため、StudentCreateActionのロジックを通す
            new StudentCreateAction().execute(request, response);
            return;
        }

        // 登録用データの作成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(true); // 初期状態は在学中
        student.setSchool(teacher.getSchool());

        // StudentDao.java の insert メソッドを呼び出し
        sDao.insert(student);

        // 完了画面へ
        request.getRequestDispatcher("/scoremanager/main/student_create_done.jsp").forward(request, response);
    }
}