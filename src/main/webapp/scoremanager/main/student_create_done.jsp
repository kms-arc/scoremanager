<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績管理システム</c:param>
    <c:param name="content">

        <%-- No.1 画面タイトル：設計書に合わせて「学生登録」または「学生情報登録」 --%>
        <h2 style="background-color: #dcdcdc; padding: 6px 10px; font-size: 1.2rem; margin-top: -30px; margin-bottom: 20px; width: 100%; box-sizing: border-box;">
            学生情報登録
        </h2>

        <%-- No.2 完了メッセージ：種別「p」を適用 --%>
        <%-- 緑帯のデザインを維持しつつタグをpに変更 --%>
        <div style="width:700px; margin:0 auto 20px; background-color:#3fa63f; padding:6px 10px; text-align:center;">
            <p style="margin: 0; font-size: 14px; color: #000000;">登録が完了しました</p>
        </div>

        <div style="width:700px; margin:0 auto 30px; text-align:left;">
            <%-- No.3 戻るリンク --%>
            <a href="StudentCreate.action"
               style="font-size:12px; color:#0066cc; text-decoration:none; margin-right:20px;">
                戻る
            </a>

            <%-- No.4 学生管理一覧リンク：文言を設計書に合わせる --%>
            <a href="StudentList.action"
               style="font-size:12px; color:#0066cc; text-decoration:none;">
                学生管理
            </a>
        </div>

    </c:param>
</c:import>