package com.ezenbooks.backend.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ezenbooks.backend.pay.Order;
import com.ezenbooks.backend.pay.OrderForm;
import com.ezenbooks.backend.pay.orderItem;
import com.ezenbooks.backend.repository.ItemRepository;
import com.ezenbooks.backend.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /**
     * 상품 생성
     * 주문 폼 작성시 상품 생성
     */
    public orderItem createItem(OrderForm form) {
        orderItem orderitem = orderItem.builder()
                .name(form.getItemName())
                .price(form.getPrice())
                .build();
        return itemRepository.save(orderitem);
    }

    /**
     * 주문 생성
     * @param form 주문 폼
     */
    public Order createOrder(OrderForm form) {
        orderItem orderitem = itemRepository.findByName(form.getItemName());
        Order order = Order.createOrder(orderitem, form.getUsername());
        return orderRepository.save(order);
    }

    /**
     * 주문 삭제
     * @param id order id
     */
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    /**
     * 주문 실패 로그
     * @param id order id
     */
    @Transactional
    public void failOrder(Long id) {
        orderRepository.deleteById(id);
    }


    /**
     * 주문 정상 완료
     * @param id order id
     */
    @Transactional
    public void completeOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.completeOrder();
    }

    /**
     * 주문 찾기
     */
    @Transactional(readOnly = true)
    public Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

}
