package com.hatanaka.ecommerce.checkout.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder // permite que eu implemente a classe de um jeito mais fácil
@Data
@Entity
public class CheckoutEntity {

    @Id
    private Long id;

    @Column
    private String code;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum Status {
        CREATED
    }
}
