/*
 * Copyright (c) 2018 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.java.functional.throwing;

import java9.util.Objects;

import me.zhanghai.java.functional.FunctionalException;
import me.zhanghai.java.functional.extension.QuadConsumer;

@FunctionalInterface
public interface ThrowingQuadConsumer<T, U, V, W> extends QuadConsumer<T, U, V, W> {

    void acceptThrows(T t, U u, V v, W w) throws Exception;

    default void accept(T t, U u, V v, W w) {
        try {
            acceptThrows(t, u, v, w);
        } catch (Exception e) {
            throw new FunctionalException(e);
        }
    }

    default ThrowingQuadConsumer<T, U, V, W> andThen(
            ThrowingQuadConsumer<? super T, ? super U, ? super V, ? super W> after) {
        Objects.requireNonNull(after);
        return (t, u, v, w) -> {
            acceptThrows(t, u, v, w);
            after.acceptThrows(t, u, v, w);
        };
    }
}
