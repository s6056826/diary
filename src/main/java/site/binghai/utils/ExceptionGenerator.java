package site.binghai.utils;

/**
 * Created by IceSea on 2018/5/5.
 * GitHub: https://github.com/IceSeaOnly
 */
public class ExceptionGenerator {
    /**
     * 阻止方法
     * */
    public static Exception notImplement() throws Exception {
        throw  new Exception("can not call the method.");
    }

    public static Exception identifyFailed() throws Exception {
        throw new Exception("identify failed!");
    }
}
