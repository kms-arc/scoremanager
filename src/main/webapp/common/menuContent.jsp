<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- ★ 全体を中央寄せ -->
<div style="width:720px; margin:0 auto;">


    <!-- ★ 3つの長方形ボックス -->
    <div style="
        display:flex;
        gap:20px;
        justify-content:center;
    ">

        <!-- 1つ目：学生一覧（ピンク） -->
        <div style="
            width:200px;
            height:120px;
            background-color:#ffe5e5;
            border-radius:4px;
            display:flex;
            justify-content:center;
            align-items:center;
            text-align:center;
        ">
            <a href="${pageContext.request.contextPath}/StudentList.action">
                学生一覧
            </a>
        </div>

        <!-- 2つ目：成績管理（緑） -->
        <div style="
            width:200px;
            height:120px;
            background-color:#e6ffe6;
            border-radius:4px;
            display:flex;
            flex-direction:column;
            justify-content:center;
            align-items:center;
            text-align:center;
        ">
            成績管理
            <div style="margin-top:8px; font-size:14px; font-weight:normal;">
                <a href="${pageContext.request.contextPath}/ScoreAddMenu.action">成績登録</a><br>
                <a href="${pageContext.request.contextPath}/ScoreResult.action">成績参照</a>
            </div>
        </div>

        <!-- 3つ目：科目管理（青） -->
        <div style="
            width:200px;
            height:120px;
            background-color:#e6f2ff;
            border-radius:4px;
            display:flex;
            justify-content:center;
            align-items:center;
            text-align:center;
        ">
            <a href="${pageContext.request.contextPath}/SubjectMenu.action">
                科目管理
            </a>
        </div>

    </div>

</div>
