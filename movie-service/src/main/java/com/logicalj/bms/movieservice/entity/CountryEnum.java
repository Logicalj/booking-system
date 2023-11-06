package com.logicalj.bms.movieservice.entity;

public enum CountryEnum {

    INDIA("India", "IND"),
    US("United States", "USA"),
    UK ("UnitedKingdom", "UK");

    private final String countryCode;
    private final String countryName;

    CountryEnum(String name, String code){
        this.countryName = name;
        this.countryCode = code;
    }

}
