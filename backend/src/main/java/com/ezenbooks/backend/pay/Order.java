package com.ezenbooks.backend.pay;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity 
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "orders_seq_gen",
        sequenceName = "orders_order_id_seq",
        initialValue = 1,
        allocationSize = 1)


public class Order {
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="orders_seq_gen")
    @Column(name = "order_num")
    private Long id;
    private String username;

    //주문당 한개의 상품 밖에 못산다고 가정 (test 용)
    @ManyToOne
    private orderItem item;
    private String createdDate;
   

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    private Order(String username, orderItem item) {
        this.username = username;
        this.item = item;
        this.createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd"));
        this.orderStatus = OrderStatus.ORDER;
    }

    /**
     * 주문 생성
     * @return Order entity
     */
    public static Order createOrder(orderItem orderitem, String username) {
        return Order.builder()
                .item(orderitem)
                .username(username)
                .build();
    }

    /**
     * 주문 정상적으로 완료
     */
    public void completeOrder() {
        this.orderStatus = OrderStatus.COMP;
    }

    /**
     * 주문 실패
     */
    public void failOrder() {
        this.orderStatus = OrderStatus.FAIL;
    }
}
