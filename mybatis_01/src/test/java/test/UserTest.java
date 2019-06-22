package test;

import Entity.User;
import Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;


/*
* 1.接口式编程
*   原生:  Dao  ====>  DaoImpl
*   mybatis  Mapper  ====>  xxMapper.xml
* 2.SqlSession代表和数据库的一次会话,用完必须关闭
* 3.SqlSession和Connection一样都是非线程安全,每次使用都应该获取新的对象,不能放在成员变量中,否则产生线程竞争
* 4.mapper接口没有实现类,但mybatis会为这个接口生成一个代理对象
*       (将接口和xml进行绑定)以生成代理对象
* 5.两个重要配置文件
*           mybatis的全局配置文件,包含数据库连接池信息,事务管理器信息等。。。系统运行环境信息
*           sql映射文件:保存了每一个sql语句的映射信息
*                           将sql抽取出来
*
* */
/**
 * @author chen
 * @date 2019/6/4--14:27
 */
public class UserTest {
    /*
    * 1.根据xml全局配置文件创建一个SqlSessionFactory对象,有数据源和一些运行环境信息
    * 2.sql映射文件;配置了每一个sql,以及sql的封装规则
    * 3.将sql映射文件注册在全局配置文件中
    *
    *
    *
    * 4.写代码
    *   1)根据全局配置文件得到SqlSessionFactory;
    *   2)使用SqlSessionFactory工厂,获取到SqlSession对象用来执行sql语句
    *       一个SqlSession就是代表和数据库的一次会话,用完即关
    *   3)使用sql的唯一标识来告诉Mybatis
    *
    *
    * */
    /*
    *   1：Step Over ,进入下一步，如果是方法，那就直接跳过（F8）

        2：Step Into,进入下一步，如果是方法，就进入方法内部，但是不会进入jdk封装的方法。（F7）

        3：Force Step Into:强制进入下一步，不管是什么方法，即使是jdk封装的方法，也会进入。(Alt+Shift+F7)

        4：Step Out:跳转到下一个断点，没有一直运行到最后。（Shift+F8）

        5: Run to Cursor:运行到光标处 (Alt+F9)

        F7	在 Debug 模式下，进入下一步，如果当前行断点是一个方法，则进入当前方法体内，如果该方法体还有方法，则不会进入该内嵌的方法中 必备
        F8	在 Debug 模式下，进入下一步，如果当前行断点是一个方法，则不进入当前方法体内 必备
        F9	在 Debug 模式下，恢复程序运行，但是如果该断点下面代码还有断点则停在下一个断点上 必备
        Alt + F8	在 Debug 的状态下，选中对象，弹出可输入计算表达式调试框，查看该输入内容的调试结果 必备
        Ctrl + F8	在 Debug 模式下，设置光标当前行为断点，如果当前已经是断点则去掉断点
        Shift + F7	在 Debug 模式下，智能步入。断点所在行上有多个方法调用，会弹出进入哪个方法
        Shift + F8	在 Debug 模式下，跳出，表现出来的效果跟 F9 一样
        Ctrl + Shift + F8	在 Debug 模式下，指定断点进入条件
        Alt + Shift + F7	在 Debug 模式下，进入下一步，如果当前行断点是一个方法，则进入当前方法体内，如果方法体还有方法，则会进入该内嵌的方法中，依此循环进入
        Drop Frame	这个不是一个快捷键，而是一个 Debug 面板上的按钮。该按钮可以用来退回到当前停住的断点的上一层方法上，可以让过掉的断点重新来过
    * */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resources="mybatis_config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resources);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void test1() throws IOException {
        SqlSessionFactory factory=getSqlSessionFactory();
        SqlSession session = factory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            System.out.println(mapper.getClass());
            User user = mapper.queryallbyid(1);
            System.out.println(user);
        }finally {
            session.close();
        }



    }
}
