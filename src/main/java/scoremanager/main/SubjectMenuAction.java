package scoremanager.main;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectMenuAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログイン中の先生情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // --- 修正ポイント：nullチェック ---
        System.out.println("セッションの中身(user): " + session.getAttribute("user"));
        if (teacher == null) {
            res.sendRedirect("Login.action");
            return;
        }

        // 先生が所属している学校の科目一覧を取得
        SubjectDao sDao = new SubjectDao();
        // teacherがnullでないことが確定したので、安心して呼び出せる
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        // リクエストに "subjects" という名前でリストを保存
        req.setAttribute("subjects", subjects);

        // JSPへ移動
        req.getRequestDispatcher("/scoremanager/main/subject_list.jsp").forward(req, res);
    }
}