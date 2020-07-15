package cn.com.eju.pmls.statisticsReport.linkDetail.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置 ,并且配置为默认异步
 * 配置队列长度
 */
@EnableAsync
@Configuration
public class SpringAsyncConfigForPmlsOrderDetail {
	@Bean(name ={"threadPoolTaskExecutorPmlsOrderDetail"})
	protected ThreadPoolTaskExecutor mvcTaskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setThreadNamePrefix("stat-task-executorOrderDetail-");
	    executor.setCorePoolSize(1);
	    executor.setMaxPoolSize(1);
	    return executor;
	}
}
