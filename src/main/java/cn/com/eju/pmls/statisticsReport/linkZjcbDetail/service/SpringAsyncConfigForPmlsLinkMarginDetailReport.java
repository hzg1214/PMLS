package cn.com.eju.pmls.statisticsReport.linkZjcbDetail.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置 ,并且配置为默认异步
 * 配置队列长度
 * zhenggang.Huang
 */
@EnableAsync
@Configuration
public class SpringAsyncConfigForPmlsLinkMarginDetailReport {
	@Bean(name ={"threadPoolTaskExecutorPmlsLinkMarginDetail"})
	protected ThreadPoolTaskExecutor mvcTaskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setThreadNamePrefix("stat-task-executorTwo-");
	    executor.setCorePoolSize(1);
	    executor.setMaxPoolSize(1);
	    return executor;
	}
}
