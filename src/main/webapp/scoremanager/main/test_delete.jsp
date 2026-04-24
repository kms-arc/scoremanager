<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<title>成績削除</title>
</head>
<body>

<h2>成績削除</h2>

<p>以下の成績を削除しますか？</p>

<form action="TestDeleteExecute.action" method="post">

	<input type="hidden" name="student_no" value="${test.student.no}">
	<input type="hidden" name="subject_cd" value="${test.subject.cd}">
	<input type="hidden" name="no" value="${test.no}">

	<p>学生番号：${test.student.no}</p>
	<p>科目コード：${test.subject.cd}</p>
	<p>回数：${test.no}</p>
	<p>点数：${test.point}</p>

	<input type="submit" value="削除">
</form>

<p><a href="Menu.action">キャンセル</a></p>

</body>
</html>