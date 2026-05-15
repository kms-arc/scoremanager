package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // DAOを書き換えないため、既存の filter(School) メソッドをそのまま使用
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> list = cNumDao.filter(teacher.getSchool());

        // 入学年度の選択肢を作成
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // リクエスト属性にセット（JSP側の名前に合わせる）
        request.setAttribute("class_num_set", list);
        request.setAttribute("ent_year_set", entYearSet);

        // フォワード
        request.getRequestDispatcher("/scoremanager/main/student_create.jsp").forward(request, response);
    }
}