<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">科目情報登録</c:param>
  <c:param name="menuTitle">科目情報登録</c:param>
  <c:param name="content">
    
    <div style="margin-top: 10px;">
      <%-- 登録完了メッセージ　--%>
      <div style="background-color: #a3cfbb; color: #084298; padding: 8px; border-radius: 4px; margin-bottom: 30px;">
        登録が完了しました
      </div>
      
      <%--　下部のナビゲーションリンク --%>
      <div style="display: flex; gap: 30px; margin-left: 10px;">
        <a href="SubjectCreate.action" style="color: #007bff; text-decoration: none;">戻る</a>
        <a href="SubjectMenu.action" style="color: #007bff; text-decoration: none;">科目一覧</a>
      </div>
    </div>
    
  </c:param>
</c:import>