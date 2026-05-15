<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">学生情報変更</c:param>
  <c:param name="content">

    <%-- No.1 画面タイトル --%>
    <h2 style="background-color: #dcdcdc; padding: 6px 10px; font-size: 1.2rem; margin-top: -30px; margin-bottom: 20px;">
        学生情報変更
    </h2>

    <form action="UpdateStudentExecute.action" method="post" style="margin-bottom:20px;">

      <%-- No.2, 3 入学年度（Value属性: ${ent_year}） --%>
      <div style="margin-bottom:15px;">
        <label>入学年度</label><br>
        <input type="text" name="ent_year" value="${ent_year}" readonly style="background-color:#eee; border:none; outline:none;">
      </div>

      <%-- No.4, 5 学生番号（Value属性: ${no}） --%>
      <div style="margin-bottom:15px;">
        <label>学生番号</label><br>
        <input type="text" name="no" value="${no}" readonly style="background-color:#eee; border:none; outline:none;">
      </div>

      <%-- No.6, 7 氏名（Value属性: ${name}） --%>
      <div style="margin-bottom:15px;">
        <label>氏名</label><br>
        <input type="text" name="name" value="${name}" maxlength="30" required style="width:300px; height:26px; padding: 2px 5px;">
      </div>

      <%-- No.8, 9 クラス（Value属性: ${class_num}） --%>
      <div style="margin-bottom:15px;">
       <label>クラス</label><br>
		<select name="class_num" style="width:120px; height:30px;">
    	 <c:forEach var="cNum" items="${class_num_set}">
      <%-- Javaで作ったリスト(class_num_set)を回して、今のクラスをselectedにする --%>
        <option value="${cNum}" <c:if test="${cNum == class_num}">selected</c:if>>${cNum}
        	</option>
    	</c:forEach>
	</select>
      </div>

      <%-- No.10, 11 在学中（Value属性: ${is_attend}） --%>
      <div style="margin-bottom:20px;">
        <label>
          <input type="checkbox" name="is_attend" value="true"
            <c:if test="${is_attend}">checked</c:if>>
          在学中
        </label>
      </div>

      <%-- No.12 更新ボタン --%>
      <button type="submit" name="login" style="padding: 5px 15px;">変更</button>
      
      <%-- No.13 戻るリンク --%>
      <a href="StudentList.action" style="font-size:14px; margin-left:10px; text-decoration: none; color: #0066cc;">戻る</a>

    </form>

  </c:param>
</c:import>