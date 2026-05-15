<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
<c:param name="title">成績管理システム</c:param>

    <c:param name="menuTitle" value="学生情報登録" />

    <c:param name="content">

        <form action="StudentCreateExecute.action" method="post" style="width:500px;">

            <!-- 入学年度 -->
<div style="margin-bottom:15px;">
    <label>入学年度</label><br>
    <select name="ent_year" style="width:200px; height:28px;">
        <option value="">------</option>

        <!-- ★ ここだけ修正：ent_year_list → ent_year_set -->
        <c:forEach var="year" items="${ent_year_set}">
            <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>
                ${year}
            </option>
        </c:forEach>

    </select>
</div>
            

            <!-- 学生番号 -->
            <div style="margin-bottom:15px;">
                <label>学生番号</label><br>
                <input type="text" name="no"
                       value="${no != null ? no : ''}"
                       maxlength="10"
                       placeholder="学生番号を入力してください"
                       style="width:200px; height:26px;">
            </div>

            <!-- 氏名 -->
            <div style="margin-bottom:15px;">
                <label>氏名</label><br>
                <input type="text" name="name"
                       value="${name != null ? name : ''}"
                       maxlength="30"
                       placeholder="氏名を入力してください"
                       style="width:200px; height:26px;">
            </div>

            <!-- クラス -->
            <div style="margin-bottom:15px;">
                <label>クラス</label><br>
                <select name="class_num" style="width:200px; height:28px;">

                    <c:forEach var="num" items="${class_num_set}">
                        <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>
                            ${num}
                        </option>
                    </c:forEach>

                </select>
            </div>

            <!-- 在学中 -->
            <div style="margin-bottom:20px;">
                <label>
                    <input type="checkbox" name="attend"
                           <c:if test="${attend}">checked</c:if>>
                    在学中
                </label>
            </div>

            <!-- ボタン -->
            <div style="margin-top:20px;">
                <button type="submit" style="padding:6px 20px;">登録して終了</button>
                <a href="StudentList.action" style="margin-left:20px;">戻る</a>
            </div>

        </form>

    </c:param>

</c:import>
