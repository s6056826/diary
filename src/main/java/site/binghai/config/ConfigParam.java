package site.binghai.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by IceSea on 2018/5/17.
 * GitHub: https://github.com/IceSeaOnly
 */
@Component
@Data
@ConfigurationProperties(prefix = "qiniu")
@PropertySource("classpath:application.properties")
public class ConfigParam {

    private String bucket;
    private String ak;
    private String sk;
}
