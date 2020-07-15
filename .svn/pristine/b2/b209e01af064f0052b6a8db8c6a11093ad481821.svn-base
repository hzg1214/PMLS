package cn.com.eju.deal.contract;


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
public class SpringAsyncConfig {
	@Bean(name ={"threadPoolTaskExecutor"})
	protected ThreadPoolTaskExecutor mvcTaskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setThreadNamePrefix("stat-task-executor-");
	    executor.setCorePoolSize(20);
	    executor.setMaxPoolSize(120);
	    return executor;
	}
}
