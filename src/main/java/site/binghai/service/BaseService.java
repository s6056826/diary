package site.binghai.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import site.binghai.entity.Admin;
import site.binghai.entity.BaseEntity;
import site.binghai.utils.BaseBean;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by IceSea on 2018/5/5.
 * GitHub: https://github.com/IceSeaOnly
 */
public abstract class BaseService<T extends BaseEntity> extends BaseBean {
    @Autowired
    private EntityManager entityManager;
    private SimpleJpaRepository<T, Long> daoHolder;

    public T newInstance(Map map) {
        JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(map));
        return obj.toJavaObject(getTypeArguement());
    }

    protected JpaRepository<T, Long> getDao() {
        if (daoHolder != null) {
            return daoHolder;
        }
        daoHolder = new SimpleJpaRepository(getTypeArguement(), entityManager);
        return daoHolder;
    }

    /**
     * 获取T的实际类型
     */
    protected Class<T> getTypeArguement() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    @Transactional
    public T save(T t) {
        return getDao().save(t);
    }

    @Transactional
    public T update(T t) {
        if (t.getId() >= 0) {
            return save(t);
        }
        return t;
    }

    public T findById(Long id) {
        if (id == null) return null;
        return getDao().findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        getDao().deleteById(id);
    }

    @Transactional
    public T deleteIfExist(Long id) {
        T t = findById(id);
        if (t == null) return null;
        delete(id);
        return t;
    }

    @Transactional
    public boolean deleteAll(String confirm) {
        if (confirm.equals("confirm")) {
            getDao().deleteAll();
            return true;
        }
        return false;
    }

    public List<T> findByIds(List<Long> ids) {
        return getDao().findAllById(ids);
    }

    public List<T> query(T example) {
        example.setCreated(null);
        example.setCreatedTime(null);
        Example<T> ex = Example.of(example);
        return getDao().findAll(ex);
    }

    public List<T> pageQuery(T example,int page,int pageSize) {
        example.setCreated(null);
        example.setCreatedTime(null);
        Example<T> ex = Example.of(example);
        return getDao().findAll(ex,new PageRequest(page,pageSize)).getContent();
    }

    public T queryOne(T example) {
        example.setCreated(null);
        example.setCreatedTime(null);
        List<T> ls = query(example);
        return (ls == null || ls.size() == 0) ? null : ls.get(0);
    }

    public List<T> sortQuery(T example, String sortField, Boolean desc) {
        example.setCreated(null);
        example.setCreatedTime(null);
        Example<T> ex = Example.of(example);
        return getDao().findAll(ex, Sort.by(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sortField));
    }

    public List<T> sortQuery(T example,int page,int pageSize, String sortField, Boolean desc) {
        example.setCreated(null);
        example.setCreatedTime(null);
        Example<T> ex = Example.of(example);
        return getDao().findAll(ex, Sort.by(desc ? Sort.Direction.DESC : Sort.Direction.ASC, sortField));
    }

    public List<T> findAll(int limit) {
        return getDao().findAll(new PageRequest(0, limit)).getContent();
    }

    public List<T> findAll(int page, int pageSize) {
        return getDao().findAll(new PageRequest(page, pageSize)).getContent();
    }

    public long count() {
        return getDao().count();
    }

    @Transactional
    public void batchSave(List<T> batch) {
        getDao().saveAll(batch);
    }

    /**
     * 使用map更新entity中除id外的其他字段
     */
    public T updateParams(T t, Map map) {
        Long id = t.getId();
        JSONObject item = JSONObject.parseObject(JSONObject.toJSONString(t));
        item.putAll(map);
        item.put("id", id);
        return item.toJavaObject(getTypeArguement());
    }

    @Transactional
    public T updateAndSave(Map map) throws Exception {
        Long id = getLong(map, "id");
        if (id == null) {
            throw new Exception("id must be present!");
        }
        T old = findById(id);
        if (old == null) {
            throw new Exception("item not exist!");
        }
        T new_ = updateParams(old, map);
        return update(new_);
    }
}

