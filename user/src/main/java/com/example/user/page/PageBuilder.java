package com.example.user.page;

import com.example.user.model.CountValue;

import java.util.List;

public interface PageBuilder {
    <E> List<CountValue> buildCountValue(List<E> filterCriteria, List<CountValue> criteriaValues);
}
