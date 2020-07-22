package com.toby.example.toby_example.dao;

import com.toby.example.toby_example.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//public abstract class UserDao {
public class UserDao {
  //public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
  //private SimpleConnectioMaker simpleConnectioMaker;
  private ConnectionMaker connectionMaker;
  public UserDao() {
    //simpleConnectioMaker  = new SimpleConnectioMaker();
    connectionMaker = new DConnectionMaker();
  }
//  private Connection getConnection() throws ClassNotFoundException, SQLException {
//    Class.forName("com.mysql.jdbc.Driver");
//    Connection c = DriverManager.getConnection(
//        "jdbc:mysql://localhost:3306/toby_book?serverTimezone=Asia/Seoul&useSSL=false", "root", "manager");
//    return c;
//  }

  public void add(User user) throws ClassNotFoundException, SQLException {

    //Connection c = getConnection();
    //Connection c = simpleConnectioMaker.makeNewConnection();
    Connection c = connectionMaker.makeConnection();

    PreparedStatement ps = c.prepareStatement(
        "insert into users(id, name, password) values(?,?,?)");
    ps.setString(1, user.getId());
    ps.setString(2, user.getName());
    ps.setString(3, user.getPassword());

    ps.executeUpdate();

    ps.close();
    c.close();
  }

  public User get(String id) throws ClassNotFoundException, SQLException {

    //Connection c = getConnection();
    //Connection c = simpleConnectioMaker.makeNewConnection();
    Connection c = connectionMaker.makeConnection();

    PreparedStatement ps = c.prepareStatement(
        "select * from users where id = ?");
    ps.setString(1, id);

    ResultSet rs = ps.executeQuery();
    // rs.next() Description
    // - next() 메서드의 리턴 타입은 boolean인데 다음 행의 결과물(필드)이 있으면 true, 없으면 false을 리턴
    // ResultSet의 현재 레코드를 가리키는 포인터인 커서를 이동시킬 뿐이고 커서가 가리키고 있는 현재 레코드로 부터 필드 값을
    // 읽어오려면 필드값의 유형에 따라 getXXX()메소드를 사용해야 함
    // - 초기 ResultSet의 포인터는 첫 첫째 레코드 전의 위치를 가리키고 있기 때문에 ResultSet의
    // 첫번째 레코드로 포인터를 이동시키기 위해 next()메소드를 사용해야 합니다.
    rs.next();
    User user = new User();
    user.setId(rs.getString("id"));
    user.setName(rs.getString("name"));
    user.setName(rs.getString("password"));

    rs.close();
    ps.close();
    c.close();

    return user;
  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    UserDao dao = new UserDao();

    User user = new User();
    user.setId("whiteship");
    user.setName("백기선");
    user.setPassword("married");

    dao.add(user);

    System.out.println(user.getId() + " 등록 성공");

    User user2 = dao.get(user.getId());
    System.out.println(user2.getName());
    System.out.println(user2.getPassword());

    System.out.println(user2.getId() + " 조회 성공");

  }

}
