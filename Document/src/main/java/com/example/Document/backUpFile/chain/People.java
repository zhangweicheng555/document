package com.boot.kaizen.backUpFile.chain;

public enum People {

	SISTERTER(1L, "妹妹"), 
	BROTHER(2L,  "哥哥"),
	MOTHER(3L,  "妈妈");

	private Long type;
	private String name;
	
	
	
	private People() {
	}
	private People(Long type, String name) {
		this.type = type;
		this.name = name;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
