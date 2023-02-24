package com.ezenbooks.main.service;

import java.util.List;

import com.ezenbooks.main.dto.BookDTO;

public interface CurationService {
	
	public List<BookDTO> curationProcess(int user_id, int bought);
	
	public List<BookDTO> userPickProcess(int user_id, int bought);
	
}
