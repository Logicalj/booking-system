package com.logicalj.bms.theaterservice.entity;

public enum SeatTypeEnum {

    ORDINARY("Ordinary","CNF"),
    DELUXE("Waiting", "WT"),
    SEMI_DELUXE("Canceled", "CANCEL"),
    BALCONY("Locked", "LCK");


    private final String seatType;
    private final String seatCode;

    SeatTypeEnum(String seatType, String seatCode){
        this.seatType = seatType;
        this.seatCode = seatCode;
    }

}
