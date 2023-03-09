package com.ezenbooks.order.service;

import com.ezenbooks.order.dto.StockDTO;

public interface OrderService {
	
	public Integer insertStockProcess(StockDTO stockDTO);

	public Integer updateStockProcess(StockDTO stockDTO);
}
