<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
</head>
<body style="margin:0; padding:0;">

<!-- ★ 全体白枠 -->
<div style="
    width:900px;
    margin:20px auto;
    background-color:#ffffff;
">

    <!-- ★ 青帯（中央寄せ） -->
    <div style="
        width:860px;
        margin:0 auto;
        background-color:#e6f6ff;
        height:50px;
        padding:4px 8px;
        border-bottom:1px solid #ccc;
        font-size:28px;
        font-weight:bold;
    ">
        得点管理システム
    </div>

    <!-- ★ 灰色帯（さらに中央寄せ） -->
    <div style="
        width:700px;               /* ← 真ん中に寄せるためさらに狭く */
        margin:30px auto 10px;
        background-color:#e0e0e0;
        padding:6px 10px;
        font-size:20px;            /* ← 少し大きめ */
        font-weight:bold;
        text-align:left;
    ">
        ログアウト
    </div>

    <!-- ★ 緑帯（濃い緑・黒文字・中央寄せ） -->
    <div style="
        width:700px;               /* ← 灰色帯と同じ幅で中央寄せ */
        margin:0 auto 20px;
        background-color:#3fa63f;  /* ← 蚊取り線香くらいの濃い緑 */
        padding:6px 10px;
        font-size:14px;
        text-align:center;
        color:#000000;             /* ← 黒文字に戻す */
    ">
        ログアウトしました
    </div>

    <!-- ★ ログイン（小さめ・中央寄せ帯と同じ幅） -->
    <div style="
        width:700px;
        margin:0 auto 30px;
        text-align:left;
    ">
        <a href="Login.action" style="
            font-size:12px;         /* ← 小さめ */
            color:#0066cc;
            text-decoration:none;
        ">
            ログイン
        </a>
    </div>

    <!-- ★ フッター（中央寄せ） -->
    <div style="
        width:860px;
        margin:0 auto;
        background-color:#e0e0e0;
        height:50px;
        padding-top:10px;
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
