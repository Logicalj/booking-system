package com.logicalj.bms.bookingservice.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
@Builder
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private Date paymentDateTime;
    @Column(nullable = false)
    private String discountCouponId;
    @Column(nullable = false)
    private Integer remoteTransactionId;
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;
    @Column(nullable = false)
    private Integer bookingId;

}
