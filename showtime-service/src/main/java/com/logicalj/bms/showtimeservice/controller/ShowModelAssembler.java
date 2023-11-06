package com.logicalj.bms.showtimeservice.controller;

import com.logicalj.bms.showtimeservice.entity.Show;
import com.logicalj.bms.showtimeservice.model.ShowDto;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ShowModelAssembler extends RepresentationModelAssemblerSupport<Show, ShowDto> {
    public ShowModelAssembler() {
        super(ShowtimeController.class, ShowDto.class);
    }

    @Override
    public ShowDto toModel(Show entity) {
        ShowDto model = new ShowDto();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

}
