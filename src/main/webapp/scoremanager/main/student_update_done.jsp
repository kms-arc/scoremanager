<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績管理システム</c:param>
    <c:param name="content">

        <%-- 画面タイトル --%>
        <h2 style="background-color: #dcdcdc; padding: 6px 10px; font-size: 1.2rem; margin-top: -30px; margin-bottom: 20px; width: 100%; box-sizing: border-box;">
            学生情報変更
        </h2>

        <%-- 完了メッセージ --%>
        <div style="width:700px; margin:0 auto 20px; background-color:#3fa63f; padding:6px 10px; text-align:center;">
            <p style="margin: 0; font-size: 14px; color: #000000;">変更が完了しました</p>
        </div>

        <%-- リンクエリア --%>
        <div style="width:700px; margin:0 auto 30px; text-align:left;">
            
            <%--戻るリンク（編集画面へ戻る） --%>
            <a href="UpdateStudent.action?no=${student.no}"
               style="font-size:12px; color:#0066cc; text-decoration:none; margin-right:20px;">
                戻る
            </a>

            <%-- 学生一覧へのリンク --%>
            <a href="StudentList.action"
               style="font-size:12px; color:#0066cc; text-decoration:none;">
                学生一覧
            </a>
        </div>

    </c:param>
</c:import>