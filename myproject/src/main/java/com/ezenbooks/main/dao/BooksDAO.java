package com.ezenbooks.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ezenbooks.main.dto.BookDTO;
import com.ezenbooks.main.dto.Criteria;

@Mapper
@Repository
public interface BooksDAO {

	List<BookDTO> getBookListWithPaging(Criteria criteria);
	
	Integer getTotal(Criteria criteria);
	
}
