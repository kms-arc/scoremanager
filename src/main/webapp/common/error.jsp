<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">エラーページ</c:param>

    <c:param name="content">
     <form>
        <style>/* エラーページ内だけ灰色バーを非表示 */
        div[style*="background-color:#e0e0e0"][style*="margin-bottom:15px"] {
            display: none;
        }
        </style>

        <p>エラーが発生しました。</p>
        
      </form>
    </c:param>
</c:import>
