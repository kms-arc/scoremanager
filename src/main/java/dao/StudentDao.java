package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao {

    private static final String URL = "jdbc:h2:file:~/kaihatu";
    private static final String USER = "sa";
    private static final String PASS = "";

    // ★ 年度一覧取得（追加）
    public List<Integer> getEntYearList(School school) {

        List<Integer> list = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            PreparedStatement st = con.prepareStatement(
                "SELECT DISTINCT ENT_YEAR FROM STUDENT WHERE SCHOOL_CD = ? ORDER BY ENT_YEAR DESC"
            );
            st.setString(1, school.getCd());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("ENT_YEAR"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ★ 全件取得
    public List<Student> filter(School school) {

        List<Student> list = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            PreparedStatement st = con.prepareStatement(
                "SELECT * FROM STUDENT WHERE SCHOOL_CD = ? ORDER BY NO"
            );
            st.setString(1, school.getCd());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getString("NO"));
                s.setName(rs.getString("NAME"));
                s.setEntYear(rs.getInt("ENT_YEAR"));
                s.setClassNum(rs.getString("CLASS_NUM"));
                s.setAttend(rs.getBoolean("IS_ATTEND"));
                s.setSchool(school);
                list.add(s);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ★ 在学のみ取得
    public List<Student> filter(School school, boolean isAttend) {

        List<Student> list = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            PreparedStatement st = con.prepareStatement(
                "SELECT * FROM STUDENT WHERE SCHOOL_CD = ? AND IS_ATTEND = ? ORDER BY NO"
            );
            st.setString(1, school.getCd());
            st.setBoolean(2, isAttend);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getString("NO"));
                s.setName(rs.getString("NAME"));
                s.setEntYear(rs.getInt("ENT_YEAR"));
                s.setClassNum(rs.getString("CLASS_NUM"));
                s.setAttend(rs.getBoolean("IS_ATTEND"));
                s.setSchool(school);
                list.add(s);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
    public Student find(String no, School school) {

        Student s = null;

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            PreparedStatement st = con.prepareStatement(
                "SELECT * FROM STUDENT WHERE NO = ? AND SCHOOL_CD = ?"
            );

            st.setString(1, no);
            st.setString(2, school.getCd());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                s = new Student();
                s.setNo(rs.getString("NO"));
                s.setName(rs.getString("NAME"));
                s.setEntYear(rs.getInt("ENT_YEAR"));
                s.setClassNum(rs.getString("CLASS_NUM"));
                s.setAttend(rs.getBoolean("IS_ATTEND"));
                s.setSchool(school);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    //編集
    public void update(Student s) {

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            PreparedStatement st = con.prepareStatement(
                "UPDATE STUDENT SET NAME=?, ENT_YEAR=?, CLASS_NUM=?, IS_ATTEND=? " +
                "WHERE NO=? AND SCHOOL_CD=?"
            );

            st.setString(1, s.getName());
            st.setInt(2, s.getEntYear());
            st.setString(3, s.getClassNum());
            st.setBoolean(4, s.isAttend());
            st.setString(5, s.getNo());
            st.setString(6, s.getSchool().getCd());

            st.executeUpdate();

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//学生新規登録
    public void insert(Student s) {

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            PreparedStatement st = con.prepareStatement(
                "INSERT INTO STUDENT(NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );

            st.setString(1, s.getNo());
            st.setString(2, s.getName());
            st.setInt(3, s.getEntYear());
            st.setString(4, s.getClassNum());
            st.setBoolean(5, s.isAttend());
            st.setString(6, s.getSchool().getCd());

            st.executeUpdate();

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ★ 絞り込み検索（追加）
    public List<Student> search(School school, int entYear, String classNum, boolean isAttend) {

        List<Student> list = new ArrayList<>();

        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            // ★ SQL を動的に組み立てる
            String sql = "SELECT * FROM STUDENT WHERE SCHOOL_CD = ?";
            
            if (entYear != 0) {
                sql += " AND ENT_YEAR = " + entYear;
            }
            if (classNum != null && !classNum.equals("0")) {
                sql += " AND CLASS_NUM = '" + classNum + "'";
            }
            if (isAttend) {
                sql += " AND IS_ATTEND = TRUE";
            }

            sql += " ORDER BY NO";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, school.getCd());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getString("NO"));
                s.setName(rs.getString("NAME"));
                s.setEntYear(rs.getInt("ENT_YEAR"));
                s.setClassNum(rs.getString("CLASS_NUM"));
                s.setAttend(rs.getBoolean("IS_ATTEND"));
                s.setSchool(school);
                list.add(s);
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
