<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">成績参照検索</c:param>
  <c:param name="menuTitle">成績参照</c:param>
  <c:param name="content">

    <h2 style="margin:0 0 15px 0;">成績参照</h2>

    <!-- 科目情報検索 -->
    <form action="TestList.action" method="post" style="margin:0;">
      <input type="hidden" name="f" value="sj">

      <div style="display:flex; align-items:flex-start; gap:15px; margin-bottom:18px;">
        <div style="width:70px; font-weight:bold; font-size:13px; padding-top:6px;">
          科目情報
        </div>

        <div style="
          border:1px solid #ccc;
          padding:12px 14px;
          flex-grow:1;
          display:flex;
          gap:18px;
          align-items:flex-end;
          border-radius:3px;
          background-color:#fff;
        ">

          <div>
            <label style="font-size:11px; color:#333;">入学年度</label><br>
            <select name="f1" style="
              width:150px;
              height:28px;
              font-size:12px;
              padding:2px 4px;
            ">
              <option value="0">--------</option>
              <c:forEach var="year" items="${ent_year_set}">
                <option value="${year}">${year}</option>
              </c:forEach>
            </select>
          </div>

          <div>
            <label style="font-size:11px; color:#333;">クラス</label><br>
            <select name="f2" style="
              width:150px;
              height:28px;
              font-size:12px;
              padding:2px 4px;
            ">
              <option value="0">--------</option>
              <c:forEach var="num" items="${class_num_set}">
                <option value="${num}">${num}</option>
              </c:forEach>
            </select>
          </div>

          <div>
            <label style="font-size:11px; color:#333;">科目</label><br>
            <select name="f3" style="
              width:200px;
              height:28px;
              font-size:12px;
              padding:2px 4px;
            ">
              <option value="">--------</option>
              <c:forEach var="subject" items="${subjects}">
                <option value="${subject.cd}">${subject.name}</option>
              </c:forEach>
            </select>
          </div>

          <div style="margin-left:auto;">
            <button type="submit" style="
              width:60px;
              height:30px;
              font-size:12px;
              background-color:#666;
              color:#fff;
              border:1px solid #444;
              border-radius:4px;
              cursor:pointer;
            ">検索</button>
          </div>

        </div>
      </div>
    </form>

    <!-- 学生情報検索 -->
    <form action="TestList.action" method="post" style="margin:0;">
      <input type="hidden" name="f" value="st">

      <div style="display:flex; align-items:flex-start; gap:15px; margin-bottom:10px;">
        <div style="width:70px; font-weight:bold; font-size:13px; padding-top:6px;">
          学生情報
        </div>

        <div style="
          border:1px solid #ccc;
          padding:12px 14px;
          flex-grow:1;
          display:flex;
          gap:18px;
          align-items:flex-end;
          border-radius:3px;
          background-color:#fff;
        ">

          <div>
            <label style="font-size:11px; color:#333;">学生番号</label><br>
            <input type="text" name="f4" maxlength="10" value="${f4}"
              placeholder="学生番号を入力してください"
              style="
                width:250px;
                height:26px;
                font-size:12px;
                padding:2px 6px;
              ">
          </div>

          <div style="margin-left:auto;">
            <button type="submit" style="
              width:60px;
              height:30px;
              font-size:12px;
              background-color:#666;
              color:#fff;
              border:1px solid #444;
              border-radius:4px;
              cursor:pointer;
            ">検索</button>
          </div>

        </div>
      </div>
    </form>

    <!-- 利用方法 -->
    <p style="color:#0070c0; font-size:12px; margin:8px 0 12px 85px;">
      科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
    </p>

    <!-- エラー -->
    <c:if test="${not empty error}">
      <div style="color:red; margin:10px 0;">${error}</div>
    </c:if>

    <!-- 科目別成績一覧 -->
    <c:if test="${f == 'sj'}">
      <c:choose>
        <c:when test="${not empty testListSubjects}">
          <table style="width:100%; border-collapse:collapse; margin-top:15px; font-size:13px;">
            <thead>
              <tr style="border-bottom:1px solid #999; text-align:left;">
                <th style="padding:8px;">入学年度</th>
                <th style="padding:8px;">クラス</th>
                <th style="padding:8px;">学生番号</th>
                <th style="padding:8px;">氏名</th>
                <th style="padding:8px;">１回の点数</th>
                <th style="padding:8px;">２回の点数</th>
                <th style="padding:8px;">操作</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="t" items="${testListSubjects}">
                <c:set var="p1" value="-"/>
                <c:set var="p2" value="-"/>

                <c:forEach var="entry" items="${t.points}">
                  <c:if test="${entry.key == 1}">
                    <c:set var="p1" value="${entry.value}"/>
                  </c:if>
                  <c:if test="${entry.key == 2}">
                    <c:set var="p2" value="${entry.value}"/>
                  </c:if>
                </c:forEach>

                <tr style="border-bottom:1px solid #eee;">
                  <td style="padding:8px;">${t.entYear}</td>
                  <td style="padding:8px;">${t.classNum}</td>
                  <td style="padding:8px;">${t.studentNo}</td>
                  <td style="padding:8px;">${t.studentName}</td>
                  <td style="padding:8px;">${p1}</td>
                  <td style="padding:8px;">${p2}</td>

                  <!-- ★操作は変更・削除の2つだけ -->
                  <td style="padding:8px; font-size:12px;">
                    <a href="TestUpdate.action?student_no=${t.studentNo}&subject_cd=${param.f3}&no=1">変更</a>
                    &nbsp;
                    <a href="TestDelete.action?student_no=${t.studentNo}&subject_cd=${param.f3}">削除</a>
                  </td>

                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>

        <c:otherwise>
          <div style="color:#666; margin-top:15px;">該当する成績情報が存在しませんでした。</div>
        </c:otherwise>
      </c:choose>
    </c:if>

    <!-- 学生別成績一覧 -->
    <c:if test="${f == 'st'}">
      <c:choose>
        <c:when test="${not empty testListStudents}">
          <table style="width:100%; border-collapse:collapse; margin-top:15px; font-size:13px;">
            <thead>
              <tr style="border-bottom:1px solid #999; text-align:left;">
                <th style="padding:8px;">科目コード</th>
                <th style="padding:8px;">科目名</th>
                <th style="padding:8px;">回数</th>
                <th style="padding:8px;">得点</th>
                <th style="padding:8px;">操作</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="t" items="${testListStudents}">
                <tr style="border-bottom:1px solid #eee;">
                  <td style="padding:8px;">${t.subjectCd}</td>
                  <td style="padding:8px;">${t.subjectName}</td>
                  <td style="padding:8px;">${t.num}</td>
                  <td style="padding:8px;">${t.point}</td>
                  <td style="padding:8px; font-size:12px;">
                    <a href="TestUpdate.action?student_no=${param.f4}&subject_cd=${t.subjectCd}&no=${t.num}">変更</a>
                    &nbsp;
                    <a href="TestDelete.action?student_no=${param.f4}&subject_cd=${t.subjectCd}&no=${t.num}">削除</a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>

        <c:otherwise>
          <div style="color:#666; margin-top:15px;">該当する成績情報が存在しませんでした。</div>
        </c:otherwise>
      </c:choose>
    </c:if>

  </c:param>
</c:import>