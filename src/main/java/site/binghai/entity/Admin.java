package site.binghai.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

/**
 * Created by IceSea on 2018/5/5.
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@Entity
public class Admin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Admin(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
