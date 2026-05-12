<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">成績削除</c:param>
  <c:param name="menuTitle">成績削除</c:param>
  <c:param name="content">

    <h2>成績削除</h2>

    <!-- 両方存在する場合（回数選択） -->
    <c:if test="${not empty test1 and not empty test2}">
      <p>削除する回数を選択してください。</p>

      <form action="TestDeleteExecute.action" method="post">

        <input type="hidden" name="student_no" value="${test1.student.no}">
        <input type="hidden" name="subject_cd" value="${test1.subject.cd}">

        <table style="border-collapse:collapse; width:500px;">
          <tr style="border-bottom:1px solid #ccc;">
            <th style="text-align:left; padding:8px;">学生番号</th>
            <td style="padding:8px;">${test1.student.no}</td>
          </tr>

          <tr style="border-bottom:1px solid #ccc;">
            <th style="text-align:left; padding:8px;">科目コード</th>
            <td style="padding:8px;">${test1.subject.cd}</td>
          </tr>

          <tr style="border-bottom:1px solid #ccc;">
            <th style="text-align:left; padding:8px;">削除する回数</th>
            <td style="padding:8px;">
              <select name="no" style="width:80px; height:26px; font-size:12px;">
                <option value="1">1回</option>
                <option value="2">2回</option>
              </select>
            </td>
          </tr>
        </table>

        <div style="margin-top:15px;">
          <button type="submit" style="
            width:90px;
            height:26px;
            font-size:12px;
            background-color:#666;
            color:#fff;
            border:1px solid #444;
            border-radius:3px;
            cursor:pointer;
          ">削除</button>
        </div>

      </form>
    </c:if>


    <!-- 片方しか存在しない場合（今まで通り） -->
    <c:if test="${not empty test}">
      <p>以下の成績を削除しますか？</p>

      <form action="TestDeleteExecute.action" method="post">

        <input type="hidden" name="student_no" value="${test.student.no}">
        <input type="hidden" name="subject_cd" value="${test.subject.cd}">
        <input type="hidden" name="no" value="${test.no}">

        <table style="border-collapse:collapse; width:500px;">
          <tr style="border-bottom:1px solid #ccc;">
            <th style="text-align:left; padding:8px;">学生番号</th>
            <td style="padding:8px;">${test.student.no}</td>
          </tr>

          <tr style="border-bottom:1px solid #ccc;">
            <th style="text-align:left; padding:8px;">科目コード</th>
            <td style="padding:8px;">${test.subject.cd}</td>
          </tr>

          <tr style="border-bottom:1px solid #ccc;">
            <th style="text-align:left; padding:8px;">回数</th>
            <td style="padding:8px;">${test.no}</td>
          </tr>

          <tr style="border-bottom:1px solid #ccc;">
            <th style="text-align:left; padding:8px;">点数</th>
            <td style="padding:8px;">${test.point}</td>
          </tr>
        </table>

        <div style="margin-top:15px;">
          <button type="submit" style="
            width:90px;
            height:26px;
            font-size:12px;
            background-color:#666;
            color:#fff;
            border:1px solid #444;
            border-radius:3px;
            cursor:pointer;
          ">削除</button>
        </div>

      </form>
    </c:if>


    <p style="margin-top:15px;">
      <a href="Menu.action">キャンセル</a>
    </p>

  </c:param>
</c:import>