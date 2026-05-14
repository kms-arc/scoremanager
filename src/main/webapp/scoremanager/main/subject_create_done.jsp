<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 共通テンプレートの読み込み --%>
<c:import url="/common/base.jsp">
  <c:param name="title">科目情報登録</c:param>
  <c:param name="content">
    
    <%-- No.1：画面タイトル (h2) --%>
	<h2 style="background-color: #dcdcdc; padding: 3px 10px; font-size: 1.1rem; margin: -30px 0 20px 0; width: 100%; box-sizing: border-box; line-height: 1.5;">
  	 科目情報登録
	</h2>	
    <div style="margin-top: 10px;">
      <%-- No.2：完了メッセージ (p) --%>
      <p style="background-color: #a3cfbb; color: #084298; padding: 8px; border-radius: 4px; margin-bottom: 30px;">
        登録が完了しました
      </p>
      
      <%-- 下部のナビゲーション --%>
      <div style="display: flex; gap: 30px; margin-left: 10px;">
        <%-- No.3：戻るリンク (a) --%>
        <a href="SubjectCreate.action" style="color: #007bff; text-decoration: none;">戻る</a>
        
        <%-- No.4：科目一覧リンク (a) --%>
        <a href="SubjectMenu.action" style="color: #007bff; text-decoration: none;">科目一覧</a>
      </div>
    </div>
    
  </c:param>
</c:import>