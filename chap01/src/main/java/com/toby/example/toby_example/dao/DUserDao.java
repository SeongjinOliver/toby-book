//package com.toby.example.toby_example.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DUserDao extends UserDao{
//  public Connection getConnection() throws ClassNotFoundException, SQLException {
//    Class.forName("com.mysql.jdbc.Driver");
//    Connection c = DriverManager.getConnection(
//        "jdbc:mysql://localhost:3306/toby_book?serverTimezone=Asia/Seoul&useSSL=false", "root", "manager");
//    return c;
//  }
//
//}
