package com.example.user.page;

import com.example.user.model.CountValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageBuilderImpl implements PageBuilder {
    @Override
    public <E> List<CountValue> buildCountValue(List<E> filterCriteria, List<CountValue> criteriaValues) {
        return filterCriteria.stream()
                .map(criteria -> {
                    for (CountValue criteriaValue : criteriaValues) {
                        if (criteriaValue.getValue() != null && criteriaValue.getValue().equals(criteria)) {
                            return criteriaValue;
                        }
                    }
                    return new CountValue(criteria, 0L);
                }).collect(Collectors.toList());
    }
}