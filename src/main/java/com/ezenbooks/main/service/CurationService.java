package com.ezenbooks.main.service;

import java.util.List;

import com.ezenbooks.main.dto.BookDTO;
import com.ezenbooks.main.dto.ReviewDTO;

public interface CurationService {
	
	public List<ReviewDTO> dataSetProcess();
	
	public List<BookDTO> curationProcess(int user_id, int bought);
	
}
