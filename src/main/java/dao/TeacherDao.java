package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao {

    private static final String URL = "jdbc:h2:file:~/kaihatu";
    private static final String USER = "sa";
    private static final String PASS = "";

    // ★ ログイン認証
    public Teacher login(String id, String password) {

        Teacher t = null;

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            PreparedStatement st = con.prepareStatement(
                "SELECT * FROM TEACHER WHERE ID = ? AND PASSWORD = ?"
            );
            st.setString(1, id);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                t = new Teacher();
                t.setId(rs.getString("ID"));
                t.setPassword(rs.getString("PASSWORD"));
                t.setName(rs.getString("NAME"));

                School school = new School();
                school.setCd(rs.getString("SCHOOL_CD"));
                t.setSchool(school);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }
}
