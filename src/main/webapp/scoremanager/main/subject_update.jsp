<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">科目編集</c:param>
  <c:param name="menuTitle">科目編集</c:param>
  <c:param name="content">
  
  <h2>科目コード</h2>
  
   	<form action="SubjectUpdateExecute.action" method="post" style="margin-bottom:20px;">
   		<input type="hidden" name="school_cd" value="${subject.school.cd}">
  		
  		<!-- 科目コードは編集不可 -->
  		<div>
  		<label>科目コード</label><br>
  		<input type="text" name="cd" value="${subject.cd}" readonly>
  		</div>
  		
  		<!-- 科目が存在しないときのエラーメッセージ-->
  		<c:if test="${not empty error}">
  			<div style="color:red;">
  				${error}
  			</div>
		</c:if>
  		
  		<!-- 科目名編集。未入力はエラー-->
  		<div>
  		<label>科目名</label><br>
  		<input type="text" name="name" value="${subject.name}" 
  			placeholder="科目名を記入してください"
  			style="width: 100%; max-width: 600px; padding: 8px; border: 1px solid #ccc; border-radius: 4px;" required>
  		</div>
  		
  		<br>
  		<button type="submit">変更</button>
  	</form>
  	
  	<br>
  	<a href="SubjectMenu.action">戻る</a>
  
  </c:param>
</c:import>





