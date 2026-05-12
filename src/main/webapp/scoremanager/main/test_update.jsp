<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">成績変更</c:param>
  <c:param name="menuTitle">成績変更</c:param>
  <c:param name="content">

    <c:if test="${not empty error}">
      <div style="color:red; margin-bottom:10px;">${error}</div>
    </c:if>

    <form action="TestUpdateExecute.action" method="post">

      <input type="hidden" name="student_no" value="${studentNo}">
      <input type="hidden" name="subject_cd" value="${subjectCd}">

      <table style="border-collapse:collapse; width:500px;">

        <tr style="border-bottom:1px solid #ccc;">
          <th style="text-align:left; padding:8px;">学生番号</th>
          <td style="padding:8px;">${studentNo}</td>
        </tr>

        <tr style="border-bottom:1px solid #ccc;">
          <th style="text-align:left; padding:8px;">科目コード</th>
          <td style="padding:8px;">${subjectCd}</td>
        </tr>

        <tr style="border-bottom:1px solid #ccc;">
          <th style="text-align:left; padding:8px;">1回目点数</th>
          <td style="padding:8px;">
            <input type="text" name="point1" value="${test1.point}" style="width:80px;">
            <span style="font-size:11px; color:#666;">（空欄なら削除）</span>
          </td>
        </tr>

        <tr style="border-bottom:1px solid #ccc;">
          <th style="text-align:left; padding:8px;">2回目点数</th>
          <td style="padding:8px;">
            <input type="text" name="point2" value="${test2.point}" style="width:80px;">
            <span style="font-size:11px; color:#666;">（空欄なら削除）</span>
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
        ">更新</button>
      </div>

    </form>

    <p style="margin-top:15px;">
      <a href="Menu.action">メニューへ戻る</a>
    </p>

  </c:param>
</c:import>