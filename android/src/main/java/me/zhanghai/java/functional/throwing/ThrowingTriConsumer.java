/*
 * Copyright (c) 2018 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.java.functional.throwing;

import java9.util.Objects;

import me.zhanghai.java.functional.FunctionalException;
import me.zhanghai.java.functional.extension.TriConsumer;

@FunctionalInterface
public interface ThrowingTriConsumer<T, U, V> extends TriConsumer<T, U, V> {

    void acceptThrows(T t, U u, V v) throws Exception;

    default void accept(T t, U u, V v) {
        try {
            acceptThrows(t, u, v);
        } catch (Exception e) {
            throw new FunctionalException(e);
        }
    }

    default ThrowingTriConsumer<T, U, V> andThen(ThrowingTriConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> {
            acceptThrows(t, u, v);
            after.acceptThrows(t, u, v);
        };
    }
}
