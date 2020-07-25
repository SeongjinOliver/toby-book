//package com.toby.example.toby_example.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//// 추상메서드가 아닌 독립적인 클래스
//public class SimpleConnectioMaker {
//  public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
//    Class.forName("com.mysql.jdbc.Driver");
//    Connection c = DriverManager.getConnection(
//        "jdbc:mysql://localhost:3306/toby_book?serverTimezone=Asia/Seoul&useSSL=false", "root", "manager");
//    return c;
//  }
//}
