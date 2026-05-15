package scoremanager.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        if (no != null && !no.isEmpty()) {
            if (sDao.find(no, teacher.getSchool()) != null) {
                errors.put("no", "学生番号が重複しています");
            }
        }
            
        // エラーがある場合の処理
        if (!errors.isEmpty()) {
            request.setAttribute("error", errors.values().iterator().next());
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("ent_year", entYearStr);
            request.setAttribute("ent_year_selected", entYear); 
            request.setAttribute("class_num", classNum);

            // 重複を排除してクラスリストをセット
            List<Student> allStudents = sDao.filter(teacher.getSchool());
            Set<String> classSet = new HashSet<>();
            for (Student s : allStudents) {
                if (s.getClassNum() != null) {
                    classSet.add(s.getClassNum());
                }
            }
            List<String> class_num_set = new ArrayList<>(classSet);
            Collections.sort(class_num_set);
            request.setAttribute("class_num_set", class_num_set);
            
            // 入学年度リストをセット
            request.setAttribute("ent_year_set", sDao.getEntYearList(teacher.getSchool()));

            // 入力画面へ戻る
            request.getRequestDispatcher("/scoremanager/main/student_create.jsp").forward(request, response);
            return;
        } // ← ここでエラーチェックのブロックを終了

        // --- ここから登録処理（エラーがない場合のみ実行される） ---
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        // 保存実行
        sDao.insert(student);

        // 完了画面へ
        request.getRequestDispatcher("/scoremanager/main/student_create_done.jsp").forward(request, response);
    }
}