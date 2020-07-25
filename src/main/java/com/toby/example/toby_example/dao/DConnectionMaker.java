package com.toby.example.toby_example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
  public Connection makeConnection() throws ClassNotFoundException, SQLException {
    // D 사의 독자적인 방법으로 Connection을 생성하는 코드
    Class.forName("com.mysql.jdbc.Driver");
    Connection c = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/toby_book?serverTimezone=Asia/Seoul&useSSL=false", "root", "manager");
    return c;
  }
}
