package test;

import Entity.User;
import Mapper.UserMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chen
 * @date 2019/6/10--15:03
 */
public class UserMapperPlusTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resources="mybatis_config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resources);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void test1() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapperPlus mapper = session.getMapper(Mapper.UserMapperPlus.class);
        User user = mapper.getuserbyid(1);
        System.out.println(user);
    }
    @Test
    public void test2() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapperPlus mapper = session.getMapper(Mapper.UserMapperPlus.class);
        User user = mapper.getuserbyidstep(1);
        System.out.println(user.getLastname());
        //System.out.println(user.getDepartment());
    }
}
