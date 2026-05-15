<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">成績管理システム</c:param>

    <c:param name="content">
    
	<h2 style="background-color: #dcdcdc; padding: 6px 10px; font-size: 1.2rem; margin-top: -30px; margin-bottom: 20px; width: 100%; box-sizing: border-box;">
     学生情報登録
    </h2>
    
        <%-- エラーメッセージ表示エリア --%>
        <c:if test="${not empty error}">
            <div style="color: orange; font-size: 0.9rem; margin-bottom: 15px;">
                ${error}
            </div>
        </c:if>

        <form action="StudentCreateExecute.action" method="post" style="width:500px;">

            <div style="margin-bottom:15px;">
                <label>入学年度</label><br>
                <select name="ent_year" style="width:200px; height:28px;">
                    <option value="">------</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>
                            ${year}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div style="margin-bottom:15px;">
                <label>学生番号</label><br>
                <input type="text" name="no"
                       value="${not empty no ? no : ''}"
                       maxlength="10"
                       placeholder="学生番号を入力してください"
                       style="width:200px; height:26px;">
            </div>

            <div style="margin-bottom:15px;">
                <label>氏名</label><br>
                <input type="text" name="name"
                       value="${not empty name ? name : ''}"
                       maxlength="30"
                       placeholder="氏名を入力してください"
                       style="width:200px; height:26px;">
            </div>

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

            <div style="margin-top:20px;">
               <button type="submit" name="end" style="...">登録して終了</button>
                
                <a href="StudentList.action" style="margin-left:20px;">戻る</a>
            </div>

        </form>
    </c:param>
</c:import>