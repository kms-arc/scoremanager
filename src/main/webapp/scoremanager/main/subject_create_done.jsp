<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="menuTitle">
    <%-- ① タイトル部分：背景グレー --%>
    <div style="background-color: #eee; padding: 10px; margin-bottom: 20px; font-weight: bold; border-radius: 4px;">科目情報登録</div>
  </c:param>
  <c:param name="content">
    
    <div style="margin-top: 10px;">
      <%-- ② 登録完了メッセージ：緑の背景バー --%>
      <div style="background-color: #a3cfbb; color: #084298; padding: 15px; border-radius: 4px; margin-bottom: 30px;">
        登録が完了しました
      </div>
      
      <%-- ③・④ 下部のナビゲーションリンク --%>
      <div style="display: flex; gap: 30px; margin-left: 10px;">
        <a href="SubjectCreate.action" style="color: #007bff; text-decoration: none;">戻る</a>
        <a href="SubjectMenu.action" style="color: #007bff; text-decoration: none;">科目一覧</a>
      </div>
    </div>
    
  </c:param>
</c:import>