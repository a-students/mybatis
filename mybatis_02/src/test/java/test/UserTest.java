package test;

import Entity.User;
import Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

/**
 * @author chen
 * @date 2019/6/4--20:18
 */
public class UserTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resources="mybatis_config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resources);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    /*
    * 测试增删改
    *   1.mybatis允许增删改直接定义以下类型返回值
    *           Interger Long Boolean
    *   2.需要我们手动提交事务
    *       factory.openSession();==>手动提交事务
    *       factory.openSession(true);==>自动提交事务
    * */

    @Test
    public void test() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.queryall(1);
        System.out.println(user);
        session.close();
    }
    @Test
    public void test1() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectbyidandlastname(1, "chen");
        System.out.println(user);
        session.close();
    }
    @Test
    public void test2() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        /*Map<String, Object> map = mapper.getuserbyidreturnmap(1);
        System.out.println(map);*/
        Map<Integer,User> map = mapper.getuserbylastnamereturnmap("%n%");
        System.out.println(map);
        session.close();
    }
}
