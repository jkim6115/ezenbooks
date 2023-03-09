package com.ezenbooks.main.service;

import java.util.List;

import com.ezenbooks.main.dto.BestsellerDTO;
import com.ezenbooks.main.dto.BookDTO;

public interface BestsellerService {
	
	public List<BookDTO> bestsellerProcess(BestsellerDTO dto);
	
	public List<BookDTO> newBooksListProcess(Integer category_code);

}
