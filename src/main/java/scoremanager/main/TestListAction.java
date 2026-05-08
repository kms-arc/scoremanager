package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからユーザーデータを取得（事前条件：ログイン状態であること）
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        School school = teacher.getSchool();

        // ユーザーが所属している学校のクラス一覧、科目一覧を取得
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        req.setAttribute("class_num_set", classNumDao.filter(school));
        req.setAttribute("subjects",      subjectDao.filter(school));

        // 入学年度リストを生成（現在年度から過去6年分）
        req.setAttribute("ent_year_set", generateEntYearList());

        // 成績参照検索画面を表示
        req.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(req, res);
    }

    /** 入学年度リスト生成（現在年度から過去6年分） */
    private List<Integer> generateEntYearList() {
        int currentYear = LocalDate.now().getYear();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(currentYear - i);
        }
        return list;
    }
}