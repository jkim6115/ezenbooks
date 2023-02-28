package com.ezenbooks.order.service;

import org.springframework.stereotype.Service;

import com.ezenbooks.order.dao.OrderDAO;
import com.ezenbooks.order.dto.StockDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final OrderDAO orderDAO;
	
	@Override
	public Integer updateStockProcess(StockDTO stockDTO) {
		
		return orderDAO.updateStock(stockDTO);
	}

	@Override
	public Integer insertStockProcess(StockDTO stockDTO) {
		
		return orderDAO.insertStock(stockDTO);
	}

}
