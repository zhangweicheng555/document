package com.boot.kaizen.backUpFile.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义过滤器  做高优先级  比spring security  还早
 * @author weichengz
 * @date 2018年12月24日 上午12:08:56
 */
public class MyFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String,String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			System.out.println(entry.getKey() +"   "+Arrays.toString(entry.getValue()));
		}
		/*String name="zwc";
		if (request.getParameter("zwc").equals(name)) {
			
		}else {
			throw new IllegalArgumentException("缺少后缀名");
		}*/
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
