package com.kuandeng.tags.export.model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String level;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Person(String id, String name, String level) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
	}

	public Person() {
		super();
	}

}
