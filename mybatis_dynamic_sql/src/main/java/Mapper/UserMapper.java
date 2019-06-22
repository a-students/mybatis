package Mapper;

import Entity.User;

import java.util.List;

/**
 * @author chen
 * @date 2019/6/12--18:57
 */
public interface UserMapper {
    public List<User> getuseranddynamic(User user);
}
