<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="menuTitle">科目情報登録</c:param>
  <c:param name="content">
    
    <%-- ★追加：エラーメッセージの表示エリア --%>
    <c:if test="${not empty error}">
      <div style="color: red; margin-left: 20px; margin-bottom: 15px;">
        ${error}
      </div>
    </c:if>
    
    <form action="SubjectCreateExecute.action" method="post" style="margin-left: 20px;">
      
      <%-- ② 科目コード見出し --%>
      <div style="margin-bottom: 5px;">科目コード</div>
      <%-- ③ 入力フィールド --%>
      <div style="margin-bottom: 20px;">
        <%-- ★修正：value="${cd}" を追加（エラー時に値を保持） --%>
        <input type="text" name="cd" value="${cd}" maxlength="3" placeholder="科目コードを入力してください" 
               style="width: 100%; max-width: 600px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;" required>
      </div>
      
      <%-- ④ 科目名見出し --%>
      <div style="margin-bottom: 5px;">科目名</div>
      <%-- ⑤ 入力フィールド --%>
      <div style="margin-bottom: 20px;">
        <%-- 修正：value="${name}" を追加（エラー時に値を保持） --%>
        <input type="text" name="name" value="${name}" placeholder="科目名を記入してください" 
               style="width: 100%; max-width: 600px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;" required>
      </div>
      
      <%-- ⑥ 登録ボタン --%>
      <div style="margin-bottom: 10px;">
        <button type="submit" style="background-color: #007bff; color: white; border: none; padding: 8px 20px; border-radius: 4px; cursor: pointer;">登録</button>
      </div>
      
      <%-- ⑦ 戻るリンク --%>
      <div>
        <a href="SubjectMenu.action" style="color: #007bff; text-decoration: none;">戻る</a>
      </div>
      
    </form>
    
  </c:param>
</c:import>