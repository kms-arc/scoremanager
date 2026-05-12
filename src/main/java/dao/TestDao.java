package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    // 1件取得（変更画面・削除画面で使う）
    public Test get(String studentNo, String subjectCd, School school, int no) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        Test test = null;

        try {
            st = con.prepareStatement(
                "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?"
            );

            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setString(3, school.getCd());
            st.setInt(4, no);

            rs = st.executeQuery();

            if (rs.next()) {
                test = new Test();

                test.setNo(rs.getInt("NO"));

                // POINTはNULLの可能性がある
                int p = rs.getInt("POINT");
                if (rs.wasNull()) {
                    test.setPoint(null);
                } else {
                    test.setPoint(p);
                }

                test.setClassNum(rs.getString("CLASS_NUM"));
                test.setSchool(school);

                Student student = new Student();
                student.setNo(rs.getString("STUDENT_NO"));
                student.setSchool(school);
                test.setStudent(student);

                Subject subject = new Subject();
                subject.setCd(rs.getString("SUBJECT_CD"));
                subject.setSchool(school);
                test.setSubject(subject);
            }

        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return test;
    }

    // 点数更新（UPDATEのみ）
    public void updatePoint(String studentNo, String subjectCd, School school, int no, int point) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement(
                "UPDATE TEST SET POINT = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?"
            );

            st.setInt(1, point);
            st.setString(2, studentNo);
            st.setString(3, subjectCd);
            st.setString(4, school.getCd());
            st.setInt(5, no);

            st.executeUpdate();

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }

    // ★ UPDATEできなければINSERT（成績変更で使う）
    public void upsertPoint(String studentNo, String subjectCd, School school, int no, int point, String classNum) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            // まずUPDATE
            st = con.prepareStatement(
                "UPDATE TEST SET POINT = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?"
            );

            st.setInt(1, point);
            st.setString(2, studentNo);
            st.setString(3, subjectCd);
            st.setString(4, school.getCd());
            st.setInt(5, no);

            int count = st.executeUpdate();
            st.close();

            // UPDATEできなかったらINSERT
            if (count == 0) {
                st = con.prepareStatement(
                    "INSERT INTO TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)"
                );

                st.setString(1, studentNo);
                st.setString(2, subjectCd);
                st.setString(3, school.getCd());
                st.setInt(4, no);
                st.setInt(5, point);
                st.setString(6, classNum);

                st.executeUpdate();
            }

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }

    // 登録（成績登録）
    public void insert(Test test) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement(
                "INSERT INTO TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)"
            );

            st.setString(1, test.getStudent().getNo());
            st.setString(2, test.getSubject().getCd());
            st.setString(3, test.getSchool().getCd());
            st.setInt(4, test.getNo());
            st.setObject(5, test.getPoint());
            st.setString(6, test.getClassNum());

            st.executeUpdate();

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }

    // 削除（成績削除）
    public void delete(String studentNo, String subjectCd, School school, int no) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = null;

        try {
            st = con.prepareStatement(
                "DELETE FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?"
            );

            st.setString(1, studentNo);
            st.setString(2, subjectCd);
            st.setString(3, school.getCd());
            st.setInt(4, no);

            st.executeUpdate();

        } finally {
            if (st != null) st.close();
            if (con != null) con.close();
        }
    }
}