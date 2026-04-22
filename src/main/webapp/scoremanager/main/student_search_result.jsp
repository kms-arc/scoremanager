<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">学生管理</c:param>
  <c:param name="menuTitle">学生管理</c:param>
  <c:param name="content">

    <div style="text-align:right; margin-bottom:10px;">
      <a href="StudentCreate.action">新規登録</a>
    </div>

<h2>検索結果</h2>

<c:choose>
  <c:when test="${students.size() > 0}">
    <div>検索結果：${students.size()}件</div>

    <table border="1" style="width:100%; margin-top:10px;">
      <tr>
        <th>入学年度</th>
        <th>学生番号</th>
        <th>氏名</th>
        <th>クラス</th>
        <th>在学中</th>
      </tr>

      <c:forEach var="s" items="${students}">
        <tr>
          <td>${s.entYear}</td>
          <td>${s.no}</td>
          <td>${s.name}</td>
          <td>${s.classNum}</td>
          <td>
            <c:choose>
              <c:when test="${s.attend}">○</c:when>
              <c:otherwise>×</c:otherwise>
            </c:choose>
          </td>
        </tr>
      </c:forEach>
    </table>
  </c:when>

  <c:otherwise>
    <div style="color:red;">該当する学生はいませんでした。</div>
  </c:otherwise>
</c:choose>

<br>
<a href="StudentList.action">検索画面に戻る</a>
  </c:param>
</c:import>
