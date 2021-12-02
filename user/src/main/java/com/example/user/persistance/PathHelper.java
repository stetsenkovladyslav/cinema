package com.example.user.persistance;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.Path;

public class PathHelper {
    private PathHelper() {
        throw new IllegalStateException("Utility class");
    }

    @NonNull
    public static <X, Y> Path<Y> getPath(@NonNull Path<X> path, @NonNull String attributeName) {
        int idx = attributeName.indexOf('.');
        if (idx < 0)
            return path.get(attributeName);
        return getPath(path.get(attributeName.substring(0, idx)), attributeName.substring(idx + 1));
    }
}