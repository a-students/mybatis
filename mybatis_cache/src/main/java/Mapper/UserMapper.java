package Mapper;

import Entity.User;

import java.util.List;

/**
 * @author chen
 * @date 2019/6/12--19:03
 */
public interface UserMapper {
    //携带了哪个字段,查询时就拼上哪个字段的值
    public List<User> getuserbyconditionif(User user);

    public List<User> getuserbyconditiontrim(User user);

    public List<User> getuserbyconditionchoose(User user);

    public void updateuser(User user);

    public List<User> getuserbyconditionforeach(List<Integer> list);

    public User getuserbyid(Integer id);
}
