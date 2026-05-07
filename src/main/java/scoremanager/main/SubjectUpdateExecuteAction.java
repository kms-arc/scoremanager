package scoremanager.main;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	//科目コードと科目名を取得
    	String cd = req.getParameter("cd");
    	String name = req.getParameter("name");
    	
    	//オブジェクトに科目コード科目名をセット
    	Subject subject = new Subject();
    	subject.setCd(cd);
    	subject.setName(name);
    	
    	School school = new School();
    	school.setCd(req.getParameter("school_cd"));
    	subject.setSchool(school);
    	
    	//更新を実行
    	SubjectDao dao = new SubjectDao();
    	int result = dao.update(subject);
    	
    	//すでに削除されてる場合
    	if (result == 0) {
    		req.setAttribute("subject", subject);
    	    req.setAttribute("error", "科目が存在していません");
    	    req.getRequestDispatcher("/scoremanager/main/subject_update.jsp")
    	       .forward(req, res);
    	    return;
    	}
    	
    	//完了画面にフォワード
    	req.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp")
    	    .forward(req, res);
    }
}
