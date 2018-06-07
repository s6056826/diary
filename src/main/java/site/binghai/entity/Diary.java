package site.binghai.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by IceSea on 2018/5/5.
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String title;
    @Column(name = "class")
    @JSONField(name = "class")
    private Long clazz;
    private Integer praiseMark;

    @Transient
    private boolean praise; // 点赞标记

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getClazz() {
        return clazz;
    }

    public void setClazz(Long clazz) {
        this.clazz = clazz;
    }

    public Integer getPraiseMark() {
        return praiseMark;
    }

    public void setPraiseMark(Integer praiseMark) {
        this.praiseMark = praiseMark;
    }

    public boolean isPraise() {
        return praise;
    }

    public void setPraise(boolean praise) {
        this.praise = praise;
    }
}
