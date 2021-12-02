package com.example.user.service.movie;

import com.example.user.persistance.Criterion;
import com.example.user.persistance.Restrictions;
import com.example.user.persistance.SearchQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.*;
import java.util.stream.Stream;

public class SpecificationBuilder {
    private SpecificationBuilder() {
        throw new IllegalStateException("Utility class");
    }

    @Nullable
    public static <T> Specification<T> build(@Nullable SearchQuery query) {
        if (query == null)
            return null;
        return build(null, query.buildCriterion());
    }

    @NonNull
    public static <T> Specification<T> build(@Nullable String[] fetch, Criterion... restrictions) {
        return new Specification<>() {
            @Nullable
            @Override
            public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
                if (fetch != null && fetch.length > 0) {
                    Class<?> qClass = query.getResultType();
                    if (qClass != Long.class)
                        Stream.of(fetch).forEach(f -> root.fetch(f, JoinType.LEFT));
                }
                return Restrictions.and(restrictions).toPredicate(root, query, cb);
            }
        };
    }
}