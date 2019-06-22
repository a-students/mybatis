package Mapper;

import Entity.Department;

/**
 * @author chen
 * @date 2019/6/11--17:23
 */
public interface DepartmentPlus {
    public Department getdepartment(Integer id);

    public Department getdepartmentplus(Integer id);

    public Department getdeptbyidstep(Integer id);
}
