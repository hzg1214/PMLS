package cn.com.eju.pmls.configure;

import cn.com.eju.deal.base.helper.LogHelper;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PrometheusConfig {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @PostConstruct
    public void init() {
        logger.info("prometheus init...");
        DefaultExports.initialize();
        logger.info("prometheus has been initialized...");
    }
}
