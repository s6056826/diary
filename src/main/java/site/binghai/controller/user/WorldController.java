package site.binghai.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.controller.BaseController;
import site.binghai.entity.Diary;
import site.binghai.service.BaseService;
import site.binghai.service.CommentService;
import site.binghai.service.DiaryService;
import site.binghai.service.UserService;

import java.util.List;

/**
 * Created by IceSea on 2018/5/13.
 * GitHub: https://github.com/IceSeaOnly
 */
@RestController
@RequestMapping("/")
public class WorldController extends BaseController {
    @Autowired
    private DiaryController diaryController;
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping("worldWild")
    public Object worldWild(@RequestParam Integer page, @RequestParam Integer pageSize) {
        List<Diary> diaryList = diaryService.findAllByOrderByCreatedDesc(page, pageSize);
        if (getUser() != null) {
            diaryList.forEach(diaryController::moreInfo);
        }
        JSONObject data = newJSONObject();
        diaryList.get(0).getId();
        data.put("list", makeItemList(diaryList));
        Long all = diaryService.count();
        data.put("total", all);
        data.put("totalPage", all / pageSize + 1);
        return success(data, null);
    }

    private JSONArray makeItemList(List<Diary> diaryList) {
        JSONArray array = newJSONArray();
        for (Diary diary : diaryList) {
            JSONObject item = newJSONObject();
            item.put("data", diary);
            item.put("owner", userService.getSimpleUserInfoById(diary.getUserId()));
            array.add(item);
    }
        return array;
    }

    @GetMapping("commentList")
    public Object list(@RequestParam Long cid) {
        return success(commentService.findByCid(cid), null);
    }


    @Override
    protected BaseService getService() {
        return null;
    }
}
