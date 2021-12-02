package com.example.user.persistance;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.Path;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public final class Restrictions {
    private Restrictions() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Returns equals criterion. In case if value is null the property will be also checked for null.
     *
     * @param field entity property.
     * @param value searched value.
     * @return equals criterion.
     * @see #isNull(String)
     */
    @NonNull
    public static Criterion eq(@NonNull String field, Object value) {
        return value != null
                ? (root, query, cb) -> cb.equal(PathHelper.getPath(root, field), value)
                : isNull(field);
    }

    @NonNull
    public static Criterion ne(@NonNull String field, Object value) {
        return value != null
                ? (root, query, cb) -> cb.notEqual(PathHelper.getPath(root, field), value)
                : isNotNull(field);
    }

    @NonNull
    public static <T extends Comparable<? super T>> Criterion gt(@NonNull String field, T value) {
        return (root, query, cb) -> cb.greaterThan(PathHelper.getPath(root, field), value);
    }

    @NonNull
    public static <T extends Comparable<? super T>> Criterion gtOrEq(@NonNull String field, T value) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(PathHelper.getPath(root, field), value);
    }

    @NonNull
    public static <T extends Comparable<? super T>> Criterion lt(@NonNull String field, T value) {
        return (root, query, cb) -> cb.lessThan(PathHelper.getPath(root, field), value);
    }

    @NonNull
    public static <T extends Comparable<? super T>> Criterion ltOrEq(@NonNull String field, T value) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(PathHelper.getPath(root, field), value);
    }

    @Nullable
    public static Criterion like(@NonNull String field, String value) {
        return value != null
                ? (root, query, cb) -> cb.like(PathHelper.getPath(root, field), "%" + value + "%")
                : null;
    }

    @Nullable
    public static Criterion likeIgnoreCase(@NonNull String field, String value) {
        return value != null
                ? (root, query, cb) -> cb.like(cb.lower(PathHelper.getPath(root, field)), "%" + value.toLowerCase() + "%")
                : null;
    }

    @NonNull
    public static <T extends Comparable<? super T>> Criterion between(@NonNull String field, T from, T to) {
        return (root, query, cb) -> cb.between(PathHelper.getPath(root, field), from, to);
    }

    @NonNull
    public static Criterion isNull(@NonNull String field) {
        return (root, query, cb) -> PathHelper.getPath(root, field).isNull();
    }

    @NonNull
    public static Criterion isNotNull(@NonNull String field) {
        return (root, query, cb) -> PathHelper.getPath(root, field).isNotNull();
    }

    private static Set<?> valuesArrayToSet(Object[] values) {
        if (values.length == 1 && values[0].getClass().isArray()) {
            if (values[0] instanceof int[])
                return IntStream.of((int[]) values[0]).boxed().collect(Collectors.toSet());
            if (values[0] instanceof long[])
                return LongStream.of((long[]) values[0]).boxed().collect(Collectors.toSet());
            return Stream.of((Object[]) values[0]).collect(Collectors.toSet());
        }
        return Set.of(values);
    }

    @NonNull
    public static Criterion in(@NonNull String field, Object... values) {
        if (values == null || values.length == 0)
            return new FalseCriterion();

        return in(field, valuesArrayToSet(values));
    }
    @NonNull
    public static Criterion in(@NonNull String field, Collection<?> values) {
        if (values == null || values.isEmpty())
            return new FalseCriterion();
        return (root, query, cb) -> PathHelper.getPath(root, field).in(values);
    }

    @NonNull
    public static Criterion in(@NonNull Path<?> path, Collection<?> values) {
        if (values == null || values.isEmpty())
            return new FalseCriterion();
        return (root, query, cb) -> path.in(values);
    }

    @Nullable
    public static Criterion notIn(@NonNull String field, Object... values) {
        if (values == null || values.length == 0)
            return new FalseCriterion();
        return notIn(field, valuesArrayToSet(values));
    }

    @Nullable
    public static Criterion notIn(@NonNull String field, Collection<?> values) {
        if (values == null || values.isEmpty())
            return null;
        return (root, query, cb) -> PathHelper.getPath(root, field).in(values).not();
    }

    /**
     * Joins all criteria by {@literal or} keyword. All {@literal null} values in the restrictions array will be ignored.
     *
     * @param restrictions array of criteria to search by.
     * @return joined criterion.
     */
    @NonNull
    public static Criterion or(Criterion... restrictions) {
        return CriterionConcatKeyword.OR.concat(restrictions);
    }

    /**
     * Joins all criteria by {@literal and} keyword. All {@literal null} values in the restrictions array will be ignored.
     *
     * @param restrictions array of criteria to search by.
     * @return joined criterion.
     */
    @NonNull
    public static Criterion and(Criterion... restrictions) {
        return CriterionConcatKeyword.AND.concat(restrictions);
    }
}
