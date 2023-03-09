package com.ezenbooks.main.service;

import com.ezenbooks.main.dto.BookDTO;
import com.ezenbooks.main.dto.Criteria;
import com.ezenbooks.main.dto.PageResultDTO;

public interface BooksService {

	PageResultDTO<BookDTO> bookListWithPagingProcess(Criteria criteria);
	
}
