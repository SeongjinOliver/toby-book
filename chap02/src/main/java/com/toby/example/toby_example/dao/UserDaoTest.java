package com.toby.example.toby_example.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.toby.example.toby_example.domain.User;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/* @RunWith : 스프링의 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정
 SpringJUnit4ClassRunner 임포트 안되다가 spring-boot-starter-test <scope>test</scope> 제거해서 됨 */
// @ContextConfiguration : 테스트 컨텍스트가 자동으로 만들어줄 애플리케이션 컨텍스트의 위치 지정
// @DirtiesContext : 테스트 메소드에서 애플리케이션 컨텍스트ㅢ 구성이나 상태를 변경한다는 것을 테스트 컨텍스트 프레임워크에 알려줌.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@DirtiesContext
public class UserDaoTest {

  // setUp() 메소드에서 만드는 오브젝트를 테스트 메소드에서 사용할 수 있도록 인스턴스 변수로 선언함.
  @Autowired
  UserDao dao; //UserDao 타입 빈을 직접 DI를 받음.
  private User user1;
  private User user2;
  private User user3;

  @Autowired
  private ApplicationContext context; // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 값이 주입 됨.

  // JUnit이 제공하는 애노테이션, @Test 메소드가 실행되기 전에는 먼저 실행돼야 하는 메소드를 정의 함.
  @Before
  public void setUp() {
    System.out.println(this.context);
    System.out.println(this);
    //context = new GenericXmlApplicationContext("applicationContext.xml");
    //this.dao = context.getBean("userDao", UserDao.class);

    this.user1 = new User("gyumee", "박성철", "springno1");
    this.user2 = new User("leegw700", "이길원", "springno2");
    this.user3 = new User("bumjin", "박범진", "springno3");

    // 테스트에서 UserDao가 사용할 DataSource 오브젝트를 직접 생성함.
    DataSource dataSource = new SingleConnectionDataSource(
        "jdbc:mysql://localhost:3306/toby_book?serverTimezone=Asia/Seoul&amp;useSSL=false"
        , "toby_book", "manager", true);
    dao.setDataSource(dataSource); // 코드에 의한 수동 DI
  }

  @Test // Junit에게 테스트용 메소드임을 알려준다
  public void addAndGet() throws SQLException, ClassNotFoundException { // Junit 테스트 메소드는 반드시 public으로 선언돼야 함.

    dao.deleteAll();
    assertThat(dao.getCount(), is(0));

    dao.add(user1);
    dao.add(user2);
    assertThat(dao.getCount(), is(2));

    User userget1 = dao.get(user1.getId());
    assertThat(userget1.getName(), is(user1.getName()));
    assertThat(userget1.getPassword(), is(user1.getPassword()));

    User userget2 = dao.get(user2.getId());
    assertThat(userget2.getName(), is(user2.getName()));
    assertThat(userget2.getPassword(), is(user2.getPassword()));
  }

  public static void main(String[] args) {
    JUnitCore.main("com.toby.example.toby_example.dao.UserDaoTest");
  }

  // getCount Test 메소드
  @Test
  public void count() throws SQLException, ClassNotFoundException {

    dao.deleteAll();
    assertThat(dao.getCount(), is(0));

    dao.add(user1);
    assertThat(dao.getCount(), is(1));

    dao.add(user2);
    assertThat(dao.getCount(), is(2));

    dao.add(user3);
    assertThat(dao.getCount(), is(3));
  }

  @Test(expected = EmptyResultDataAccessException.class)
  public void getUserFailure() throws SQLException, ClassNotFoundException {

    dao.deleteAll();
    assertThat(dao.getCount(), is(0));

    dao.get("unknown_id");
  }

}
