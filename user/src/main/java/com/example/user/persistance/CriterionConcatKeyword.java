package com.example.user.persistance;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.Objects;
import java.util.stream.Stream;

public enum CriterionConcatKeyword {
    OR,
    AND;

    public Predicate concat(CriteriaBuilder cb, Predicate... predicates) {
        return switch (this) {
            case OR -> cb.or(predicates);
            case AND -> cb.and(predicates);
        };
    }

    @NonNull
    public Criterion concat(@Nullable Criterion... restrictions) {
        if (restrictions == null || restrictions.length == 0)
            return (root, query, cb) -> null;
        return (root, query, cb) -> {
            Predicate[] predicates = Stream.of(restrictions)
                    .filter(Objects::nonNull)
                    .map((c) -> c.toPredicate(root, query, cb))
                    .filter(Objects::nonNull)
                    .toArray(Predicate[]::new);
            if (predicates.length == 0)
                return null;
            if (predicates.length == 1)
                return predicates[0];
            return concat(cb, predicates);
        };
    }
}