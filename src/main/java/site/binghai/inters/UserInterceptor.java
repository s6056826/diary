package site.binghai.inters;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by IceSea on 2018/4/5.
 * GitHub: https://github.com/IceSeaOnly
 * 用户登录拦截器
 */
public class UserInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") != null) {
            //设置session过期时间2天
            session.setMaxInactiveInterval(3600*48);
            return true;
        }

        response.getWriter().write(fail("NOT LOGIN").toJSONString());
        return false;
    }

    public JSONObject fail(String err) {
        JSONObject object = new JSONObject();
        object.put("status", "FAIL");
        object.put("msg", err);
        object.put("code", 1);
        return object;
    }

}
