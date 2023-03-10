package com.ezenbooks.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezenbooks.main.dto.BestsellerDTO;
import com.ezenbooks.main.dto.BookDTO;
import com.ezenbooks.main.service.BestsellerService;
import com.ezenbooks.main.service.CurationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@RequestMapping("/main")
@RequiredArgsConstructor
@Log4j2
public class MainController {
	
	private final CurationService curationService;
	
	private final BestsellerService bestsellerService;
	
	/**
	 * 주문 기록 기반 추천
	 * 접속중인 유저 ID와 최근 본 책의 번호를 받아 추천 리스트를 반환한다.
	 * 
	 * @author 김요한
	 * @param user_id
	 * @param bought
	 * @return List<BookDTO>
	 */
	@GetMapping("/curation")
	public ResponseEntity<List<BookDTO>> curation(@RequestParam(required = false) Integer user_id, 
												  @RequestParam(required = false) Integer bought) {
				
		log.info("user_id: " + user_id);
		
		if (ObjectUtils.isEmpty(bought) || ObjectUtils.isEmpty(bought)) {
			user_id = 1;
			bought = 1;
		}
		
		List<BookDTO> list = curationService.curationProcess(user_id, bought);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 평점 기반 추천
	 * 접속중인 유저 ID와 최근 본 책의 번호를 받아 추천 리스트를 반환한다.
	 * 
	 * @author 김요한
	 * @param user_id
	 * @param bought
	 * @return List<BookDTO>
	 */
	@GetMapping("/userPick")
	public ResponseEntity<List<BookDTO>> userPick(@RequestParam(required = false) Integer user_id, 
			                                      @RequestParam(required = false) Integer bought) {
		
		// 로그인이 안된 사용자 이거나, 내역이 없는 사용자인 경우
		log.info("user_id: " + user_id);
		
		if (ObjectUtils.isEmpty(bought) || ObjectUtils.isEmpty(bought)) {
			user_id = 1;
			bought = 1;
		}
		
		List<BookDTO> list = curationService.userPickProcess(user_id, bought);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 카테고리 번호, 시작 날짜, 끝나는 날짜를 받아 
	 * 베스트셀러 책 리스트를 반환 합니다. 
	 * 
	 * @author 김요한
	 * @param category_code
	 * @param start_date
	 * @param end_date
	 * @return List<BookDTO>
	 */
	@GetMapping("/bestseller")
	public ResponseEntity<List<BookDTO>> bestseller(
			@RequestParam("category_code") Integer category_code,
			@RequestParam("start_date") String start_date,
			@RequestParam("end_date") String end_date) {
		 
		log.info("bestseller.............");
		
		BestsellerDTO bestsellerDTO = BestsellerDTO.builder()
				.category_code(category_code)
				.start_date(start_date)
				.end_date(end_date)
				.build();
		
		if(ObjectUtils.isEmpty(category_code) || ObjectUtils.isEmpty(start_date) || ObjectUtils.isEmpty(end_date)) {
			return new ResponseEntity<List<BookDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		List<BookDTO> result = bestsellerService.bestsellerProcess(bestsellerDTO);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/**
	 * 카테고리 번호를 받아 신간 리스트를 반환 합니다.
	 * 
	 * @author 김요한
	 * @param category_code
	 * @return List<BookDTO>
	 */
	@GetMapping("/newbooks")
	public ResponseEntity<List<BookDTO>> newbooks(
			@RequestParam("category_code") Integer category_code) {
		
		log.info("new books................");
		
		if(ObjectUtils.isEmpty(category_code)) {
			return new ResponseEntity<List<BookDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		List<BookDTO> result = bestsellerService.newBooksListProcess(category_code);
		
		return new ResponseEntity<List<BookDTO>>(result, HttpStatus.OK);
	}

}
