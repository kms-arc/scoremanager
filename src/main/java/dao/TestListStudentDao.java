package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

/**
 * 学生別成績一覧DAO。
 * 「ある学生の」全科目・全回数の得点一覧を取得する（成績参照画面用）。
 *
 * <p>TEST と SUBJECT を結合し、(科目名, 科目コード, 回数, 得点) の
 * 単純なフラット行を返す。</p>
 */
public class TestListStudentDao extends Dao {

    /** 共通SELECT。学生番号と学校コードの2条件をWHEREに付ける前提。 */
    private final String baseSql =
            "SELECT SUBJECT.NAME AS SUBJECT_NAME, SUBJECT.CD AS SUBJECT_CD, "
            + "TEST.NO AS NUM, TEST.POINT AS POINT "
            + "FROM TEST INNER JOIN SUBJECT "
            + "ON TEST.SUBJECT_CD = SUBJECT.CD "
            + "AND TEST.SCHOOL_CD = SUBJECT.SCHOOL_CD "
            + "WHERE TEST.STUDENT_NO = ? AND TEST.SCHOOL_CD = ? "
            + "ORDER BY SUBJECT.CD, TEST.NO";

    /**
     * ResultSet から TestListStudent のリストを組み立てる。
     * @param rSet 検索結果
     * @return TestListStudentリスト
     * @throws Exception DB例外など
     */
    public List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent tls = new TestListStudent();
            tls.setSubjectName(rSet.getString("SUBJECT_NAME"));
            tls.setSubjectCd(rSet.getString("SUBJECT_CD"));
            tls.setNum(rSet.getInt("NUM"));
            tls.setPoint(rSet.getInt("POINT"));
            list.add(tls);
        }
        return list;
    }

    /**
     * 指定した学生の成績一覧を取得する。
     * @param student 学生
     * @return TestListStudentリスト（該当なしの場合は空リスト）
     * @throws Exception DB例外など
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list;
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(baseSql)) {
            st.setString(1, student.getNo());
            st.setString(2, student.getSchool().getCd());
            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs);
            }
        }
        return list;
    }
}
