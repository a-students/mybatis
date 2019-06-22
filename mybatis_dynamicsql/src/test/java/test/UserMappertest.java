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
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen
 * @date 2019/6/13--10:36
 */
public class UserMappertest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resources="mybatis_config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resources);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    //测试if
    @Test
    public void test() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> users = mapper.getuserbyconditionif(new User(null, "%n%", null));
            System.out.println(users);
  /*          查询时如果某些条件没带可能sql拼装会有问题
            1.给where后面添加 1=1,以后的条件都 and xxx
            2.mybatis推荐使用where标签来将所有的查询条件包括在内,mybatis会将where标签中拼装的sql,多出来的and或者or去掉
                但where标签只能去除前面多出来的and或or,对于后面的无能为力
            */
        }finally {
            session.close();
        }
    }
    //测试trim
    @Test
    public void test1() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.getuserbyconditiontrim(new User(null, "%n%", null));
        for (User user:users
             ) {
            System.out.println(user);
        }
        session.close();
    }
    @Test
    //测试choose
    public void test2() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.getuserbyconditionchoose(new User(null, null, null));
        for (User user:users
        ) {
            System.out.println(user);
        }
        session.close();
    }
    @Test
    //测试set
    public void test3() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.updateuser(new User(1, "wen", null));
        session.commit();
        session.close();
    }
    @Test
    //测试foreach
    public void test4() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<User> users = mapper.getuserbyconditionforeach(list);
        for (User user:users
             ) {
            System.out.println(user);
        }
        session.close();
    }
}
