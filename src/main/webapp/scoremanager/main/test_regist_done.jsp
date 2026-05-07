<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">

  
  <c:param name="title">成績登録完了</c:param>

  
  <c:param name="menuTitle">成績管理</c:param>

 
  <c:param name="content">

    <div style="
        width:700px;               /* ← 灰色帯と同じ幅で中央寄せ */
        margin:0 auto 20px;
        background-color:#3fa63f;  /* ← 蚊取り線香くらいの濃い緑 */
        padding:6px 10px;
        font-size:14px;
        text-align:center;
        color:#000000;             /* ← 黒文字に戻す */
    ">
        登録が完了しました
    </div>
    
    
    <div style="margin-top:20px; text-align:left;">
      <a href="${pageContext.request.contextPath}/TestRegist.action"
         style="font-size:14px; color:#0066cc; text-decoration:none;">
        戻る
        
        <a href="${pageContext.request.contextPath}/TestList.action"
         style="font-size:14px; color:#0066cc; text-decoration:none;">
        成績参照
      </a>
      
      
    </div>

  </c:param>

</c:import>
