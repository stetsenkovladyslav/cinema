package com.example.user.persistance;

import org.springframework.lang.NonNull;

import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public interface Criterion extends Serializable {
    Predicate toPredicate(@NonNull Root<?> root, @NonNull AbstractQuery<?> query, @NonNull CriteriaBuilder cb);
}
