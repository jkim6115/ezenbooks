package com.ezenbooks.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDTO {
	
	private int book_num;
	
	private int category_code;
	
	private String book_title;
	
	private String book_author;
	
	private String book_image;
	
	private String book_price;
	
	private String book_publisher;
	
	private String book_pubdate;
	
	private String book_isbn;
	
	private String book_desc;

}
