package site.binghai.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.binghai.entity.Admin;
import site.binghai.entity.BaseEntity;
import site.binghai.entity.User;
import site.binghai.service.BaseService;
import site.binghai.utils.BaseBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by IceSea on 2018/5/5.
 * GitHub: https://github.com/IceSeaOnly
 */
public abstract class BaseController<T extends BaseEntity> extends BaseBean {

    protected abstract BaseService<T> getService();

    protected void beforeAdd(Map map) throws Exception {
    }

    protected void afterAdd(T t) throws Exception {
    }

    protected void beforeUpdate(Map map) throws Exception {
    }

    protected void afterUpdate(T t) throws Exception {
    }

    protected void beforeDelete(Long id) throws Exception {
    }

    protected void afterDelete(Long id) throws Exception {
    }



    @PostMapping("update")
    public Object update(@RequestBody Map map) {
        try {
            beforeUpdate(map);
            T t = getService().updateAndSave(map);
            afterUpdate(t);
            return success(t, "SUCCESS");
        } catch (Exception e) {
            logger.error("update failed!{}", map, e);
            return fail(e.getMessage());
        }
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id) {
        try {
            beforeDelete(id);
            getService().delete(id);
            afterDelete(id);
            return success();
        } catch (Exception e) {
            logger.error("delete failed! id={}", id, e);
            return fail(e.getMessage());
        }
    }

    @PostMapping("add")
    public Object add(@RequestBody Map map) {
        try {
            beforeAdd(map);
            T t = getService().newInstance(map);
            t = getService().save(t);
            afterAdd(t);
            return success(t, "SUCCESS");
        } catch (Exception e) {
            logger.error("add failed! {}", map, e);
            return fail(e.getMessage());
        }
    }


    protected String commonResp(String title, String info, String btn, String url, ModelMap map) {
        map.put("title", title);
        map.put("btn", btn);
        map.put("info", info);
        map.put("url", url);
        return "commonResp";
    }

    /**
     * 从thread local获取网络上下文
     */
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取当前客户端session对象
     * @return
     */
    public HttpSession getSession() {
        return getServletRequest().getSession();
    }

    public Admin getAdmin() {
        Object obj = getServletRequest().getSession().getAttribute("admin");
        return obj == null ? null : (Admin) obj;
    }

    public User getUser() {
        Object obj = getServletRequest().getSession().getAttribute("user");
        return obj == null ? null : (User) obj;
    }

    public JSONObject fail(String err) {
        JSONObject object = new JSONObject();
        object.put("status", "FAIL");
        object.put("msg", err);
        object.put("code", 1);
        return object;
    }

    public Object jsoupFail(String err, String callback) {
        if (StringUtils.isEmpty(callback)) {
            return fail(err);
        }
        return callback + "(" + fail(err).toJSONString() + ")";
    }

    public JSONObject success() {
        JSONObject object = new JSONObject();
        object.put("status", "SUCCESS");
        object.put("code", 0);
        return object;
    }

    public JSONObject success(String msg) {
        JSONObject object = new JSONObject();
        object.put("status", "SUCCESS");
        object.put("msg", msg);
        object.put("code", 0);
        return object;
    }

    public Object jsoupSuccess(Object data, String msg, String callBack) {
        if (StringUtils.isEmpty(callBack)) {
            return success(data, msg);
        }
        callBack = callBack == null ? "" : callBack;
        return callBack + "(" + success(data, msg).toJSONString() + ")";
    }

    public JSONObject success(Object data, String msg) {
        JSONObject object = new JSONObject();
        object.put("status", "SUCCESS");
        object.put("data", data);
        object.put("msg", msg);
        object.put("code", 0);
        return object;
    }
}
