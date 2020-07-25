package com.toby.example.toby_example.dao;

import java.sql.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
// 오브젝트를 생성하는 쪽과 생성된 오브젝트를 사용한는 쪽의 역할과 책임을 깔끔하게 분리하려는 목적으로 사용
public class DaoFactory {

  @Bean // 오브젝트 생성을 담당하는 Ioc용 메소드라는 표시
  public UserDao userDao() {
//    ConnectionMaker connectionMaker = new DConnectionMaker();
//    UserDao userDao = new UserDao(connectionMaker);
//    return userDao;
    return new UserDao(connectionMaker());
  }

  @Bean
  public ConnectionMaker connectionMaker() {
    return new DConnectionMaker();
  }

  // 만약 다른 데이터베이스를 연결하고자 한다면 메서드를 추가하고 빈으로 등록
}
