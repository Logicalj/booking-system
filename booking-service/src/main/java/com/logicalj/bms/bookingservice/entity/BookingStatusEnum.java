package com.logicalj.bms.bookingservice.entity;

public enum BookingStatusEnum {

    CONFIRMED("Confirmed","CNF"),
    WAITING("Waiting", "WT"),
    CANCELED("Canceled", "CANCEL"),
    LOCKED("Locked", "LCK");


    private final String bookingStatus;
    private final String bookingStatusCode;

    BookingStatusEnum(String bookingStatus, String bookingStatusCode){
        this.bookingStatus = bookingStatus;
        this.bookingStatusCode = bookingStatusCode;
    }

}
