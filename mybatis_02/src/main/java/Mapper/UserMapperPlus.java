package Mapper;

import Entity.User;

import java.util.List;

/**
 * @author chen
 * @date 2019/6/10--15:01
 */
public interface UserMapperPlus {

    public User getuserbyid(Integer id);

    public User getuseranddept(Integer id);

    public User getuserbyidstep(Integer id);

    public List<User> getuserbydeptid(Integer deptid);

}
