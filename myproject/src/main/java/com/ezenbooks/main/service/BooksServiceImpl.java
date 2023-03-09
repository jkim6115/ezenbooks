package com.ezenbooks.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezenbooks.main.dao.BooksDAO;
import com.ezenbooks.main.dto.BookDTO;
import com.ezenbooks.main.dto.Criteria;
import com.ezenbooks.main.dto.PageResultDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {
	
	private final BooksDAO dao;

	@Override
	public PageResultDTO<BookDTO> bookListWithPagingProcess(Criteria criteria) {
		List<BookDTO> result = dao.getBookListWithPaging(criteria);
		
		PageResultDTO<BookDTO> test = new PageResultDTO<>(dao.getTotal(criteria), result, criteria);
		
		return test;
	}

}
