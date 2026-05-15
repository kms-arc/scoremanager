package tool;
 
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@WebServlet("*.action")
public class FrontController extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
 
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
 
        // try-catchを消して、そのまま実行
        // ここでエラーが起きれば、サーバーが自動でデフォルトのエラー画面を出してくれる
        try {
            String path = req.getServletPath();
            String name = path.substring(1, path.lastIndexOf("."));
            String fqcn = "scoremanager.main." + name + "Action";
 
            Class<?> c = Class.forName(fqcn);
            Action action = (Action) c.getDeclaredConstructor().newInstance();
            action.execute(req, res);
            
        } catch (Exception e) {
            // ブラウザのデフォルト画面に詳細を出すために、
            // RuntimeExceptionに包んで投げ直す（これだけでOK）
            throw new ServletException(e);
        }
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}