package com.ezenbooks.main.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageResultDTO<DTO> {
	
	private List<DTO> dtoList;
	
	// 총 페이지 번호
	private int totalPage;
	
	// 현재 페이지 번호
	private int page;
	// 목록 사이즈
	private int size;
	
	// 시작 페이지 번호, 끝 페이지 번호
	private int start, end;
	
	// 이전, 다음
	private boolean prev, next;
	
	// 페이지 번호 리스트
	private List<Integer> pageList; 
	
	public PageResultDTO(int totalPage, List<DTO> dtoList, Criteria criteria) {
		this.totalPage = totalPage;
		this.dtoList = dtoList;
		makePageList(criteria);
	}
	
	private void makePageList(Criteria criteria) {
		
		this.page = criteria.getPage();
		this.size = criteria.getSize();
		
		int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
		
		start = tempEnd - 9;
		end = Math.min(totalPage, tempEnd);
		
		prev = start > 1;
		next = totalPage > tempEnd;
		
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
	
}
