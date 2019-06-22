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

/**
 * @author chen
 * @date 2019/6/14--14:51
 */
public class UserMapperTest {



    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resources="mybatis_config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resources);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    /*
    * 两级缓存:
    *   一级缓存:(本地缓存):SqlSession级别的缓存,一级缓存是一直开启的
    *               与数据库同一次会话期间查询到的数据会放在本地缓存中
    *               以后如果要获取相同的数据,直接从缓存中拿,没必要再去查询数据库
    * 一级缓存失效情况:(没有使用到当前一级缓存的情况.效果就是还需要再查询数据库发出查询)
    *       1.SqlSession不同
    *       2.SqlSession相同,但查询条件不同(当前一级缓存中没有这个数据)
    *       3.SqlSession相同,再次查询之前执行了增删改操作(这次增删改操作可能对当前数据有影响)
    *       4.SqlSession相同,手动清除了一级缓存(缓存清空)  session.clearCache();
    *
    *   二级缓存:(全局缓存):基于namespace级别的缓存,一个namespace对应一个二级缓存
    *       工作机制:
    *           1.一个方法,查询一条数据,这个数据就会放在当前会话的一级缓存中
    *           2.如果会话关闭,一级缓存中的数据就会被保存到二级缓存中,新的会话查询信息,就可以参照二级缓存
    *           3.不同的namespace查出的数据会被放在自己对应的缓存中(map)
    *       使用:
    *           1.开启全局二级缓存配置         <setting name="cacheEnabled" value="true"/>
    *           2.去每个mapper.xml中配置使用二级缓存        <cache></cache>
    *           3.我们的POJO需要实现序列化接口
    *和缓存有关的设置/属性
    *           1)cacheEnabled默认为true开启二级缓存  false:关闭缓存(二级缓存)(一级缓存一直可用)
    *           2)每个select标签都有useCache="true",
    *                       false:不使用缓存( 一级缓存依然使用,二级缓存不使用)
    *           3)每一个增删改标签上都有一个flushCache="true",
    *                               默认是增删改执行完成后就会清除缓存(一级缓存失效)(二级缓存失效)
    *           4) clearCache():    清除当前session的一级缓存
    *           5)localCacheScope:本地缓存作用域(影响一级缓存)
    *                                   SESSION:当前会话的所有数据
    *                                   STAEMENT:可以禁用一级缓存
    * */
    @Test
    //测试一级缓存
    public void test() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.getuserbyid(1);
            System.out.println(user);
            User user1 = mapper.getuserbyid(1);
            System.out.println(user1);
        }finally {
            session.close();
        }
    }
    @Test
    //测试二级缓存
    public void test1() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        SqlSession session1 = factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            UserMapper mapper1 = session1.getMapper(UserMapper.class);
            User user = mapper.getuserbyid(1);
            System.out.println(user);
            session.close();
            /*
            * Cache Hit Ratio [Mapper.UserMapper]: 0.5
            * */
            //代表从二级缓存中查询出来的
            User user1 = mapper1.getuserbyid(1);
            System.out.println(user1);
        }finally {
            session1.close();
        }
    }


}
