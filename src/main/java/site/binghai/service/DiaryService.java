
package site.binghai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import site.binghai.entity.Diary;
import site.binghai.entity.User;

import java.util.List;

/**
 * Created by IceSea on 2018/5/5.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class DiaryService extends BaseService<Diary> {
    @Autowired
    private DiaryDao diaryDao;

    public List<Diary> findByUser(Long userId, Integer page, Integer pageSize) {
        page = page < 0 ? 0 : page;
        pageSize = pageSize > 100 ? 100 : pageSize;
        Diary diary = new Diary();
        diary.setUserId(userId);
        //List<Diary> ls = pageQuery(diary, page, pageSize);
        List<Diary> ls = sortQuery(diary, page, pageSize, "id", true);
        return ls;
    }

    public List<Diary> findAllByOrderByCreatedDesc(int page, int pageSize) {
        return diaryDao.findAllByOrderByCreatedDesc(new PageRequest(page, pageSize));
    }
}
