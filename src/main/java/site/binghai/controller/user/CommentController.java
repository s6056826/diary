package site.binghai.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.controller.BaseController;
import site.binghai.entity.Comment;
import site.binghai.entity.User;
import site.binghai.service.BaseService;
import site.binghai.service.CommentService;
import site.binghai.service.UserService;
import site.binghai.utils.ExceptionGenerator;

import java.util.*;

/**
 * Created by IceSea on 2018/5/17.
 * GitHub: https://github.com/IceSeaOnly
 */
@RestController
@RequestMapping("/user/comment/")
public class CommentController extends BaseController<Comment> {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @Override
    protected BaseService<Comment> getService() {
        return commentService;
    }


    /**
     * 获取多个文章的评论
     * @param cids
     * @return
     */
    @RequestMapping("lists")
    public Object getListByCids(@RequestParam("cids[]") Long[] cids){
        logger.info("******cids:"+cids);
        List<List<Comment>> byCids = commentService.findByCids(cids);
        return success(byCids,"SUCCESS");
    }

    /**
     * 获取一篇文章多个评论
     * @param cid
     * @return
     */
    @RequestMapping("list")
    public Object getListByCid(@RequestParam("cid") Long cid){
        List<Comment> list = commentService.findByCid(cid);
        List<Long> userList=new ArrayList<>();
        JSONArray jsonArray=new JSONArray();
        Map<Long,String> uidm=new HashMap<>();
        for(Comment comment:list){
            JSONObject jsonObject=new JSONObject();
            Long uid=comment.getUid();
            if(!uidm.containsKey(uid)){
                User user = userService.findById(uid);
                jsonObject.put("img",user.getImg());
            }else{
                jsonObject.put("img",uidm.get(uid));
            }
            jsonObject.put("comment",comment);
            jsonArray.add(jsonObject);
        }
        return success(jsonArray,"");
    }

    @Override
    protected void beforeUpdate(Map map) throws Exception {
        throw ExceptionGenerator.notImplement();
    }

    @Override
    protected void beforeDelete(Long id) throws Exception {
        Comment comment = commentService.findById(id);
        if (!getUser().getId().equals(comment.getUid())) {
            throw ExceptionGenerator.identifyFailed();
        }
    }
}
