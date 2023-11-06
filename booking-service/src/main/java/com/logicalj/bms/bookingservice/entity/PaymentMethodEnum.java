package com.logicalj.bms.bookingservice.entity;

public enum PaymentMethodEnum {

    CASH("Cash","CASH"),
    UPI("Upi", "UPI"),
    CREDIT_CARD("CreditCard", "CREDIT_CARD"),
    DEBIT_CARD("DebitCard", "DEBIT_CARD"),
    NET_BANKING("NetBanking", "NET_BANKING");


    private final String paymentMethod;
    private final String paymentCode;

    PaymentMethodEnum(String paymentMethod, String paymentCode){
        this.paymentMethod = paymentMethod;
        this.paymentCode = paymentCode;
    }

}
