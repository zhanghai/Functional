/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package me.zhanghai.java.functional.throwing;

import java9.util.Objects;
import java9.util.function.LongUnaryOperator;

import me.zhanghai.java.functional.FunctionalException;

/**
 * Represents an operation on a single {@code long}-valued operand that produces
 * a {@code long}-valued result.  This is the primitive type specialization of
 * {@link ThrowingUnaryOperator} for {@code long}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #applyAsLong(long)}.
 *
 * @see ThrowingUnaryOperator
 * @since 1.8
 */
@FunctionalInterface
public interface ThrowingLongUnaryOperator extends LongUnaryOperator {

    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    long applyAsLongThrows(long operand) throws Exception;

    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    default long applyAsLong(long operand) {
        try {
            return applyAsLongThrows(operand);
        } catch (Exception e) {
            throw new FunctionalException(e);
        }
    }

    /**
     * Returns a composed operator that first applies the {@code before}
     * operator to its input, and then applies this operator to the result.
     * If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before the operator to apply before this operator is applied
     * @return a composed operator that first applies the {@code before}
     * operator and then applies this operator
     * @throws NullPointerException if before is null
     *
     * @see #andThen(ThrowingLongUnaryOperator)
     */
    default ThrowingLongUnaryOperator compose(ThrowingLongUnaryOperator before) {
        Objects.requireNonNull(before);
        return (long v) -> applyAsLongThrows(before.applyAsLongThrows(v));
    }

    /**
     * Returns a composed operator that first applies this operator to
     * its input, and then applies the {@code after} operator to the result.
     * If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after the operator to apply after this operator is applied
     * @return a composed operator that first applies this operator and then
     * applies the {@code after} operator
     * @throws NullPointerException if after is null
     *
     * @see #compose(ThrowingLongUnaryOperator)
     */
    default ThrowingLongUnaryOperator andThen(ThrowingLongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (long t) -> after.applyAsLongThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static ThrowingLongUnaryOperator identity() {
        return t -> t;
    }
}
