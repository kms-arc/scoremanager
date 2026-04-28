package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {
    private int entYear;         // 入学年度
    private String studentNo;    // 学生番号
    private String studentName;  // 氏名
    private String classNum;     // クラス番号
    private Map<Integer, Integer> points; // 回数と得点のセット（例：1回目->80点）

    public TestListSubject() {
    }

    // --- ゲッターとセッター ---
    public int getEntYear() {
        return entYear;
    }
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentNo() {
        return studentNo;
    }
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Map<Integer, Integer> getPoints() {
        return points;
    }
    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }

    // --- DAOで使っている便利なメソッド ---

    /**
     * 特定の回の得点を追加する
     * @param testNo 回数
     * @param point 得点
     */
    public void putPoint(int testNo, int point) {
        this.points.put(testNo, point);
    }
}