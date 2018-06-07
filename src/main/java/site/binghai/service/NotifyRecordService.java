package site.binghai.service;

import org.hibernate.annotations.LazyToOne;
import org.springframework.stereotype.Service;
import site.binghai.entity.Diary;
import site.binghai.entity.NotifyRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class NotifyRecordService extends  BaseService<NotifyRecord> {
    /**
     * 添加通知信息
     * @param notifyRecord
     * @return
     */
    public List<NotifyRecord> getNotifyRecordList(NotifyRecord notifyRecord){
        List<NotifyRecord> query = query(notifyRecord);
        return  query;
    }


        public List<NotifyRecord> getNotifyRecordListByPage(Long userId, Integer page, Integer pageSize) {
            page = page < 0 ? 0 : page;
            pageSize = pageSize > 100 ? 100 : pageSize;
            NotifyRecord notifyRecord = new NotifyRecord();
            notifyRecord.setToid(userId);
            notifyRecord.setFlag(0);
            //List<NotifyRecord> ls = pageQuery(notifyRecord, page, pageSize);
            List<NotifyRecord> ls = sortQuery(notifyRecord, page, pageSize, "id", true);
            return ls;
        }


    /**
     * 更新通知状态信息 flag=1
     */
    public NotifyRecord updataNotifyStatus(Long id) throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("flag",1);
        NotifyRecord notifyRecord = updateAndSave(map);
        return  notifyRecord;
    }

    /**
     * 批量更新notifyStatus的状态
     * @param ids
     * @throws Exception
     */
    public void updataNotifyStatusBatch(List<Long> ids) throws Exception {
        for(Long id:ids){
            Map<String,Object> map=new HashMap<>();
            map.put("id",id);
            map.put("flag",1);
            updateAndSave(map);
        }
    }
}
