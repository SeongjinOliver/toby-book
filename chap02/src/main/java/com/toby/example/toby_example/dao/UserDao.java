package com.toby.example.toby_example.dao;

import com.toby.example.toby_example.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {

  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void add(User user) throws ClassNotFoundException, SQLException {

    Connection c = dataSource.getConnection();

    PreparedStatement ps = c.prepareStatement(
        "insert into users(id, name, password) values(?,?,?)");
    ps.setString(1, user.getId());
    ps.setString(2, user.getName());
    ps.setString(3, user.getPassword());

    ps.executeUpdate();

    ps.close();
    c.close();
  }

  // DB 내용들을 가져와서 User에 저장
  public User get(String id) throws ClassNotFoundException, SQLException {

    Connection c = dataSource.getConnection();

    PreparedStatement ps = c.prepareStatement(
        "select * from users where id = ?");
    ps.setString(1, id);

    ResultSet rs = ps.executeQuery();
    // rs.next() Description
    // - next() 메서드의 리턴 타입은 boolean인데 다음 행의 결과물(필드)이 있으면 true, 없으면 false을 리턴
    // ResultSet의 현재 레코드를 가리키는 포인터인 커서를 이동시킬 뿐이고 커서가 가리키고 있는 현재 레코드로 부터 필드 값을
    // 읽어오려면 필드값의 유형에 따라 getXXX()메소드를 사용해야 함
    // - 초기 ResultSet의 포인터는 첫 첫째 레코드 전의 위치를 가리키고 있기 때문에 ResultSet의
    // 첫번째 레코드로 포인터를 이동시키기 위해 next()메소드를 사용해야 함
    // rs.next();

    //User user = new User();
    User user = null;
    if(rs.next()) {
      user.setId(rs.getString("id"));
      user.setName(rs.getString("name"));
      user.setPassword(rs.getString("password"));
    }

    rs.close();
    ps.close();
    c.close();

    if(user == null) throw new EmptyResultDataAccessException(1);

    return user;
  }

  // user 테이블의 모든 레코드를 삭제해주는 간단한 기능
  public void deleteAll() throws SQLException {
    Connection c = dataSource.getConnection();

    PreparedStatement ps = c.prepareStatement("delete from users");

    ps.executeUpdate();

    ps.close();
    c.close();
  }

  // user 테이블의 레코드 개수를 돌려준다.
  public int getCount() throws SQLException {
    Connection c = dataSource.getConnection();

    PreparedStatement ps = c.prepareStatement("select count(*) from users");

    ResultSet rs = ps.executeQuery();
    rs.next();
    int count = rs.getInt(1);

    rs.close();
    ps.close();
    c.close();

    return count;
  }



}
