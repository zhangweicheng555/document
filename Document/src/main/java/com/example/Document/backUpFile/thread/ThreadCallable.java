package com.boot.kaizen.backUpFile.thread;

import java.util.concurrent.Callable;

/**
 * 线程反馈类设计
 * 
 * @author a-zhangweicheng
 *
 */
public class ThreadCallable implements Callable<Object> {

	private IThreadExecutor threadExecutor;

	@Override
	public Object call() throws Exception {
		if (threadExecutor != null) {
			return threadExecutor.executor();
		}
		return null;
	}

	public ThreadCallable(IThreadExecutor threadExecutor) {
		this.threadExecutor = threadExecutor;
	}

	public ThreadCallable() {
		super();
	}

	public IThreadExecutor getThreadExecutor() {
		return threadExecutor;
	}

	public void setThreadExecutor(IThreadExecutor threadExecutor) {
		this.threadExecutor = threadExecutor;
	}

}
