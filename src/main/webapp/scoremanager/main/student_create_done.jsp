<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
<c:param name="title">成績管理システム</c:param>
    <c:param name="menuTitle" value="学生情報登録" />

    <c:param name="content">

        <!-- 緑帯 -->
        <div style="
            width:700px;
            margin:0 auto 20px;
            background-color:#3fa63f;
            padding:6px 10px;
            font-size:14px;
            text-align:center;
            color:#000000;
        ">
            登録が完了しました
        </div>

        <!-- 戻る・学生一覧 -->
        <div style="
            width:700px;
            margin:0 auto 30px;
            text-align:left;
        ">
            <a href="StudentCreate.action"
               style="font-size:12px; color:#0066cc; text-decoration:none; margin-right:20px;">
                戻る
            </a>

            <a href="StudentList.action"
               style="font-size:12px; color:#0066cc; text-decoration:none;">
                学生一覧
            </a>
        </div>

    </c:param>

</c:import>
