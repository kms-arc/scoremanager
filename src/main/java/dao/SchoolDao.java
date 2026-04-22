package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends Dao {

    public School get(String cd) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        School school = null;

        try {
            st = con.prepareStatement("select * from school where cd=?");
            st.setString(1, cd);
            rs = st.executeQuery();

            if (rs.next()) {
                school = new School();
                school.setCd(rs.getString("cd"));
                school.setName(rs.getString("name"));
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return school;
    }
}
