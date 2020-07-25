package com.toby.example.toby_example.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
  public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
