/*
 * Copyright (c) 2018 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.java.functional.extension;

import java9.util.Objects;

@FunctionalInterface
public interface QuadPredicate<T, U, V, W> {

    boolean test(T t, U u, V v, W w);

    default QuadPredicate<T, U, V, W> and(
            QuadPredicate<? super T, ? super U, ? super V, ? super W> other) {
        Objects.requireNonNull(other);
        return (T t, U u, V v, W w) -> test(t, u, v, w) && other.test(t, u, v, w);
    }

    default QuadPredicate<T, U, V, W> negate() {
        return (T t, U u, V v, W w) -> !test(t, u, v, w);
    }

    default QuadPredicate<T, U, V, W> or(
            QuadPredicate<? super T, ? super U, ? super V, ? super W> other) {
        Objects.requireNonNull(other);
        return (T t, U u, V v, W w) -> test(t, u, v, w) || other.test(t, u, v, w);
    }
}
