package site.binghai.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.config.ConfigParam;
import site.binghai.utils.BaseBean;

/**
 * Created by IceSea on 2018/5/17.
 * GitHub: https://github.com/IceSeaOnly
 */
@RestController
@RequestMapping("/api/")
public class CdnController extends BaseBean {
    @Autowired
    private ConfigParam configParam;

    @RequestMapping("qiniuToken")
    @CrossOrigin(origins = "*")
    public Object qiniuToken(){
        String bucket = configParam.getBucket();
        Auth auth = Auth.create(configParam.getAk(), configParam.getSk());
        String upToken = auth.uploadToken(bucket);

        JSONObject obj = new JSONObject();
        obj.put("data",upToken);
        obj.put("status","SUCCESS");
        obj.put("msg","SUCCESS");

        return obj;
    }
}
