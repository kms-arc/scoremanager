package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

/**
 * クラス別成績一覧DAO。
 * 「あるクラスの・ある科目の」全学生×全回数の得点一覧を取得する（成績参照画面用）。
 *
 * <p>STUDENT を主、TEST を外部結合し、得点未登録の学生も含めて返す。
 * 同一学生の複数回得点は points: Map&lt;回数, 得点&gt; に集約する。</p>
 */
public class TestListSubjectDao extends Dao {

    /** 共通SELECT。STUDENT 主・TEST 従の LEFT JOIN。 */
    private final String baseSql =
            "SELECT S.ENT_YEAR AS ENT_YEAR, "
            + "S.NO AS STUDENT_NO, "
            + "S.NAME AS STUDENT_NAME, "
            + "S.CLASS_NUM AS CLASS_NUM, "
            + "T.NO AS TEST_NO, "
            + "T.POINT AS POINT "
            + "FROM STUDENT S LEFT JOIN TEST T "
            + "ON S.NO = T.STUDENT_NO "
            + "AND T.SUBJECT_CD = ? "
            + "AND T.SCHOOL_CD = ? "
            + "WHERE S.SCHOOL_CD = ? "
            + "AND S.ENT_YEAR = ? "
            + "AND S.CLASS_NUM = ? "
            + "AND S.IS_ATTEND = TRUE "
            + "ORDER BY S.NO, T.NO";

    /**
     * ResultSet から TestListSubject のリストを組み立てる。
     * 同一学生の複数行を points: Map に集約する。
     *
     * @param rSet 検索結果（学生単位でソート済みであること）
     * @return TestListSubjectリスト
     * @throws Exception DB例外など
     */
    public List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        // 学生番号 → TestListSubject の対応で集約
        Map<String, TestListSubject> map = new LinkedHashMap<>();
        while (rSet.next()) {
            String studentNo = rSet.getString("STUDENT_NO");
            TestListSubject tls = map.get(studentNo);
            if (tls == null) {
                tls = new TestListSubject();
                tls.setEntYear(rSet.getInt("ENT_YEAR"));
                tls.setStudentNo(studentNo);
                tls.setStudentName(rSet.getString("STUDENT_NAME"));
                tls.setClassNum(rSet.getString("CLASS_NUM"));
                tls.setPoints(new LinkedHashMap<Integer, Integer>());
                map.put(studentNo, tls);
            }
            // TEST が外部結合のため null になり得る
            int testNo = rSet.getInt("TEST_NO");
            if (!rSet.wasNull()) {
                int point = rSet.getInt("POINT");
                tls.putPoint(testNo, point);
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 入学年度・クラス番号・科目・学校で絞り込み、クラスの成績一覧を取得する。
     * @param entYear  入学年度
     * @param classNum クラス番号
     * @param subject  科目
     * @param school   学校
     * @return TestListSubjectリスト（該当なしの場合は空リスト）
     * @throws Exception DB例外など
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list;
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(baseSql)) {
            // ON句のパラメータ
            st.setString(1, subject.getCd());
            st.setString(2, school.getCd());
            // WHERE句のパラメータ
            st.setString(3, school.getCd());
            st.setInt(4, entYear);
            st.setString(5, classNum);
            try (ResultSet rs = st.executeQuery()) {
                list = postFilter(rs);
            }
        }
        return list;
    }
}
