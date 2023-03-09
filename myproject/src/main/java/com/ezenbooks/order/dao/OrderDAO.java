package com.ezenbooks.order.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ezenbooks.order.dto.StockDTO;

@Mapper
@Repository
public interface OrderDAO {
	
	public Integer insertStock(StockDTO stockDTO);

	public Integer updateStock(StockDTO stockDTO);
	
}
