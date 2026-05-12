<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">成績変更</c:param>
  <c:param name="menuTitle">成績変更</c:param>
  <c:param name="content">

    <h2>成績変更</h2>

    <c:if test="${not empty error}">
      <div style="color:red; margin-bottom:10px;">${error}</div>
    </c:if>

    <c:if test="${not empty test}">
      <form action="TestUpdateExecute.action" method="post">

        <input type="hidden" name="student_no" value="${test.student.no}">
        <input type="hidden" name="subject_cd" value="${test.subject.cd}">

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
            <td style="padding:8px;">
              <select name="no" style="width:80px; height:26px; font-size:12px;">
                <option value="1" <c:if test="${test.no == 1}">selected</c:if>>1</option>
                <option value="2" <c:if test="${test.no == 2}">selected</c:if>>2</option>
              </select>
            </td>
          </tr>

          <tr style="border-bottom:1px solid #ccc;">
            <th style="text-align:left; padding:8px;">点数</th>
            <td style="padding:8px;">
              <input type="text" name="point" value="${test.point}" style="width:80px;">
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
    </c:if>

    <p style="margin-top:15px;">
      <a href="Menu.action">メニューへ戻る</a>
    </p>

  </c:param>
</c:import>