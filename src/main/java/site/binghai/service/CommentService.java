package site.binghai.service;

import org.springframework.stereotype.Service;
import site.binghai.entity.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceSea on 2018/5/17.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class CommentService extends BaseService<Comment> {

    public List<Comment> findByCid(Long cid) {
        Comment comment = new Comment();
        comment.setCid(cid);
        return query(comment);
    }

    public List<List<Comment>> findByCids(Long[] cids){
        List<List<Comment>> commentList=new ArrayList<>();
        for(Long cid:cids){
            List<Comment> byCid = findByCid(cid);
            commentList.add(byCid);
        }
        return commentList;
    }

    public boolean addComment(Comment comment){
        Comment cs = save(comment);
        if(cs!=null){
            return true;
        }else{
            return false;
        }
    }
}
