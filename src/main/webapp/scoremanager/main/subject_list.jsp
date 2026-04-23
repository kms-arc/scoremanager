<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="menuTitle">
    <%-- ① タイトル部分：背景グレーのヘッダー --%>
    <div style="background-color: #eee; padding: 10px; margin-bottom: 20px; font-weight: bold; border-radius: 4px;">科目管理</div>
  </c:param>
  <c:param name="content">

    <%-- ② 新規登録ボタン（右寄せ） --%>
    <div style="text-align:right; margin-bottom:10px;">
      <a href="SubjectCreate.action" style="color: #007bff; text-decoration: none;">新規登録</a>
    </div>

    <c:choose>
      <%-- ③ subjectsリストにデータがある場合 --%>
      <c:when test="${not empty subjects}">
        <table style="width:100%; border-collapse: collapse; margin-top:10px;">
          <thead>
            <%-- 見出し部分：下線のみのスタイル --%>
            <tr style="border-bottom: 1px solid #ccc; text-align: left;">
              <th style="padding: 10px; width: 20%;">科目コード</th>
              <th style="padding: 10px; width: 40%;">科目名</th>
              <th style="padding: 10px; width: 10%;"></th> <%-- ⑧ 変更リンク用空セル --%>
              <th style="padding: 10px; width: 10%;"></th> <%-- ⑨ 削除リンク用空セル --%>
            </tr>
          </thead>
          <tbody>
            <%-- データのループ回し --%>
            <c:forEach var="subject" items="${subjects}">
              <tr style="border-bottom: 1px solid #eee;">
                <%-- ④ 科目コード --%>
                <td style="padding: 10px;">${subject.cd}</td>
                <%-- ⑤ 科目名 --%>
                <td style="padding: 10px;">${subject.name}</td>
                <%-- ⑧ 変更リンク --%>
                <td style="padding: 10px; text-align: center;">
                  <a href="SubjectUpdate.action?cd=${subject.cd}" style="color: #007bff; text-decoration: none;">変更</a>
                </td>
                <%-- ⑨ 削除リンク --%>
                <td style="padding: 10px; text-align: center;">
                  <a href="SubjectDelete.action?cd=${subject.cd}" style="color: #007bff; text-decoration: none;">削除</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:when>

      <%-- データがない場合 --%>
      <c:otherwise>
        <div style="margin-top:20px;">科目情報が存在しませんでした。</div>
      </c:otherwise>
    </c:choose>

  </c:param>
</c:import>