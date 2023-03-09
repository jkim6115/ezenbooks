package com.ezenbooks.main.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	private int page;
	private int size;
	private String type;
	private String keyword;
	
	public Criteria() {
		this.page = 1;
		this.size = 10;
	}
	
}
