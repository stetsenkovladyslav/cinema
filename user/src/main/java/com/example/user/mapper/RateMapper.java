package com.example.user.mapper;


import com.example.root.dto.rate.RateDTO;
import com.example.root.dto.rate.RateRequest;
import com.example.root.model.Rate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RateMapper extends CrudMapper<Rate, RateDTO, RateRequest, RateRequest> {


    RateDTO toDTO(Rate rate);

    Rate dtoToRate(RateDTO rateDTO);
}
