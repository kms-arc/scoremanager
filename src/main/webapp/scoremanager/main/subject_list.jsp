<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="menuTitle">
    <div style="background-color: #eee; padding: 10px; margin-bottom: 20px; font-weight: bold; border-radius: 4px;">科目管理</div>
  </c:param>
  <c:param name="content">

    <div style="text-align:right; margin-bottom:10px;">
      <a href="SubjectCreate.action" style="color: #007bff; text-decoration: none;">新規登録</a>
    </div>

    <%-- ★テーブルの枠組み（見出し）を外に出す --%>
    <table style="width:100%; border-collapse: collapse; margin-top:10px;">
      <thead>
        <tr style="border-bottom: 1px solid #ccc; text-align: left;">
          <th style="padding: 10px; width: 20%;">科目コード</th>
          <th style="padding: 10px; width: 20%;">科目名</th>
          <th style="padding: 10px; width: 10%;"></th>
          <th style="padding: 10px; width: 10%;"></th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <%-- データがある場合：行を繰り返し表示 --%>
          <c:when test="${not empty subjects}">
            <c:forEach var="subject" items="${subjects}">
              <tr style="border-bottom: 1px solid #eee;">
                <td style="padding: 10px;">${subject.cd}</td>
                <td style="padding: 10px;">${subject.name}</td>
                <td style="padding: 10px; text-align: center;">
                  <a href="SubjectUpdate.action?cd=${subject.cd}&school_cd=${subject.school.cd}" style="color: #007bff; text-decoration: none;">変更</a>
                </td>
                <td style="padding: 10px; text-align: center;">
                  <a href="SubjectDelete.action?cd=${subject.cd}&school_cd=${subject.school.cd}" style="color: #007bff; text-decoration: none;">削除</a>
                </td>
              </tr>
            </c:forEach>
          </c:when>

          <%-- ★データがない場合：テーブルの行としてメッセージを表示 --%>
          <c:otherwise>
            <tr>
              <td colspan="4" style="padding: 20px; color: #666;">
                科目情報が存在しませんでした。
              </td>
            </tr>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>

  </c:param>
</c:import>
