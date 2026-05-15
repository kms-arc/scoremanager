<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">成績登録</c:param>
  <c:param name="menuTitle">成績登録</c:param>
  <c:param name="content">

    <!-- ★ 右側コンテンツの最大幅を固定（サイドバーと干渉しない） -->
    <div style="max-width:700px; margin:0 auto;">

      <form action="TestRegist.action" method="get">
        <div style="
            border:1px solid #ccc;
            padding:12px;
            margin-bottom:20px;
            display:flex;
            gap:20px;
            align-items:flex-end;
        ">

          <div>
            <label style="font-size:12px;">入学年度</label><br>
            <select name="entYear" style="width:120px; height:24px; font-size:12px; padding:2px 4px;">
              <option value="">--</option>
              <c:forEach var="year" items="${entYearList}">
                <option value="${year}" <c:if test="${param.entYear == year}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>

          <div>
            <label style="font-size:12px;">クラス</label><br>
            <select name="classNum" style="width:120px; height:24px; font-size:12px; padding:2px 4px;">
              <option value="">--</option>
              <c:forEach var="num" items="${classList}">
                <option value="${num}" <c:if test="${param.classNum == num}">selected</c:if>>${num}</option>
              </c:forEach>
            </select>
          </div>

          <div>
            <label style="font-size:12px;">科目</label><br>
            <select name="subjectCd" style="width:120px; height:24px; font-size:12px; padding:2px 4px;">
              <option value="">--</option>
              <c:forEach var="sub" items="${subjectList}">
                <option value="${sub.cd}" <c:if test="${param.subjectCd == sub.cd}">selected</c:if>>${sub.name}</option>
              </c:forEach>
            </select>
          </div>

          <div>
            <label style="font-size:12px;">回数</label><br>
            <select name="count" style="width:120px; height:24px; font-size:12px; padding:2px 4px;">
              <option value="">--</option>
              <c:forEach var="c" items="${countList}">
                <option value="${c}" <c:if test="${param.count == c}">selected</c:if>>${c}</option>
              </c:forEach>
            </select>
          </div>

          <div>
            <button type="submit" style="
                width:40px;
                height:26px;
                font-size:12px;
                background-color:#666;
                color:#fff;
                border:1px solid #444;
                border-radius:3px;
                cursor:pointer;
            ">検索</button>
          </div>

        </div>
      </form>

      <!-- ★ 検索結果 -->
      <c:choose>

        <c:when test="${not empty studentList}">

          <!-- ★ 科目名（回数） -->
          <div style="font-weight:bold; margin-bottom:10px;">
            科目：${subject.name}（${param.count}回）
          </div>

          <form action="TestRegistExecute.action" method="post">
            <input type="hidden" name="entYear" value="${param.entYear}">
            <input type="hidden" name="classNum" value="${param.classNum}">
            <input type="hidden" name="subjectCd" value="${param.subjectCd}">
            <input type="hidden" name="count" value="${param.count}">

            <!-- ★ 横線だけのテーブル -->
            <table style="width:100%; border-collapse:collapse;">
              <tr style="border-bottom:1px solid #333;">
                <th style="padding:6px 4px; text-align:left;">入学年度</th>
                <th style="padding:6px 4px; text-align:left;">クラス</th>
                <th style="padding:6px 4px; text-align:left;">学生番号</th>
                <th style="padding:6px 4px; text-align:left;">氏名</th>
                <th style="padding:6px 4px; text-align:left;">点数</th>
              </tr>

              <c:forEach var="s" items="${studentList}">
                <tr style="border-bottom:1px solid #ccc;">
                  <td style="padding:6px 4px;">${s.entYear}</td>
                  <td style="padding:6px 4px;">${s.classNum}</td>
                  <td style="padding:6px 4px;">${s.no}</td>
                  <td style="padding:6px 4px;">${s.name}</td>

                  <td style="padding:6px 4px;">
                    <!-- ★ 0～100 の入力制限 -->
                    <input type="number"
                           name="score_${s.no}"
                           value="${s.point}"
                           min="0"
                           max="100"
                           style="width:60px;">

                    <!-- ★ コメント（スクショ通りのオレンジ） -->
                    <div style="font-size:11px; color:#ff6600; margin-top:2px;">
                      0～100の範囲で入力してください
                    </div>
                  </td>
                </tr>
              </c:forEach>
            </table>

            <!-- ★ 登録して終了ボタン -->
            <div style="margin-top:15px; text-align:left;">
              <button type="submit" style="
                  width:90px;
                  height:26px;
                  font-size:12px;
                  background-color:#666;
                  color:#fff;
                  border:1px solid #444;
                  border-radius:3px;
                  cursor:pointer;
              ">登録して終了</button>
            </div>

          </form>
        </c:when>

        <c:otherwise>
          <c:if test="${param.entYear != null}">
            <div style="color:red;">学生情報が存在しませんでした。</div>
          </c:if>
        </c:otherwise>

      </c:choose>

    </div>

  </c:param>
</c:import>
