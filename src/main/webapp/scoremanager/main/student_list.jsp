<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">学生管理</c:param>
  <c:param name="menuTitle">学生管理</c:param>
  <c:param name="content">

    <div style="text-align:right; margin-bottom:10px;">
      <a href="StudentCreate.action">新規登録</a>
    </div>

    <form action="StudentSearchResult.action" method="get">
      <div style="border:1px solid #ccc; padding:15px; margin-bottom:20px; display:flex; gap:30px;">

        <div>
          <label>入学年度</label><br>
          <select name="f1" style="width:160px; height:28px;">
            <option value="0">----</option>
            <c:forEach var="year" items="${ent_year_set}">
              <option value="${year}">${year}</option>
            </c:forEach>
          </select>
        </div>

        <div>
          <label>クラス</label><br>
          <select name="f2" style="width:160px; height:28px;">
            <option value="0">----</option>
            <c:forEach var="num" items="${class_num_set}">
              <option value="${num}">${num}</option>
            </c:forEach>
          </select>
        </div>

        <div>
          <label><input type="checkbox" name="f3" value="t"> 在学中</label>
        </div>

        <div>
          <button type="submit">絞り込み</button>
        </div>

      </div>
    </form>

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
            <th>編集</th>
          </tr>

          <c:forEach var="s" items="${students}">
            <tr>
              <td>${s.entYear}</td>
              <td>${s.no}</td>
              <td>${s.name}</td>
              <td>${s.classNum}</td>
              <td><c:if test="${s.attend}">○</c:if><c:if test="${!s.attend}">×</c:if></td>
              <td><a href="EditStudent.action?no=${s.no}">編集</a></td>
            </tr>
          </c:forEach>
        </table>
      </c:when>

      <c:otherwise>
        <div style="color:red;">学生情報が存在しませんでした。</div>
      </c:otherwise>
    </c:choose>

  </c:param>
</c:import>
