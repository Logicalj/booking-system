package com.logicalj.bms.movieservice.entity;

public enum LanguageEnum {

    HINDI("HINDI"),
    ENGLISH("ENGLISH"),
    PUNJABI("PUNJABI"),
    TAMIL("TAMIL"),
    MALAYALAM("MALAYALAM");

    private final String language;

    LanguageEnum(String language){
        this.language = language;
    }
}
