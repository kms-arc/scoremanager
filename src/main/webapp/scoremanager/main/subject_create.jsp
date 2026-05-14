<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="content">
    <%-- No.1：画面タイトル --%>
	<h2 style="background-color: #dcdcdc; padding: 6px 10px; font-size: 1.2rem; margin-top: -30px; margin-bottom: 20px; width: 100%; box-sizing: border-box;">
      科目情報登録
    </h2>
    
    <form action="SubjectCreateExecute.action" method="post" style="margin-left: 20px;">
      
      <%-- No.2/3：科目コード --%>
      <div style="margin-bottom: 5px;">科目コード</div>
      <div style="margin-bottom: 5px;">
        <input type="text" name="cd" value="${cd}" maxlength="3" placeholder="科目コードを入力してください" 
               style="width: 100%; max-width: 600px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;" required>
      </div>

      <%-- エラーメッセージ：科目コードの下にオレンジで表示 --%>
      <c:if test="${not empty error}">
        <div style="color: orange; font-size: 0.9rem; margin-bottom: 15px;">
          ${error}
        </div>
      </c:if>

      <%-- No.4|5：科目名 --%>
      <div style="margin-bottom: 5px;">科目名</div>
      <div style="margin-bottom: 20px;">
        <input type="text" name="name" value="${name}" maxlength="20" placeholder="科目名を記入してください" 
               style="width: 100%; max-width: 600px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;" required>
      </div>
      
      <%-- No.6：登録ボタン (input type="submit") --%>
      <div style="margin-bottom: 10px;">
        <input type="submit" value="登録" 
               style="background-color: #007bff; color: white; border: none; padding: 8px 20px; border-radius: 4px; cursor: pointer;">
      </div>
      
      <%-- No.7：戻るリンク --%>
      <div>
        <a href="SubjectMenu.action" style="color: #007bff; text-decoration: none;">戻る</a>
      </div>
      
    </form>
  </c:param>
</c:import>