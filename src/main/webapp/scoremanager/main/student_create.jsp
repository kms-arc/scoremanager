<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">学生管理</c:param>
  <c:param name="menuTitle">学生管理</c:param>
  <c:param name="content">

<h2>学生新規登録</h2>

<form action="StudentCreateExecute.action" method="post">

  <div>
    <label>学生番号</label><br>
    <input type="text" name="no" style="width:200px;">
  </div>

  <div>
    <label>氏名</label><br>
    <input type="text" name="name" style="width:200px;">
  </div>

  <div>
    <label>入学年度</label><br>
    <input type="number" name="entYear" style="width:200px;">
  </div>

  <div>
    <label>クラス</label><br>
    <input type="text" name="classNum" style="width:200px;">
  </div>

  <div>
    <label>在学中</label>
    <input type="checkbox" name="attend" value="true">
  </div>

  <br>
  <button type="submit">登録</button>

</form>

<br>
<a href="StudentList.action">戻る</a>

</c:param>
</c:import>

