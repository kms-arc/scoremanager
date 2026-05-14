<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 共通テンプレート（base.jsp）を読み込み --%>
<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="content">

    <%-- No.1：画面タイトル (h2) --%>
    <%-- 共通枠の余白を打ち消すため margin-top: -30px を指定 --%>
    <h2 style="background-color: #dcdcdc; padding: 6px 10px; font-size: 1.2rem; margin-top: -30px; margin-bottom: 20px; width: 100%; box-sizing: border-box;">
      科目管理
    </h2>

    <%-- No.2：新規登録リンク（右寄せ配置） --%>
    <div style="text-align:right; margin-bottom:10px;">
      <a href="SubjectCreate.action" style="color: #007bff; text-decoration: none;">新規登録</a>
    </div>

    <%-- No.3：科目一覧テーブル --%>
    <table style="width:100%; border-collapse: collapse; margin-top:10px;">
      <thead>
        <%-- テーブルヘッダ：下線を引き、左寄せにする --%>
        <tr style="border-bottom: 1px solid #ccc; text-align: left;">
          <%-- No.4, 5：ヘッダ項目 --%>
          <th style="padding: 10px; width: 20%;">科目コード</th>
          <th style="padding: 10px; width: 20%;">科目名</th>
          <th style="padding: 10px; width: 10%;"></th> 
          <th style="padding: 10px; width: 10%;"></th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <%-- 科目データ（subjects）が存在する場合のループ処理 --%>
          <c:when test="${not empty subjects}">
            <c:forEach var="subject" items="${subjects}">
              <tr style="border-bottom: 1px solid #eee;">
                <%-- No.6, 7：科目データ表示 --%>
                <td style="padding: 10px;">${subject.cd}</td>
                <td style="padding: 10px;">${subject.name}</td>
                
                <%-- No.8：変更リンク --%>
                <td style="padding: 10px; text-align: center;">
                  <a href="SubjectUpdate.action?cd=${subject.cd}&school_cd=${subject.school.cd}" style="color: #007bff; text-decoration: none;">変更</a>
                </td>
                
                <%-- No.9：削除リンク --%>
                <td style="padding: 10px; text-align: center;">
                  <a href="SubjectDelete.action?cd=${subject.cd}&school_cd=${subject.school.cd}" style="color: #007bff; text-decoration: none;">削除</a>
                </td>
              </tr>
            </c:forEach>
          </c:when>

          <%-- データが存在しない場合のメッセージ表示 --%>
          <c:otherwise>
            <tr>
              <td colspan="4" style="padding: 20px; color: #666; text-align: center;">
                科目情報が存在しませんでした。
              </td>
            </tr>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>

  </c:param>
</c:import>