package com.example.admin.mapper;


import com.example.admin.dto.rate.RateDTO;
import com.example.admin.dto.rate.RateRequest;
import com.example.admin.model.Rate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RateMapper extends CrudMapper<Rate, RateDTO, RateRequest, RateRequest> {


    RateDTO toDTO(Rate rate);

    Rate dtoToRate(RateDTO rateDTO);
}
