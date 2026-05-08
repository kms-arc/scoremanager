<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
  <c:param name="title">成績参照検索</c:param>
  <c:param name="menuTitle">成績参照検索</c:param>
  <c:param name="content">

    <h2>成績参照検索</h2>

    <%-- 科目情報検索フォーム --%>
    <form action="TestList.action" method="post">
      <input type="hidden" name="f" value="sj">

      <p>科目情報</p>

      <div style="border:1px solid #ccc; padding:15px; margin-bottom:20px; display:flex; gap:30px;">
        <div>
          <label>入学年度</label><br>
          <select name="f1" style="width:160px; height:28px;">
            <option value="0">--------</option>
            <c:forEach var="year" items="${ent_year_set}">
              <option value="${year}">${year}</option>
            </c:forEach>
          </select>
        </div>

        <div>
          <label>クラス</label><br>
          <select name="f2" style="width:160px; height:28px;">
            <option value="0">--------</option>
            <c:forEach var="num" items="${class_num_set}">
              <option value="${num}">${num}</option>
            </c:forEach>
          </select>
        </div>

        <div>
          <label>科目</label><br>
          <select name="f3" style="width:160px; height:28px;">
            <option value="">--------</option>
            <c:forEach var="subject" items="${subjects}">
              <option value="${subject.cd}">${subject.name}</option>
            </c:forEach>
          </select>
        </div>

        <div style="align-self:flex-end;">
          <button type="submit">検索</button>
        </div>
      </div>
    </form>

    <%-- 学生情報検索フォーム --%>
    <form action="TestList.action" method="post">
      <input type="hidden" name="f" value="st">

      <p>学生情報</p>

      <div style="border:1px solid #ccc; padding:15px; margin-bottom:20px; display:flex; gap:30px;">
        <div>
          <label>学生番号</label><br>
          <input type="text" name="f4" maxlength="10" value="${f4}"
                 placeholder="学生番号を入力してください"
                 style="width:220px; height:24px;">
        </div>

        <div style="align-self:flex-end;">
          <button type="submit">検索</button>
        </div>
      </div>
    </form>

    <%-- 利用方法案内メッセージ --%>
    <p>科目情報または学生番号を指定して検索してください。</p>

    <%-- エラーメッセージ --%>
    <c:if test="${not empty error}">
      <div style="color:red; margin-bottom:10px;">${error}</div>
    </c:if>

    <%-- 科目別成績一覧 --%>
    <c:if test="${f == 'sj'}">
      <c:choose>
        <c:when test="${not empty testListSubjects}">
          <table style="width:100%; border-collapse:collapse; margin-top:10px;">
            <thead>
              <tr style="border-bottom:1px solid #ccc; text-align:left;">
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

                  <td style="padding:8px;">
                    <a href="TestUpdate.action?student_no=${t.studentNo}&subject_cd=${param.f3}&no=1">変更(1回)</a>
                    &nbsp;
                    <a href="TestUpdate.action?student_no=${t.studentNo}&subject_cd=${param.f3}&no=2">変更(2回)</a>
                    <br>
                    <a href="TestDelete.action?student_no=${t.studentNo}&subject_cd=${param.f3}&no=1">削除(1回)</a>
                    &nbsp;
                    <a href="TestDelete.action?student_no=${t.studentNo}&subject_cd=${param.f3}&no=2">削除(2回)</a>
                  </td>

                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>
        <c:otherwise>
          <div style="color:#666; margin-top:10px;">該当する成績情報が存在しませんでした。</div>
        </c:otherwise>
      </c:choose>
    </c:if>

    <%-- 学生別成績一覧 --%>
    <c:if test="${f == 'st'}">
      <c:choose>
        <c:when test="${not empty testListStudents}">
          <table style="width:100%; border-collapse:collapse; margin-top:10px;">
            <thead>
              <tr style="border-bottom:1px solid #ccc; text-align:left;">
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

                  <td style="padding:8px;">
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
          <div style="color:#666; margin-top:10px;">該当する成績情報が存在しませんでした。</div>
        </c:otherwise>
      </c:choose>
    </c:if>

  </c:param>
</c:import>
