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
      
     // 未入力チェック
        if (no == null || no.isEmpty()) {
            errors.put("no", "学生番号を入力してください");
        }
        if (name == null || name.isEmpty()) {
            errors.put("name", "氏名を入力してください");
        }

        // 入学年度チェック
        int entYear = 0;
        if (entYearStr == null || entYearStr.isEmpty()) {
            errors.put("ent_year", "入学年度を選択してください");
        } else {
            entYear = Integer.parseInt(entYearStr);
        }

        // 学生番号重複チェック
        // StudentDao.java の find(String no, School school) に合わせる
        if (!errors.isEmpty()) {
            request.setAttribute("error", errors.values().iterator().next());
            
            // エラーがある場合は入力値を保持
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("ent_year", entYearStr);
            request.setAttribute("class_num", classNum);

            // ここから追加：クラスリストを再取得してセット 
            dao.ClassNumDao cNumDao = new dao.ClassNumDao();
            java.util.List<String> class_num_set = cNumDao.filter(teacher.getSchool());
            request.setAttribute("class_num_set", class_num_set);
            
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