package site.binghai.controller.user;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.controller.BaseController;
import site.binghai.entity.Diary;
import site.binghai.entity.PraiseRecord;
import site.binghai.service.BaseService;
import site.binghai.service.DiaryService;
import site.binghai.service.PraiseRecordService;
import site.binghai.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by IceSea on 2018/5/5.
 * GitHub: https://github.com/IceSeaOnly
 */
@RestController
@RequestMapping("/user/diary/")
public class DiaryController extends BaseController<Diary> {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private PraiseRecordService praiseRecordService;
    @Autowired
    private UserService userService;

    @Override
    protected BaseService<Diary> getService() {
        return diaryService;
    }

    @GetMapping("list")
    public Object list(Long cid, Long userId, Integer page, Integer pageSize) {
        if (cid != null) {
            Diary diary = diaryService.findById(cid);
            moreInfo(diary);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("diary",diary);
            jsonObject.put("owner",userService.getSimpleUserInfoById(diary.getUserId()));
            return success(diary, null);
        }
        page = page == null ? 0 : page;
        pageSize = pageSize == null ? 10 : pageSize;
        userId = userId == null ? getUser().getId() : userId;
        List<Diary> ls = diaryService.findByUser(userId, page, pageSize);
        ls.forEach(this::moreInfo);
        return success(ls, null);
    }

    public void moreInfo(Diary diary) {
        Long userId = getUser().getId();
        PraiseRecord record = praiseRecordService.findByCidAndUserId(diary.getId(), userId);
        if (record == null) {
            diary.setPraise(false);
        } else {
            diary.setPraise(true);
        }
    }

    @GetMapping("praise")
    public Object praise(@RequestParam Long cid) {
        Long userId = getUser().getId();
        PraiseRecord record = praiseRecordService.findByCidAndUserId(cid, userId);
        if (record == null) {
            record = new PraiseRecord();
            record.setUserId(userId);
            record.setDiaryId(cid);
            Diary diary = diaryService.findById(cid);
            diary.setPraiseMark(diary.getPraiseMark() + 1);
            diaryService.update(diary);
            praiseRecordService.save(record);
            return success(diary, null);
        } else {
            Diary diary = diaryService.findById(cid);
            diary.setPraiseMark(diary.getPraiseMark() - 1);
            diaryService.update(diary);
            praiseRecordService.delete(record.getId());
            return success(diary, null);
        }
    }

    @Override
    protected void beforeAdd(Map map) throws Exception {
        map.put("userId", getUser().getId());
        map.put("praiseMark", 0);
    }
}
