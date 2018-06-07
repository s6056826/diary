package site.binghai.controller.user;

import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.controller.BaseController;
import site.binghai.entity.NotifyRecord;
import site.binghai.service.BaseService;
import site.binghai.service.NotifyRecordService;

import java.util.List;

@RestController
@RequestMapping("/user/diary/notify")
public class NotifyRecordController extends BaseController<NotifyRecord> {

    @Autowired
    private NotifyRecordService recordService;

    @Override
    protected BaseService<NotifyRecord> getService() {
        return this.recordService;
    }
    @RequestMapping("get")
    public  Object getDiaryReplyNotify(Long uid, @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int pageSize){
           NotifyRecord notifyRecord =NotifyRecord.builder().build();
           notifyRecord.setToid(uid);
           notifyRecord.setFlag(0);
           List<NotifyRecord> notifyRecordList = recordService.getNotifyRecordListByPage(uid,page,pageSize);
           return  success(notifyRecordList,null);
    }


    /**
     * 更新通知状态 flag=1
     * @param id
     */
    @RequestMapping("updata")
    public Object updataNotifyStuse(Long id){
        try {
            NotifyRecord notifyRecord = recordService.updataNotifyStatus(id);
            if(notifyRecord!=null){
                return  success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  fail("updata error");
    }

}
