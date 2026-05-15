<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">学生情報変更</c:param>
  <c:param name="menuTitle">学生情報変更</c:param>
  <c:param name="content">

    <form action="UpdateStudent.action" method="post" style="margin-bottom:20px;">

      <!-- 入学年度（表示のみ） -->
      <div style="margin-bottom:15px;">
        <label>入学年度</label><br>
        <span>${student.entYear}</span>
        <!-- ★ hidden を追加（これが無いとエラーになる） -->
        <input type="hidden" name="entYear" value="${student.entYear}">
      </div>

      <!-- 学生番号（表示のみ） -->
      <div style="margin-bottom:15px;">
        <label>学生番号</label><br>
        <span>${student.no}</span>
        <input type="hidden" name="no" value="${student.no}">
      </div>

      <!-- 氏名（必須） -->
      <div style="margin-bottom:15px;">
        <label>氏名</label><br>
        <input type="text" name="name"
               value="${student.name}"
               placeholder="氏名を入力してください"
               style="width:300px; height:26px;"
               required>
      </div>

      <!-- クラス（編集可能） -->
      <div style="margin-bottom:15px;">
        <label>クラス</label><br>
        <input type="text" name="classNum"
               value="${student.classNum}"
               style="width:120px; height:26px;">
      </div>

      <!-- 在学中 -->
      <div style="margin-bottom:20px;">
        <label>
          <input type="checkbox" name="attend" value="true"
            <c:if test="${student.attend}">checked</c:if>>
          在学中
        </label>
      </div>

      <!-- ボタン -->
      <button type="submit">更新</button>
      <a href="StudentList.action" style="font-size:14px;">戻る</a>

    </form>

  </c:param>
</c:import>
