package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * ① 一覧取得: 指定された学校の科目一覧を取得する
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from subject where school_cd = ? order by cd asc"
            );
            statement.setString(1, school.getCd());
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }

    /**
     * ② 登録・更新: 科目情報をDBに保存する
     */
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Subject check = get(subject.getCd(), subject.getSchool());
            
            if (check == null) {
                // 新規登録 (INSERT)
                statement = connection.prepareStatement(
                    "insert into subject (cd, name, school_cd) values (?, ?, ?)"
                );
                statement.setString(1, subject.getCd());
                statement.setString(2, subject.getName());
                statement.setString(3, subject.getSchool().getCd());
            } else {
                // 更新 (UPDATE)
                statement = connection.prepareStatement(
                    "update subject set name = ? where cd = ? and school_cd = ?"
                );
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getSchool().getCd());
            }
            count = statement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }

    /**
     * 1件取得
     */
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                "select * from subject where cd = ? and school_cd = ?"
            );
            statement.setString(1, cd);
            statement.setString(2, school.getCd());
            ResultSet rSet = statement.executeQuery();
            
            if (rSet.next()) {
                subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return subject;
    }

    /**
     * 更新
     */
    public int update(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(
                "update subject set name=? where cd = ? and school_cd = ?"
            );
            statement.setString(1, subject.getName());
            statement.setString(2, subject.getCd());
            statement.setString(3, subject.getSchool().getCd());
            
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    /**
     * 削除 (Subjectオブジェクト引数)
     */
    public boolean delete(Subject subject) throws Exception {
        return delete(subject.getCd(), subject.getSchool());
    }

    /**
     * 削除 (コードと学校引数)
     */
    public boolean delete(String cd, School school) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int line = 0;
        try {
            statement = connection.prepareStatement(
                "delete from subject where cd = ? and school_cd = ?"
            );
            statement.setString(1, cd);
            statement.setString(2, school.getCd());
            line = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return line > 0;
    }
}
  