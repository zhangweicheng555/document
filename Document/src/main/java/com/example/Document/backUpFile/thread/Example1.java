package com.boot.kaizen.backUpFile.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现线程执行接口
 */
import org.springframework.stereotype.Component;
import com.boot.kaizen.dao.PermissionDao;
import com.boot.kaizen.model.Permission;
import com.boot.kaizen.util.SpringUtil;

@Component
public class Example1 implements IThreadExecutor {

	
	@Override
	public List<Object> executor() {
		PermissionDao permissionDao=(PermissionDao) SpringUtil.getBean("permissionDao");
		List<Permission> listAll = permissionDao.listAll();
		List<Object> list=new ArrayList<>();
		for (Permission permission : listAll) {
			System.out.println("执行了。。。。。。。。。。。。。。。。。。");
			list.add(permission.getName());
		}
		return list;
	}

}
