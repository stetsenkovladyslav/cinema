package com.example.user.model;

import com.example.root.enums.BaseEnum;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;

@Getter
@Setter
public class CountValue {
    private String value;
    private Long count;

    public CountValue(Object value, Long count) {
        if(value instanceof BaseEnum){
            this.value = ((BaseEnum) value).getValue();

        }
        else if(value instanceof String){
            this.value = (String) value;
        }
        else if(value instanceof LocalDate){
            this.value = value.toString();

        }
        this.count = count;
    }

}