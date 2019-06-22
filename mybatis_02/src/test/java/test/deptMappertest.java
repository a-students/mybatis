package test;

import Entity.Department;
import Entity.User;
import Mapper.DepartmentPlus;
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
 * @date 2019/6/11--20:58
 */
public class deptMappertest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resources="mybatis_config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resources);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void test1() throws IOException {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = factory.openSession();
        DepartmentPlus mapper = session.getMapper(DepartmentPlus.class);
        Department department = mapper.getdepartmentplus(1);
        System.out.println(department);
        System.out.println(department.getUsers());
    }
}
