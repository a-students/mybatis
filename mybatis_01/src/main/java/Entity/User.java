package Entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author chen
 * @date 2019/6/4--14:18
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String lastname;
    private String gender;
}
