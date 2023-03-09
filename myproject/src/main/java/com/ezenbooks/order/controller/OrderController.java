package com.ezenbooks.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezenbooks.main.dto.Criteria;
import com.ezenbooks.main.dto.PageResultDTO;
import com.ezenbooks.order.dto.StockDTO;
import com.ezenbooks.order.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
@RequiredArgsConstructor
@Log4j2
public class OrderController {
	
	private final OrderService orderService;
	
	@GetMapping("/stock")
	public ResponseEntity<PageResultDTO<StockDTO>> getStock(Criteria criteria) {
		log.info("StockDTO: " + criteria);
		
		return null;
	}
	
	@PostMapping("/stock")
	public ResponseEntity<String> insertStock(@RequestBody StockDTO stockDTO) {
		log.info("Post StockDTO: " + stockDTO);
		orderService.insertStockProcess(stockDTO);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PutMapping("/stock")
	public ResponseEntity<String> updateStock(@RequestBody List<StockDTO> stockDTOList) {
		log.info("Put StockDTO: " + stockDTOList);
		
		stockDTOList.forEach(stockDTO -> {
			orderService.updateStockProcess(stockDTO);
		});
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}
