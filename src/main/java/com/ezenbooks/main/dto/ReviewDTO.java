package com.ezenbooks.main.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

	private int review_num;
	private int user_num;
	private int book_num;
	private String review_content;
	private String review_rating;
	private String review_writer;
	private LocalDateTime review_reporting_date;
	
}
