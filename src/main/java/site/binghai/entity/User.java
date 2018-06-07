package site.binghai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IceSea on 2018/5/5.
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String img;
    private String email;
    private String userName;
    private String pass;

    private String personalitySignature; // 个性签名
    private String gender;
    private String city;
    private Integer vip; //vip字段 0不是 1是

    public Long getUid(){return id;}


}
