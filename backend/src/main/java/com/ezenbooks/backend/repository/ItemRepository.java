package com.ezenbooks.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ezenbooks.backend.pay.orderItem;

public interface ItemRepository extends JpaRepository<orderItem, Long> {

    orderItem findByName(String name);
}
