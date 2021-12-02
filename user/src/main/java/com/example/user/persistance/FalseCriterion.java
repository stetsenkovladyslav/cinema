package com.example.user.persistance;

import org.springframework.lang.NonNull;

import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

class FalseCriterion implements Criterion {
    FalseCriterion() {
    }

    @Override
    public Predicate toPredicate(@NonNull Root<?> root, @NonNull AbstractQuery<?> query, @NonNull CriteriaBuilder cb) {
        return cb.disjunction();
    }
}
