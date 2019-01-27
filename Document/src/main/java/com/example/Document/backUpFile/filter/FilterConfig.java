package com.example.Document.backUpFile.filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义过滤器配置文件
 * @author weichengz
 * @date 2018年12月24日 上午12:11:37
 */
@Configuration
public class FilterConfig {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean bean=new FilterRegistrationBean();
		MyFilter myFilter=new MyFilter();
		//bean.setFilter(myFilter);
		bean.addUrlPatterns("/login.html");
		return bean;
	}
}
