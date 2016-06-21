package com.cc.mynote.beans;

import java.io.Serializable;

public class Note implements Serializable {

	int id;
	String info;
	String st;

	public Note() {
	}

	@Override
	public boolean equals(Object o) {
		Note temp = (Note) o;
		if (this.getId() == temp.getId()) {
			return true;
		} else {
			return false;
		}

	}

	public Note(int id, String info, String st) {
		this.id = id;
		this.info = info;
		this.st = st;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}
}
