package bean;

import java.io.Serializable;

public class Test implements Serializable {

    /** 回数 */
    private int no;

    /** 学生 */
    private Student student;

    /** 科目 */
    private Subject subject;

    /** 点数 */
    private Integer point;

    /** クラス番号 */
    private String classNum;

    /** 学校 */
    private School school;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}