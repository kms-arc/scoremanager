package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class UpdateStudentAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 1. 学校情報の固定設定
        School school = new School();
        school.setCd("tes");
        school.setName("テスト学校");

        // 2. 一覧画面から送られてきた「学生番号(no)」を受け取る
        String no = req.getParameter("no");

        StudentDao sDao = new StudentDao();
        
        // 3. DBから該当する学生の現在の情報を取得する
        Student student = sDao.find(no, school);

        if (student != null) {
            req.setAttribute("no", student.getNo());
            req.setAttribute("name", student.getName());
            req.setAttribute("ent_year", student.getEntYear());
            req.setAttribute("class_num", student.getClassNum());
            req.setAttribute("is_attend", student.isAttend());

            // --- DAOを修正せず、既存のfilterメソッドでクラス一覧を作る ---
            List<Student> allStudents = sDao.filter(school); // 全生徒取得
            List<String> class_num_set = new ArrayList<>();
            for (Student s : allStudents) {
                String cNum = s.getClassNum();
                if (cNum != null && !class_num_set.contains(cNum)) {
                    class_num_set.add(cNum); // 重複してなければ追加
                }
            }
            java.util.Collections.sort(class_num_set); // 101, 102のように並び替え
            req.setAttribute("class_num_set", class_num_set);
            req.setAttribute("ent_year_set", sDao.getEntYearList(school));
        } else {
            req.setAttribute("error", "学生情報が見つかりませんでした。");
        }

        // 5. 編集画面（student_update.jsp）へ移動する
        req.getRequestDispatcher("/scoremanager/main/student_edit.jsp").forward(req, res);
    }
}