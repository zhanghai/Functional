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

import java9.util.function.LongFunction;

import me.zhanghai.java.functional.FunctionalException;

/**
 * Represents a function that accepts a long-valued argument and produces a
 * result.  This is the {@code long}-consuming primitive specialization for
 * {@link ThrowingFunction}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(long)}.
 *
 * @param <R> the type of the result of the function
 *
 * @see ThrowingFunction
 * @since 1.8
 */
@FunctionalInterface
public interface ThrowingLongFunction<R> extends LongFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R applyThrows(long value) throws Exception;

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    default R apply(long value) {
        try {
            return applyThrows(value);
        } catch (Exception e) {
            throw new FunctionalException(e);
        }
    }
}
