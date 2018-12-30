package com.boot.kaizen.backUpFile.fileConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 分三步
 *   1。注解
		@Component
		@PropertySource(value="classpath:my.properties",encoding="UTF-8")  任何形式的文件都可以    遗留一个问题  配置文件外置
		@ConfigurationProperties(prefix = "stu")
	 2.文件位置
 * 使用：
 *  @Autowired
	private Stu stu;
 * 
 * @author weichengz
 * @date 2018年12月23日 下午10:25:26
 */
@Component
@PropertySource(value="classpath:my.txt",encoding="UTF-8")
@ConfigurationProperties(prefix = "stu")
public class Stu {

	private String name;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Stu(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Stu() {
		super();
	}

}
