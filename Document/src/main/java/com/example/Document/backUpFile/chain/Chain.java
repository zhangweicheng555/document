package com.boot.kaizen.backUpFile.chain;

import java.util.UUID;

/**
 * 内部类的实现 本节点 上节点 上上节点
 * 
 * @author a-zhangweicheng
 *
 */
public class Chain {

	private static class Persion {
		private Long id;
		private Persion prev;
		private Persion next;

		public Persion(Long id) {
			this.id = id;
		}
	}

	private static final Persion first;
	static {
		first = new Persion(People.BROTHER.getType());
		first.prev = new Persion(People.SISTERTER.getType());
		first.next = new Persion(People.MOTHER.getType());
	}

	/** 得到下一个 得到上一个类推 */
	public static Long nextOf(Long id) {
		Persion curr = getNode(id);
		return curr.next == null ? null : curr.next.id;
	}

	private static Persion getNode(Long id) {
		Persion persion = first;
		while (persion != null) {
			if (persion.id.equals(id)) {
				return persion;
			}
			persion = persion.next;
		}
		return null;
	}
	
	/***
	 * 使用uuid定义id
	* @Description:
	* @author: weichengz
	 */
		//System.out.println(UUID.randomUUID().toString());
}
