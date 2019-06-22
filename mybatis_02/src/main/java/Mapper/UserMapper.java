package Mapper;

import Entity.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import java.util.Map;


/**
 * @author chen
 * @date 2019/6/4--20:15
 */
public interface UserMapper {
    //返回一条记录的map,key就是列名,值就是对应的值
    public Map<String,Object> getuserbyidreturnmap(Integer id);
    //多条记录封装一个map,map<Integer,User>,键是这条记录的主键,值是记录封装后的javabean
    @MapKey("id")//告诉mybatis封装这个map时使用哪个属性作为key
    public Map<Integer,User> getuserbylastnamereturnmap(String lastname);
    public User queryall(Integer id);
    public User selectbyidandlastname(@Param("id") Integer id, @Param("lastname") String lastname);
}
