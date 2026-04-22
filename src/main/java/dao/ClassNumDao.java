package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

    // ★ 1件取得
    public ClassNum get(String classNum, School school) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        ClassNum cn = null;

        try {
            st = con.prepareStatement(
                "SELECT * FROM CLASS_NUM WHERE CLASS_NUM = ? AND SCHOOL_CD = ?"
            );
            st.setString(1, classNum);
            st.setString(2, school.getCd());

            rs = st.executeQuery();

            if (rs.next()) {
                cn = new ClassNum();
                cn.setCd(rs.getString("CLASS_NUM"));  // ← 修正
                cn.setSchool(school);
            }

        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return cn;
    }

    // ★ クラス番号一覧取得
    public List<String> filter(School school) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();

        try {
            st = con.prepareStatement(
                "SELECT CLASS_NUM FROM CLASS_NUM WHERE SCHOOL_CD = ? ORDER BY CLASS_NUM"
            );
            st.setString(1, school.getCd());

            rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("CLASS_NUM"));  // ← 修正
            }

        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return list;
    }
}
