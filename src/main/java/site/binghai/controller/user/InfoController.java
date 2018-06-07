package site.binghai.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.binghai.controller.BaseController;
import site.binghai.entity.User;
import site.binghai.service.BaseService;
import site.binghai.service.UserService;
import site.binghai.utils.ExceptionGenerator;

import java.util.Map;

/**
 * Created by IceSea on 2018/5/17.
 * GitHub: https://github.com/IceSeaOnly
 */
@RequestMapping("/user/info/")
@RestController
public class InfoController extends BaseController<User> {
    @Autowired
    private UserService userService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    @Override
    protected void beforeUpdate(Map map) throws Exception {
        Long id = getLong(map, "id");
        if (!getUser().getId().equals(id)) {
            logger.error("user {} want to update user {},identify failed!", getUser(), map);
            throw ExceptionGenerator.identifyFailed();
        }
        User user = userService.findById(id);
        map.put("pass", user.getPass());
    }

    @GetMapping("info")
    public Object getInfo(Long uid) {
        if (uid != null) {
            Object obj = userService.getSimpleUserInfoById(uid);
            if (obj == null) return fail("用户不存在!");
            return success(obj, null);
        }

        User user = getUser();
        user = userService.findById(user.getId());
        getSession().setAttribute("user", user);
        user.setPass(null);
        return success(user, null);
    }
}
