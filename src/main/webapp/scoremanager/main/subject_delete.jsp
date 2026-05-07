<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">科目編集</c:param>
  <c:param name="menuTitle">科目編集</c:param>
  <c:param name="content">
  
  <h2>科目情報削除</h2>
  
   	<form action="SubjectDeleteExecute.action" method="post" style="margin-bottom:20px;">
   	<input type="hidden" name="cd" value="${subject.cd}">
	<input type="hidden" name="school_cd" value="${subject.school.cd}">
   	
  		<p>${subject.name}(${subject.cd})を削除してよろしいですか？</p>
  		<button type="submit">削除</button>
  	</form>
  	
  	<br>
  	<a href="SubjectMenu.action">戻る</a>
  
  </c:param>
</c:import>