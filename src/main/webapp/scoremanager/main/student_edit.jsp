<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">学生編集</c:param>
  <c:param name="menuTitle">学生編集</c:param>
  <c:param name="content">

    <h2>学生情報編集</h2>

    <form action="UpdateStudent.action" method="post" style="margin-bottom:20px;">
      <input type="hidden" name="no" value="${student.no}">

      <div>
        <label>氏名</label><br>
        <input type="text" name="name" value="${student.name}">
      </div>

      <div>
        <label>入学年度</label><br>
        <input type="number" name="entYear" value="${student.entYear}">
      </div>

      <div>
        <label>クラス</label><br>
        <input type="text" name="classNum" value="${student.classNum}">
      </div>

      <div>
        <label>
          <input type="checkbox" name="attend" value="true"
            <c:if test="${student.attend}">checked</c:if>>
          在学中
        </label>
      </div>

      <br>
      <button type="submit">更新</button>
    </form>

    <br>
    <a href="StudentList.action">戻る</a>

  </c:param>
</c:import>
