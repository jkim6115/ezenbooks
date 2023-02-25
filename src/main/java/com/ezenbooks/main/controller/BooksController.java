package com.ezenbooks.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezenbooks.main.dto.BookDTO;
import com.ezenbooks.main.dto.Criteria;
import com.ezenbooks.main.dto.PageResultDTO;
import com.ezenbooks.main.service.BooksService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@RequestMapping("/books")
@RequiredArgsConstructor
@Log4j2
public class BooksController {

	private final BooksService service;
	
	@GetMapping("/list") 
	public ResponseEntity<PageResultDTO<BookDTO>> list(Criteria criteria) {
		log.info("list...........");
		
		PageResultDTO<BookDTO> result = service.bookListWithPagingProcess(criteria);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	public ResponseEntity<PageResultDTO<?>> review(Criteria criteria) {
		log.info("review...............");
		
		return null;
	}
}
