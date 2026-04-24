package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        // セッションキー取得
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        
        // 画面からの入力値を受け取る
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        
        SubjectDao sDao = new SubjectDao();
        
        // 同じ科目コードがすでに登録されているかDBに確認
        Subject check = sDao.get(cd, teacher.getSchool());
        
        if (check != null) {
            // すでに存在する場合：エラーメッセージと入力した値をセットして元の画面に戻す
            req.setAttribute("error", "科目コードが重複しています。");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            
            // ForwardでJSP（登録画面）に戻る
            req.getRequestDispatcher("/scoremanager/main/subject_create.jsp").forward(req, res);
            return;
        }
        
        // 存在しない場合は、新規作成してDBに保存
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());
        
        sDao.save(subject);
        
        // 保存が終わったら一覧画面へリダイレクト
        res.sendRedirect("SubjectMenu.action");
    }
}