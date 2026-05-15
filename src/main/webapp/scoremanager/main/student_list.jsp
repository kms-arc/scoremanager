<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">成績管理システム</c:param>
  <c:param name="content">

    <%-- No.1 画面タイトル (h2) --%>
	<h2 style="background-color: #dcdcdc; padding: 6px 10px; font-size: 1.2rem; margin-top: -30px; margin-bottom: 20px; width: 100%; box-sizing: border-box;">
     学生管理
    </h2>

    <%-- No.8 新規登録リンク --%>
    <div style="text-align:right; margin-bottom:10px;">
      <a href="StudentCreate.action">新規登録</a>
    </div>

    <%-- 絞り込みフォーム --%>
    <form action="StudentList.action" method="get">
      <div style="border:1px solid #ccc; padding:15px; margin-bottom:20px; display:flex; gap:30px; align-items: flex-end;">

        <%-- No.2, 4 入学年度 --%>
        <div>
          <label>入学年度</label><br>
          <select name="f1" style="width:160px; height:28px;">
            <option value="0">----</option>
            <c:forEach var="year" items="${ent_year_set}">
              <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
            </c:forEach>
          </select>
        </div>

        <%-- No.3, 5 クラス --%>
        <div>
          <label>クラス</label><br>
          <select name="f2" style="width:160px; height:28px;">
            <option value="0">----</option>
            <c:forEach var="num" items="${class_num_set}">
              <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
            </c:forEach>
          </select>
        </div>

        <%-- No.6, 7 在学中チェックボックス --%>
        <div>
          <label><input type="checkbox" name="f3" value="t" <c:if test="${not empty f3}">checked</c:if>> 在学中</label>
        </div>

        <%-- No.9 絞り込みボタン --%>
        <div>
          <button type="submit" name="filter">絞り込み</button>
        </div>

      </div>
    </form>

    <c:choose>
      <c:when test="${students.size() > 0}">
        <%-- No.10 検索結果件数 --%>
        <div style="margin-bottom: 5px;">検索結果：${students.size()}件</div>

        <%-- No.11 学生一覧テーブル --%>
        <table border="1" style="width:100%; border-collapse: collapse; margin-top:10px;">
          <tr style="background-color: #f2f2f2;">
            <%-- No.12〜16 ヘッダー --%>
            <th>入学年度</th>
            <th>学生番号</th>
            <th>氏名</th>
            <th>クラス</th>
            <th style="text-align: center;">在学中</th>
            <th></th> <%-- 編集リンク用 --%>
          </tr>

          <c:forEach var="s" items="${students}">
            <tr>
              <%-- No.17〜21 学生情報表示 --%>
              <td>${s.entYear}</td>
              <td>${s.no}</td>
              <td>${s.name}</td>
              <td>${s.classNum}</td>
              <td style="text-align: center;">
                <c:choose>
                    <c:when test="${s.attend}">○</c:when>
                    <c:otherwise>×</c:otherwise>
                </c:choose>
              </td>
              <td style="text-align: center;">
                <%-- No.22 学生情報変更リンク --%>
                <a href="UpdateStudent.action?no=${s.no}">編集</a>
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:when>

      <c:otherwise>
        <%-- No.23 学生情報無しメッセージ --%>
        <div style="margin-top: 10px;">学生情報が存在しませんでした。</div>
      </c:otherwise>
    </c:choose>

  </c:param>
</c:import>