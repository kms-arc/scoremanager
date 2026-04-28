<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>
</head>
<body style="margin:0; padding:0;">

<!-- ★ 全体白枠（画面いっぱいに広げる） -->
<div style="
    width:900px;
    margin:20px auto;
    background-color:#ffffff;
    padding-bottom:20px;
    min-height: calc(100vh - 40px);
    display:flex;
    flex-direction:column;
">

    <!-- ★ 青帯 -->
    <div style="
        background-color:#e6f6ff;
        height:50px;
        padding:4px 8px;
        border-bottom:1px solid #ccc;
        position:relative;
    ">
        <div style="font-size:28px; font-weight:bold;">成績管理システム</div>

        <div style="position:absolute; right:8px; bottom:4px; font-size:12px;">
         <c:if test="${not empty teacher}">
    ${teacher.name} 様　
    <a href="${pageContext.request.contextPath}/Logout.action">ログアウト</a>
</c:if>
         
        </div>
    </div>

    <!-- ★ 左ナビ + メイン -->
    <div style="display:flex; flex-grow:1;">

        <!-- 左ナビ -->
        <div style="width:200px; padding:15px; border-right:1px solid #ccc;">
            <a href="${pageContext.request.contextPath}/scoremanager/main/menu.jsp">メニュー</a>
            <div style="font-weight:bold; margin-bottom:8px;">学生管理</div>
            <a href="${pageContext.request.contextPath}/StudentList.action">学生一覧</a><br>
            <a href="${pageContext.request.contextPath}/TestRegist.action">成績登録</a><br>
            <a href="${pageContext.request.contextPath}/TestList.action">成績参照</a><br>
            <a href="${pageContext.request.contextPath}/SubjectList.action">科目管理</a>
        </div>

        <!-- メイン -->
        <div style="flex-grow:1; padding:20px;">

            <!-- ★ 灰色帯（画面ごとにタイトル変更） -->
            <div style="
                background-color:#e0e0e0;
                padding:6px 10px;
                margin-bottom:15px;
                font-weight:bold;
                border-radius:3px;
            ">
                ${param.menuTitle}
            </div>

            <!-- ★ 画面ごとの内容 -->
           <c:out value="${param.content}" escapeXml="false" />

        </div>
    </div>

    <!-- ★ 下部の灰色バー（フォントを元に戻した版） -->
    <div style="
        background-color:#e0e0e0;
        height:50px;
        padding:4px 8px;
        border-top:1px solid #ccc;
        position:relative;
        margin-top:auto;
        text-align:center;
        font-size:14px;      /* ← 元のフォントサイズに戻した */
        line-height:20px;    /* ← br 改行の2行表示に最適 */
    ">
        © 2023 TIC<br>
        大原学園
    </div>

</div>

</body>
</html>
