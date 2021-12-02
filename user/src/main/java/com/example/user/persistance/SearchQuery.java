package com.example.user.persistance;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface SearchQuery {

    @Nullable
    Criterion buildCriterion();

    @Nullable
    default <T> Criterion ifNotNull(@Nullable T value, @NonNull Supplier<Criterion> function) {
        return value == null ? null : function.get();
    }

    @Nullable
    default <T> Criterion ifNotNull(@Nullable T value, @NonNull Function<T, Criterion> function) {
        return value == null ? null : function.apply(value);
    }

    @Nullable
    default Criterion and(Criterion... criteria) {
        if (criteria.length == 0)
            return null;
        Criterion[] filtered = Stream.of(criteria).filter(Objects::nonNull).toArray(Criterion[]::new);
        return filtered.length == 0 ? null : Restrictions.and(filtered);
    }

    /**
     * Returns criterion if value is not null, otherwise returns null.
     *
     * @param field Entity property name, must not be null.
     * @param value Search value.
     * @return equals criterion or null.
     */
    @Nullable
    default Criterion eq(@NonNull String field, Object value) {
        return value != null ? Restrictions.eq(field, value) : null;
    }

    @NonNull
    default Criterion isNull(@NonNull String field) {
        return Restrictions.isNull(field);
    }

    @Nullable
    default Criterion likeIgnoreCase(@NonNull String field, String keyword) {
        return StringUtils.hasText(keyword) ? Restrictions.likeIgnoreCase(field, keyword) : null;
    }

    @Nullable
    default Criterion notEq(@NonNull String field, Object value) {
        return value != null ? Restrictions.ne(field, value) : null;
    }

    @Nullable
    default Criterion in(@NonNull Path<?> field, Collection<?> values) {
        return values != null ? Restrictions.in(field, values) : null;
    }
}