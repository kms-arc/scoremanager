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
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        
        // 画面からの入力値を受け取る
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        
        // 文字数チェック（3文字以外はエラー）
        if (cd != null && cd.length() != 3) {
            req.setAttribute("error", "科目コードは3文字で入力してください");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            
            req.getRequestDispatcher("/scoremanager/main/subject_create.jsp").forward(req, res);
            return;
        }
        
        SubjectDao sDao = new SubjectDao();
        
        // 重複チェック 
        Subject check = sDao.get(cd, teacher.getSchool());
        if (check != null) {
            req.setAttribute("error", "科目コードが重複しています。");
            req.setAttribute("cd", cd);
            req.setAttribute("name", name);
            
            req.getRequestDispatcher("/scoremanager/main/subject_create.jsp").forward(req, res);
            return;
        }
        
        // 全てクリアしたら保存
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());
        
        sDao.save(subject);
        
        // 完了画面へ
        req.getRequestDispatcher("/scoremanager/main/subject_create_done.jsp").forward(req, res);
    }
}