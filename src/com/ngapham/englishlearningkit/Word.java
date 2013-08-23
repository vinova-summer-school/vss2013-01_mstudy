package com.ngapham.englishlearningkit;

public class Word {
	private int id;
	private String name;
	private String meaning;
	private String type;
	
	public Word(int id, String name, String type, String meaning)
	{
		this.id = id;
		this.name = name;
		this.type = type;
		this.meaning = meaning;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return (name + " (" + type + ".) " + meaning); 
	}
}
