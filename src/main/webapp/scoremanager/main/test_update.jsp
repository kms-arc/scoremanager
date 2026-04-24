<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>成績変更</title>
</head>
<body>

<h2>成績変更</h2>

<c:if test="${not empty error}">
	<p style="color:red;">${error}</p>
</c:if>

<form action="TestUpdateExecute.action" method="post">

	<input type="hidden" name="student_no" value="${test.student.no}">
	<input type="hidden" name="subject_cd" value="${test.subject.cd}">
	<input type="hidden" name="no" value="${test.no}">

	<p>学生番号：${test.student.no}</p>
	<p>科目コード：${test.subject.cd}</p>
	<p>回数：${test.no}</p>

	<p>
		点数：
		<input type="text" name="point" value="${test.point}">
	</p>

	<input type="submit" value="更新">
</form>

<p><a href="Menu.action">メニューへ戻る</a></p>

</body>
</html>