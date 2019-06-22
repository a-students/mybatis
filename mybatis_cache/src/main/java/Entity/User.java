package Entity;

import java.io.Serializable;

/**
 * @author chen
 * @date 2019/6/4--20:13
 */
//@Alias("")//指定别名
public class User implements Serializable {
    private Integer id;
    private String lastname;
    private String gender;

    public User() {
        super();
    }

    public User(Integer id, String lastname, String gender) {
        this.id = id;
        this.lastname = lastname;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
