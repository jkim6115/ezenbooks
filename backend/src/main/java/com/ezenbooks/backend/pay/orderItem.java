package com.ezenbooks.backend.pay;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class orderItem {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;

    @Builder
    public orderItem(String name, int price) {
        this.name = name;
        this.price = price;
        
    }
    
   
}
