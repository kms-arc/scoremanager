package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

    protected Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
            "jdbc:h2:file:~/kaihatu",  // ← H2 の DB ファイル名
            "sa",                      // ← ユーザー名
            ""                         // ← パスワード（空）
        );
    }
}
