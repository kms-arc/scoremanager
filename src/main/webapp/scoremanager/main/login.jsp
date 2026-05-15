<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>

<!-- ★ 全体白枠 -->
<div style="width:900px; margin:20px auto; background-color:#ffffff; padding-bottom:20px;">

    <!-- ★ 青帯 -->
    <div style="
        background-color:#e6f6ff;
        height:50px;
        padding:4px 8px;
        border-bottom:1px solid #ccc;
    ">
        <div style="font-size:28px; font-weight:bold;">得点管理システム</div>
    </div>

    <div style="height:20px;"></div>

    <!-- ★ ログイン枠 -->
    <div style="
        width:400px;
        margin:0 auto;
        border:1px solid #ccc;
        border-radius:4px;
        padding:0;
    ">

        <!-- ★ 灰色帯 -->
        <div style="
            background-color:#e0e0e0;
            padding:8px 10px;
            font-weight:bold;
            border-radius:4px 4px 0 0;
            text-align:left;
        ">
            ログイン
        </div>

        <!-- ★ フォーム本体 -->
        <div style="padding:20px;">

            <form action="${pageContext.request.contextPath}/Login.action" method="post">

                <!-- ★ ID -->
                <div style="margin-bottom:10px;">
                    <label>ID</label><br>
                    <input type="text" name="id"
                           value="${id}"
                           style="width:100%; height:28px;"
                           maxlength="10"
                           required>
                </div>

                <!-- ★ パスワード -->
                <div style="margin-bottom:10px;">
                    <label>パスワード</label><br>
                    <input type="password" id="pw" name="password"
                           style="width:100%; height:28px;"
                           maxlength="30"
                           required>
                </div>

                <!-- ★ パスワード表示切替 -->
                <div style="margin-bottom:15px;">
                    <input type="checkbox" id="chk_d_ps"
                           onclick="document.getElementById('pw').type = this.checked ? 'text' : 'password';">
                    <label for="chk_d_ps" style="font-size:12px;">パスワードを表示</label>
                </div>

                <!-- ★ ログインボタン -->
                <button type="submit" style="width:100%; padding:8px;">ログイン</button>

            </form>

            <!-- ★ 認証エラー（サーバ側） -->
            <c:if test="${not empty error}">
                <div style="color:red; margin-top:10px;">
                    ${error}
                </div>
            </c:if>

        </div>
    </div>

    <!-- ★ フッター -->
    <div style="
        background-color:#e0e0e0;
        height:50px;
        padding-top:10px;
        margin-top:20px;
        text-align:center;
        font-size:14px;
        border-top:1px solid #ccc;
    ">
        © 2023 TIC<br>
        大原学園
    </div>

</div>

</body>
</html>
