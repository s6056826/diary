package site.binghai.service;

import org.springframework.stereotype.Service;
import site.binghai.entity.PraiseRecord;

/**
 * Created by IceSea on 2018/5/13.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class PraiseRecordService extends BaseService<PraiseRecord> {
    public PraiseRecord findByCidAndUserId(Long cid, Long userId) {
        PraiseRecord praiseRecord = new PraiseRecord();
        praiseRecord.setDiaryId(cid);
        praiseRecord.setUserId(userId);
        return queryOne(praiseRecord);
    }
}
