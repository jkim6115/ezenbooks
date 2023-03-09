package com.ezenbooks.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezenbooks.main.dao.BestsellerDAO;
import com.ezenbooks.main.dto.BestsellerDTO;
import com.ezenbooks.main.dto.BookDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BestsellerServiceImpl implements BestsellerService {
	
	private final BestsellerDAO dao;

	@Override
	public List<BookDTO> bestsellerProcess(BestsellerDTO dto) {
		
		return dao.getBestsellerList(dto);
	}

	@Override
	public List<BookDTO> newBooksListProcess(Integer category_code) {
		
		return dao.getNewBooksList(category_code);
	}

}
