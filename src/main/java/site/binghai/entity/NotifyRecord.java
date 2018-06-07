package site.binghai.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * by dbw
 * 2018-6-02
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifyRecord extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long uid;
    private String uname;
    private Long toid;
    private String toname;
    private Long cid;
    private String title;
    private int  flag;

    @Override
    public Long getId() {
        return this.id;
    }
}
